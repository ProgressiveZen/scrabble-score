package lood.corporatezen.scrabblescore

import android.content.Context
import androidx.preference.PreferenceManager

object Platinum {
    val key = "plat_key"

    fun isPlatinum(context: Context) : Boolean{
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        return pref.getBoolean(key, false)
    }

    fun setPlatinum(context: Context, boolean : Boolean){
        val pref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        pref.putBoolean(key, boolean).apply()
    }
}