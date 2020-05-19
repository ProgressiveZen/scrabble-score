package lood.corporatezen.scrabblescore

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.android.billingclient.api.*
import com.example.android.scrabblescore.BuildConfig
import timber.log.Timber
import java.io.IOException
import java.security.*
import java.security.spec.InvalidKeySpecException
import java.security.spec.X509EncodedKeySpec

/**
 * Singleton used for Billing
 *
 * Call build asap
 */
object ScrabbleBilling {

    private val _onPurchase = MutableLiveData<Boolean>()
    val onPurchase: LiveData<Boolean>
        get() = _onPurchase

    private val _hasFailed = MutableLiveData<Boolean>()
    val hasFailed: LiveData<Boolean>
        get() = _hasFailed

    /**
     * Before showing price, check that purchase is not pending
     * Code Example:
     *
     * onCreate(){
     *  Billing.localPrice.observe {
     *          if (Billing.isPending){
     *              _price.value = resources.getString(R.string.billing_pending)
     *          } else {
     *              _price.value = resources.getString(R.string.billing_price, Billing.localPrice)
     *          }
     *  }
     * }
     */
    // Is string due to showing currency symbol
    private val _localPrice = MutableLiveData<String>()
    val localPrice: LiveData<String>
        get() = _localPrice

    var isPending = false

    private var skuCode: String? = null   // Item key from play console, needs to match play console
    private var skuResultList = mutableListOf<SkuDetails>() // Billing Client loaded

    @Volatile
    private lateinit var billingClient: BillingClient
    private lateinit var activity: Activity // Needed to show purchase screen to user
    private lateinit var context: Context


    /**
     * Build Billing service
     *
     * Call in activity that holds the purchase option
     *
     * @param sku Item sku for in-app purchase. Set through the play console
     */
    fun build(act: Activity, sku: String) {
        skuCode = sku

        if (!ScrabbleBilling::activity.isInitialized) {
            activity = act
            context = act.applicationContext
        }

        synchronized(this) {
            if (!ScrabbleBilling::billingClient.isInitialized) {
                billingClient = BillingClient.newBuilder(activity)
                        .setListener { billingResult, purchases ->
                            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                                Timber.i("billing good")
                                acknowledgePurchases(purchases)
                            } else {
                                Timber.i("billing failed")
                            }
                        }
                        .enablePendingPurchases()
                        .build()

                startConnection()
            }
        }
    }

    /**
     * Destroys/Refunds Purchase
     * Used for testing basically
     */
    fun onClickConsumeVip(context: Context) {
        if (BuildConfig.DEBUG && billingClient.isReady) {
            val results = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
            val purchasesList = results.purchasesList

            purchasesList.forEach {
                if (it.purchaseState == Purchase.PurchaseState.PURCHASED && it.sku == skuCode) {
                    val params = ConsumeParams.newBuilder().setPurchaseToken(it.purchaseToken).build()

                    billingClient.consumeAsync(params) { result, _ ->
                        if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                            Timber.i("Consume VIP")
                            _onPurchase.value = false
                         //   VipStatus.removeVip(context)
                        } else {
                            Timber.e("Can't allowMultiplePurchases, responseCode: ${result.responseCode}")
                        }
                    }
                }
            }
        }
    }

    /**
     * Called when user clicks button to purchase
     */
    fun onClickPurchase() {
        if (ScrabbleBilling::billingClient.isInitialized && skuResultList.isNotEmpty()) {
            val billingFlowParams = BillingFlowParams
                    .newBuilder()
                    .setSkuDetails(skuResultList.first())
                    .build()

            billingClient.launchBillingFlow(activity, billingFlowParams)
        }
    }

    /**
     * Called after Billing Client is built
     * Time to start the connection
     */
    private fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult?) {
                if (billingResult?.responseCode == BillingClient.BillingResponseCode.OK) {
                    loadSkus()
                } else {
                    Timber.e("BILLING | startConnection | RESULT: ${billingResult?.responseCode}")
                    _hasFailed.value = true
                }
            }

            override fun onBillingServiceDisconnected() {
                Timber.i("BILLING | onBillingServiceDisconnected | DISCONNECTED")
                _hasFailed.value = true
            }
        })
    }

    /**
     * Called after Billing Client is connected
     * Time to load purchases
     */
    private fun loadSkus() {
        if (billingClient.isReady) {
            val params = SkuDetailsParams
                    .newBuilder()
                    .setSkusList(listOf(skuCode))
                    .setType(BillingClient.SkuType.INAPP)
                    .build()

            billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    skuResultList = skuDetailsList


                    // Get local price
                    val item = skuResultList[0]
                    _localPrice.value = item.price


                    // Now that skus are loaded, time to check if they have purchased it
                    // This is done each time in case of refunds etc
                    checkIfVipHasBeenPurchased()
                } else {
                    Timber.e("Can't querySkuDetailsAsync, responseCode: $billingResult")
                }
            }
        } else {
            Timber.i("Billing Client not ready")
        }
    }

    /**
     * Called after Billing Client has loaded Skus
     * Time to check if VIP has been purchased
     */
    private fun checkIfVipHasBeenPurchased() {
        if (billingClient.isReady) {
            val results = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
            val list = results.purchasesList
            // Prevent crash relating to null list
            // was using list.isNotEmpty but still had crash reports
            // hopefully !list.isNullOrEmpty fixes the issue
            if (!list.isNullOrEmpty()) {
                list.forEach {
                    Timber.i("purchase")
                    if (it.purchaseState == Purchase.PurchaseState.PURCHASED && it.sku == skuCode) {
                        Timber.i("VIP is purchased")

                        // Update VIP status before _isVipPurchased otherwise bright orbs stay disabled after purchase
                        // TODO add a function here that remembers if they have made a purchase
                        // VipStatus.addVipStatus(context)
                        // CODE EXAMPLE
                        // fun addVipStatus(context: Context) {
                        //    val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

                        //    sharedPref.edit()
                        //            .putBoolean(PREF_KEY_IS_VIP, true)
                        //            .apply()
                        //}


                        // _onPurchase.value = true // Do this prematurely before purchase is acknowledge to update UI asap

                        if (!it.isAcknowledged) { // If not acknowledge, it means purchase is still processing
                            acknowledgePurchases(listOf<Purchase>(it))
                        }
                    } else if (it.purchaseState == Purchase.PurchaseState.PENDING && it.sku == skuCode) {
                        Timber.i("isPending")
                        isPending = true
                    }

                }
            } else {
                // TODO remove users VIP status
                // VipStatus.removeVip(context)
            }
        } else {
            Timber.e("BILLING CLIENT ERROR")
        }
    }

    /**
     * Called after if VIP has been purchased
     * Purchase must be acknowledged, otherwise refund is given in 3 days
     *
     * Purchase is now complete and user now has VIP
     */
    private fun acknowledgePurchases(purchases: List<Purchase>) {
        if (purchases.isNotEmpty()) {
            purchases.forEach {
                if (it.purchaseState == Purchase.PurchaseState.PURCHASED && it.sku == skuCode) {
                    Timber.i("VIP is purchased")
                    // Update VIP status before _isVipPurchased otherwise bright orbs stay disabled after purchase
                    // TODO update users vip status
                    // VipStatus.addVipStatus(context)
                    _onPurchase.value = true // Do this prematurely before purchase is acknowledge to update UI asap

                    val params = AcknowledgePurchaseParams
                            .newBuilder()
                            .setPurchaseToken(it.purchaseToken)
                            .build()

                    if (verifyValidPurchaseSignature(it)) {
                        billingClient.acknowledgePurchase(params) {
                            Timber.i("Acknowledge result: ${it.responseCode}")
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks is purchase is valid
     */
    private fun verifyValidPurchaseSignature(purchase: Purchase): Boolean {
        Timber.i("Verify is purchase is valid")
        val signedData = purchase.originalJson
        val signature = purchase.signature
        return try {
            ScrabbleSecurity.verifyPurchase(signedData, signature)
            true
        } catch (e: IOException) {
            Timber.e("Got an exception trying to validate a purchase: $e")
            // TODO remove users VIP
//            VipStatus.removeVip(context)
            false
        }
    }
}

private object ScrabbleSecurity {

    private const val KEY_FACTORY_ALGORITHM = "RSA"
    private const val SIGNATURE_ALGORITHM = "SHA1withRSA"

    // No idea where tf i got this code from
    // Most of this class is copy and pasted from what i remember
    private var baseKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnsyGloREB5ZLGlT6UgNpSSzHsgxFGlarHuPgPfrTzECmrOqt6ekSHBhCJCJGnMqPPG+f22cLPQUQMgIPbGLe822NwmEEYftvKlFFe/geDkSXU/L0a/h/iQU86w9NUX8lfgzInK4XlJg6P1qYS5kmISU2RMU1TA6ZkhP2nUDIyApqAOvjcuEzzCRcg7dldehE/7nmQ64gEBbbiXrbSDsjK+s2eHy2OWpDJRf+A9AnoGzoVejETcw8icGdy5tqY1tpEeVM8Mz6z9DvTRMsXUa+CAghdbYW7Urv4xV7VO/RLAKjvZGB1RLjdA8ykwusf7VnLTk9AV7tg/5sGep8SQuzcQIDAQAB"

    /**
     * Verifies that the data was signed with the given signature, and returns the verified
     * purchase.
     * @param base64PublicKey the base64-encoded public key to use for verifying.
     * @param signedData the signed JSON string (signed, not encrypted)
     * @param signature the signature for the data, signed with the private key
     * @throws IOException if encoding algorithm is not supported or key specification
     * is invalid
     */
    @Throws(IOException::class)
    fun verifyPurchase(signedData: String, signature: String?): Boolean {
        if (TextUtils.isEmpty(signedData) || TextUtils.isEmpty(baseKey)
                || TextUtils.isEmpty(signature)) {
            Timber.e("Purchase verification failed: missing data.")
            return false
        }
        val key: PublicKey = generatePublicKey(baseKey)
        return verify(key, signedData, signature)
    }

    /**
     * Generates a PublicKey instance from a string containing the Base64-encoded public key.
     *
     * @param encodedPublicKey Base64-encoded public key
     * @throws IOException if encoding algorithm is not supported or key specification
     * is invalid
     */
    @Throws(IOException::class)
    fun generatePublicKey(encodedPublicKey: String?): PublicKey {
        return try {
            val decodedKey: ByteArray = Base64.decode(encodedPublicKey, Base64.DEFAULT)
            val keyFactory: KeyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM)
            keyFactory.generatePublic(X509EncodedKeySpec(decodedKey))
        } catch (e: NoSuchAlgorithmException) { // "RSA" is guaranteed to be available.
            throw RuntimeException(e)
        } catch (e: InvalidKeySpecException) {
            val msg = "Invalid key specification: $e"
            Timber.e(msg)
            throw IOException(msg)
        }
    }

    /**
     * Verifies that the signature from the server matches the computed signature on the data.
     * Returns true if the data is correctly signed.
     *
     * @param publicKey public key associated with the developer account
     * @param signedData signed data from server
     * @param signature server signature
     * @return true if the data and signature match
     */
    fun verify(publicKey: PublicKey?, signedData: String, signature: String?): Boolean {
        val signatureBytes: ByteArray
        signatureBytes = try {
            Base64.decode(signature, Base64.DEFAULT)
        } catch (e: IllegalArgumentException) {
            Timber.e("Base64 decoding failed.")
            return false
        }
        try {
            val signatureAlgorithm: Signature = Signature.getInstance(SIGNATURE_ALGORITHM)
            signatureAlgorithm.initVerify(publicKey)
            signatureAlgorithm.update(signedData.toByteArray())
            if (!signatureAlgorithm.verify(signatureBytes)) {
                Timber.e("Signature verification failed.")
                return false
            }
            return true
        } catch (e: NoSuchAlgorithmException) { // "RSA" is guaranteed to be available.
            throw RuntimeException(e)
        } catch (e: InvalidKeyException) {
            Timber.e("Invalid key specification.")
        } catch (e: SignatureException) {
            Timber.e("Signature exception.")
        }
        return false
    }
}
