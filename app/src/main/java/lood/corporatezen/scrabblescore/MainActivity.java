package lood.corporatezen.scrabblescore;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.example.android.scrabblescore.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.color.transparent;
import static com.example.android.scrabblescore.R.color.appblue;
import static com.example.android.scrabblescore.R.color.appgrey;
import static com.example.android.scrabblescore.R.color.dialogbg;
import static com.example.android.scrabblescore.R.drawable.blank;
import static com.example.android.scrabblescore.R.drawable.dlse;
import static com.example.android.scrabblescore.R.drawable.dwse;
import static com.example.android.scrabblescore.R.drawable.loader;
import static com.example.android.scrabblescore.R.drawable.tls;
import static com.example.android.scrabblescore.R.drawable.tlse;
import static com.example.android.scrabblescore.R.id.bonus;
import static com.example.android.scrabblescore.R.id.dls1;
import static com.example.android.scrabblescore.R.id.dls2;
import static com.example.android.scrabblescore.R.id.dls3;
import static com.example.android.scrabblescore.R.id.dls4;
import static com.example.android.scrabblescore.R.id.dls5;
import static com.example.android.scrabblescore.R.id.dls6;
import static com.example.android.scrabblescore.R.id.dls7;
import static com.example.android.scrabblescore.R.id.dls8;
import static com.example.android.scrabblescore.R.id.letter1;
import static com.example.android.scrabblescore.R.id.letter2;
import static com.example.android.scrabblescore.R.id.letter3;
import static com.example.android.scrabblescore.R.id.letter4;
import static com.example.android.scrabblescore.R.id.letter5;
import static com.example.android.scrabblescore.R.id.letter6;
import static com.example.android.scrabblescore.R.id.letter7;
import static com.example.android.scrabblescore.R.id.letter8;
import static com.example.android.scrabblescore.R.id.ll1;
import static com.example.android.scrabblescore.R.id.ll2;
import static com.example.android.scrabblescore.R.id.ll3;
import static com.example.android.scrabblescore.R.id.ll4;
import static com.example.android.scrabblescore.R.id.ll5;
import static com.example.android.scrabblescore.R.id.ll6;
import static com.example.android.scrabblescore.R.id.ll7;
import static com.example.android.scrabblescore.R.id.p1;
import static com.example.android.scrabblescore.R.id.p1total;
import static com.example.android.scrabblescore.R.id.p1words;
import static com.example.android.scrabblescore.R.id.p2;
import static com.example.android.scrabblescore.R.id.p2total;
import static com.example.android.scrabblescore.R.id.p2words;
import static com.example.android.scrabblescore.R.id.p4;
import static com.example.android.scrabblescore.R.id.player2nameinput;
import static com.example.android.scrabblescore.R.id.player3nameinput;
import static com.example.android.scrabblescore.R.id.playerScore1;
import static com.example.android.scrabblescore.R.id.playerScore2;
import static com.example.android.scrabblescore.R.id.playerScore3;
import static com.example.android.scrabblescore.R.id.playerScore4;
import static com.example.android.scrabblescore.R.id.tls1;
import static com.example.android.scrabblescore.R.id.tls2;
import static com.example.android.scrabblescore.R.id.tls3;
import static com.example.android.scrabblescore.R.id.tls4;
import static com.example.android.scrabblescore.R.id.tls5;
import static com.example.android.scrabblescore.R.id.tls6;
import static com.example.android.scrabblescore.R.id.tls7;
import static com.example.android.scrabblescore.R.id.tls8;
import static com.example.android.scrabblescore.R.id.winner;
import static com.example.android.scrabblescore.R.layout.endgame;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {
    final ViewGroup nullParent = null;
    boolean twspressed = false;
    boolean dwspressed = false;
    boolean pressed1 = false;
    boolean pressed2 = false;
    boolean pressed3 = false;
    boolean pressed4 = false;
    boolean pressed5 = false;
    boolean pressed6 = false;
    boolean pressed7 = false;
    boolean pressed8 = false;
    HashMap<String, Integer> alphabet = new HashMap<>();
    String playerWord = "";
    String le1 = " ";
    String le2 = " ";
    String le3 = " ";
    String le4 = " ";
    String le5 = " ";
    String le6 = " ";
    String le7 = " ";
    String le8 = " ";
    int total = 0;
    int wordMultipleBonus = 1;
    int l1bonus = 0;
    int l2bonus = 0;
    int l3bonus = 0;
    int l4bonus = 0;
    int l5bonus = 0;
    int l6bonus = 0;
    int l7bonus = 0;
    int l8bonus = 0;
    int lastTotal;
    int bonuspoints = 0;
    int wordTotal;
    int wordCount = 0;
    boolean dicoption = true;
    boolean dicoverride = true;
    boolean dicon = true;
    String lastWord;
    boolean tls1Pressed;
    boolean dls1Pressed;
    boolean tls2Pressed;
    boolean dls2Pressed;
    boolean tls3Pressed;
    boolean dls3Pressed;
    boolean tls4Pressed;
    boolean dls4Pressed;
    boolean tls5Pressed;
    boolean dls5Pressed;
    boolean tls6Pressed;
    boolean dls6Pressed;
    boolean tls7Pressed;
    boolean dls7Pressed;
    boolean tls8Pressed;
    boolean dls8Pressed;
    ArrayList<Player> players = new ArrayList<>();
    boolean yesno = false;
    boolean bonuspressed = false;
    boolean saved;
    int playerTurn = 0;
    ImageView loading;
    Context context;

    private InterstitialAd mInterstitialAd;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = getApplicationContext();
        alphabetCreator(alphabet);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        loading = findViewById(R.id.loading);
        saved = settings.getBoolean("saved", false);
        for (int i = 1; i < players.size(); i++) {
            players.get(i).setOver(settings, settings.getBoolean(i + "over", false));
        }
        dicon = settings.getBoolean("dicon", true);
        dicoption = settings.getBoolean("dicopton", true);
        dicoverride = settings.getBoolean("dicoverride", true);
        playerTurn = settings.getInt("playerTurn", playerTurn);
        wordCount = settings.getInt("wordcount", 0);
        int size = settings.getInt("list_size1", 0);
//        for (int v = 1; v < players.size(); v++) {
//            for (int i = 0; i < size; i++) {
//                players.get(v).getWordArray().add(settings.getString(v + "word" + i, null));
//                players.get(v).getWordScoreArray().add(settings.getInt(v + "wordscore" + i, 0));
//            }
//        }
        players.add(new Player("Player1", 0));
        players.add(new Player("Player2", 1));
//        turnNameChange(players.get(0));
//        if (players.get(0).getName().length() == 0) {
//            saved = false;
//        }
        //TESTING SAVED
        boolean saved = false;
        if (saved) {
            setup();
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            @SuppressLint("InflateParams") View promptView = layoutInflater.inflate(R.layout.areyousure, null);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            alertD.setCanceledOnTouchOutside(false);
            alertD.setCancelable(false);
            TextView playerName = promptView.findViewById(R.id.playerName);
            playerName.setText(getString(R.string.wb));
            TextView continueornew = promptView.findViewById(R.id.areyoursuretext);
            continueornew.setText(getString(R.string.wcg));
            Button yesbutton = promptView.findViewById(R.id.yes);
            yesbutton.setText(getString(R.string.ng));
            yesbutton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    dialogBox();
                    alertD.dismiss();
                }

            });
            Button nobutton = promptView.findViewById(R.id.no);
            nobutton.setText(getString(R.string.cg));
            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context;
                    context = getApplicationContext();
                    ImageView loadingPic = findViewById(R.id.loading);
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
                    loadingPic.startAnimation(animation);
                    alertD.dismiss();
                    if (players.get(0).isOver() && players.get(1).isOver() && players.size() == 2) {
                        endGameFormat();
                    } else if (players.get(0).isOver() && players.get(1).isOver() && players.get(2).isOver() && players.size() == 3) {
                        endGameFormat();
                    } else if (players.get(0).isOver() && players.get(1).isOver() && players.get(2).isOver() && players.get(3).isOver() && players.size() == 4) {
                        endGameFormat();
                    }
                }
            });
            alertD.setView(promptView);
            alertD.show();
        }
        if (savedInstanceState != null) {
            displayTotalWordScore();
            turnNameChange(players.get(playerTurn));
        } else if (!saved) {
            dialogBox();
            saved = true;
        }
        editor.putBoolean("saved", saved).apply();
        editor.commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        EditText soft = findViewById(R.id.userWord);
        soft.setSelected(false);
        soft.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (playerWord.length() < 1) {
                        exchangeTiles();
                    }
                }
                return false;
            }
        });
        soft.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                enterWord();
                Button addWord = findViewById(R.id.addWordButton);
                addWord.setText(getString(R.string.adw));
                if (playerWord.length() < 1) {
                    addWord.setText(getString(R.string.st));
                }
            }
        });
        ImageView loadingPic = findViewById(R.id.loading);
        loadingPic.setImageResource(loader);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        TextView player1namedisplayer = findViewById(R.id.player1name);
        TextView player2namedisplayer = findViewById(R.id.player2name);
        TextView player3namedisplayer = findViewById(R.id.player3name);
        TextView player4namedisplayer = findViewById(R.id.player4name);
        TextView player1scoredisplay = findViewById(R.id.playerScore1);
        TextView player2scoredisplay = findViewById(R.id.playerScore2);
        TextView player3scoredisplay = findViewById(playerScore3);
        TextView player4scoredisplay = findViewById(playerScore4);
        super.onSaveInstanceState(savedInstanceState);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setName(savedInstanceState.getString(i + "player"));
            players.get(i).setScore(savedInstanceState.getInt(i + "score"));
            players.get(i).setWordArray(savedInstanceState.getStringArrayList(i + "wordlist"));
            players.get(i).setWordScoreArray(savedInstanceState.getIntegerArrayList(i + "wordscorelist"));
        }
        player1namedisplayer.setText(players.get(0).getName());
        player2namedisplayer.setText(players.get(1).getName());
        player3namedisplayer.setText(players.get(2).getName());
        player4namedisplayer.setText(players.get(3).getName());
        player1scoredisplay.setText(String.valueOf(players.get(0).getScore()));
        player2scoredisplay.setText(String.valueOf(players.get(1).getScore()));
        player3scoredisplay.setText(String.valueOf(players.get(2).getScore()));
        player4scoredisplay.setText(String.valueOf(players.get(3).getScore()));
        playerTurn = savedInstanceState.getInt("playerTurn");
        displayTotalWordScore();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putString("player1", players.get(0).getName());
//        savedInstanceState.putString("player2", players.get(1).getName());
//        savedInstanceState.putString("player3", players.get(2).getName());
//        savedInstanceState.putString("player4", players.get(3).getName());
//        savedInstanceState.putInt("p1score", players.get(0).getScore());
//        savedInstanceState.putInt("p2score", players.get(1).getScore());
//        savedInstanceState.putInt("p3score", players.get(2).getScore());
//        savedInstanceState.putInt("p4score", players.get(3).getScore());
//        for (int i = 0; i < players.size(); i++) {
//            savedInstanceState.putStringArrayList(i + "wordlist", players.get(i).getWordArray());
//            savedInstanceState.putIntegerArrayList(i + "wordscorelist", players.get(i).getWordScoreArray());
//            savedInstanceState.putBoolean(i + "over", players.get(i).isOver());
//        }
//        savedInstanceState.putInt("playerTurn", playerTurn);
    }

    public void dialogBox() {
        reset();
        Context context;
        context = getApplicationContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.putBoolean("saved", true).apply();
        editor.commit();
        wordCount = 0;
        playerTurn = 0;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View promptView = layoutInflater.inflate(R.layout.dialoglayout, null);
        final AlertDialog alertD = new AlertDialog.Builder(this).create();
        alertD.setCanceledOnTouchOutside(false);
        alertD.setCancelable(false);
        Button startbutton = promptView.findViewById(R.id.start);
        ImageButton plus = promptView.findViewById(R.id.plusbutton);
        ImageButton minus = promptView.findViewById(R.id.minusbutton);
        final TextView nop = promptView.findViewById(R.id.nop);
        final EditText player1userinput;
        final EditText player2userinput;
        final EditText player3userinput;
        final EditText player4userinput;
        player1userinput = promptView.findViewById(R.id.player1nameinput);
        player2userinput = promptView.findViewById(R.id.player2nameinput);
        player3userinput = promptView.findViewById(R.id.player3nameinput);
        player4userinput = promptView.findViewById(R.id.player4nameinput);
        final TextView player31 = promptView.findViewById(R.id.player3name);
        final TextView player41 = promptView.findViewById(R.id.player4name);
        final TextView player32 = findViewById(playerScore3);
        final TextView player42 = findViewById(R.id.player4name);
        final TextView player33 = findViewById(R.id.player3name);
        final TextView player43 = findViewById(playerScore4);
        player4userinput.setVisibility(View.GONE);
        player41.setVisibility(View.GONE);
        player42.setVisibility(View.GONE);
        player43.setVisibility(View.GONE);
        player3userinput.setVisibility(View.GONE);
        player31.setVisibility(View.GONE);
        player32.setVisibility(View.GONE);
        player33.setVisibility(View.GONE);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (players.size() == 2) {
                    players.add(new Player("Player3", 2));
                    nop.setText(getString(R.string.ThreePlayer));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    player3userinput.setVisibility(View.VISIBLE);
                    player31.setVisibility(View.VISIBLE);
                    player32.setVisibility(View.VISIBLE);
                    player33.setVisibility(View.VISIBLE);
                    EditText player2userinput = alertD.findViewById(player2nameinput);
                    if (player2userinput != null) {
                        player2userinput.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                    }
                    EditText player3userinput = alertD.findViewById(player3nameinput);
                    if (player3userinput != null) {
                        player3userinput.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    }
                } else if (players.size() == 3) {
                    nop.setText(getString(R.string.FourPlayer));
                    players.add(new Player("Player4", 3));
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    player4userinput.setVisibility(View.VISIBLE);
                    player41.setVisibility(View.VISIBLE);
                    player42.setVisibility(View.VISIBLE);
                    player43.setVisibility(View.VISIBLE);
                    EditText player3userinput = alertD.findViewById(player3nameinput);
                    if (player3userinput != null) {
                        player3userinput.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                    }
                }
            }

        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (players.size() == 4) {
                    players.remove(3);
                    player4userinput.setVisibility(View.GONE);
                    player41.setVisibility(View.GONE);
                    player42.setVisibility(View.GONE);
                    player43.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    nop.setText(getString(R.string.ThreePlayer));
                    EditText player3userinput = alertD.findViewById(player3nameinput);
                    if (player3userinput != null) {
                        player3userinput.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    }
                } else if (players.size() == 3) {
                    players.remove(2);
                    player3userinput.setVisibility(View.GONE);
                    player31.setVisibility(View.GONE);
                    player32.setVisibility(View.GONE);
                    player33.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    nop.setText(getString(R.string.TwoPlayer));
                    EditText player2userinput = alertD.findViewById(player2nameinput);
                    if (player2userinput != null) {
                        player2userinput.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    }
                }
            }

        });
        startbutton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Context context;
                                               context = getApplicationContext();
                                               if (dicon) {
                                                   loading.setVisibility(View.VISIBLE);
                                                   Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
                                                   loading.startAnimation(animation);
                                               }
                                               if (!dicon) {
                                                   dicoverride = true;
                                               }
                                               players.clear();
                                               if (player1userinput != null) {
                                                   players.add(new Player(player1userinput.getText().toString(), 0));
                                                   System.out.println("WTF" + player1userinput.getText().toString());
                                               }
                                               if (player2userinput != null) {
                                                   players.add(new Player(player2userinput.getText().toString(), 1));
                                               }
                                               if (player3userinput != null && players.size() > 2) {
                                                   players.add(new Player(player3userinput.getText().toString(), 2));
                                               }
                                               if (player4userinput != null && players.size() > 3) {
                                                   players.add(new Player(player4userinput.getText().toString(), 3));
                                               }
                                               if (players.get(0).getName().length() == 0) {
                                                   context = getApplicationContext();
                                                   Toast toast = Toast.makeText(context, "Player 1 please enter your name", Toast.LENGTH_SHORT);
                                                   toast.show();
                                               } else if (players.get(1).getName().length() == 0) {
                                                   context = getApplicationContext();
                                                   Toast toast = Toast.makeText(context, "Player 2 please enter your name", Toast.LENGTH_SHORT);
                                                   toast.show();
                                               } else if (players.size() > 2 && players.get(2).getName().length() == 0) {
                                                   context = getApplicationContext();
                                                   Toast toast = Toast.makeText(context, "Player 3 please enter your name", Toast.LENGTH_SHORT);
                                                   toast.show();
                                               } else if (players.size() > 3 && players.get(3).getName().length() == 0) {
                                                   context = getApplicationContext();
                                                   Toast toast = Toast.makeText(context, "Player 4 please enter your name", Toast.LENGTH_SHORT);
                                                   toast.show();
                                               }
                                               TextView player1namedisplayer = findViewById(R.id.player1name);
                                               TextView player2namedisplayer = findViewById(R.id.player2name);
                                               TextView player3namedisplayer = findViewById(R.id.player3name);
                                               TextView player4namedisplayer = findViewById(R.id.player4name);
                                               TextView player3scoredisplayer = findViewById(R.id.playerScore3);
                                               TextView player4scoredisplayer = findViewById(R.id.playerScore4);
                                               TextView player1scoreDisplay = findViewById(R.id.playerScore1);
                                               TextView player2scoreDisplay = findViewById(R.id.playerScore2);
                                               TextView player3scoreDisplay = findViewById(playerScore3);
                                               TextView player4scoreDisplay = findViewById(playerScore4);
                                               player1namedisplayer.setText(players.get(0).getName());
                                               player2namedisplayer.setText(players.get(1).getName());
                                               player1scoreDisplay.setText(String.valueOf(players.get(0).getScore()));
                                               player2scoreDisplay.setText(String.valueOf(players.get(1).getScore()));
                                               if (players.size() > 2) {
                                                   player3namedisplayer.setText(players.get(2).getName());
                                                   player3scoreDisplay.setText(String.valueOf(players.get(2).getScore()));
                                               }
                                               if (players.size() > 3) {
                                                   player4namedisplayer.setText(players.get(3).getName());
                                                   player4scoreDisplay.setText(String.valueOf(players.get(3).getScore()));
                                               }
                                               loadScoreObservers();
                                               context = getApplicationContext();
                                               SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
                                               SharedPreferences.Editor editor = settings.edit();
                                               for (int i = 0; i < players.size(); i++) {
                                                   editor.putString(i + "name", players.get(i).getName()).apply();
                                               }
                                               player1namedisplayer.setBackgroundColor(ContextCompat.getColor(context, appblue));
                                               player1scoreDisplay.setBackgroundColor(ContextCompat.getColor(context, appblue));
                                               player2namedisplayer.setBackgroundColor(ContextCompat.getColor(context, transparent));
                                               player2scoreDisplay.setBackgroundColor(ContextCompat.getColor(context, transparent));
                                               player3namedisplayer.setBackgroundColor(ContextCompat.getColor(context, transparent));
                                               player3scoredisplayer.setBackgroundColor(ContextCompat.getColor(context, transparent));
                                               player4namedisplayer.setBackgroundColor(ContextCompat.getColor(context, transparent));
                                               player4scoredisplayer.setBackgroundColor(ContextCompat.getColor(context, transparent));
                                               if (dicon) {
                                                   loading.setImageResource(loader);
                                                   Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
                                                   loading.startAnimation(animation);
                                               } else {
                                                   loading.clearAnimation();
                                                   loading.setVisibility(View.GONE);
                                               }
                                               alertD.dismiss();
                                           }
                                       }

        );
        alertD.setView(promptView);
        alertD.show();
    }

    public void loadScoreObservers() {
        players.get(0).score.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                TextView playerOneName = findViewById(playerScore1);
                playerOneName.setText(s.toString());
            }
        });
        players.get(1).score.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                TextView playerOneName = findViewById(playerScore2);
                playerOneName.setText(s.toString());
            }
        });
        if (players.size() > 2) {
            players.get(2).score.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer s) {
                    TextView playerOneName = findViewById(playerScore3);
                    playerOneName.setText(s.toString());
                }
            });
        }
        if (players.size() > 3) {
            players.get(3).score.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer s) {
                    TextView playerOneName = findViewById(playerScore4);
                    playerOneName.setText(s.toString());
                }
            });
        }
    }

    public void alphabetCreator(HashMap<String, Integer> alphabet) {
        alphabet.put("a", 1);
        alphabet.put("b", 3);
        alphabet.put("c", 3);
        alphabet.put("d", 2);
        alphabet.put("e", 1);
        alphabet.put("f", 4);
        alphabet.put("g", 2);
        alphabet.put("h", 4);
        alphabet.put("i", 1);
        alphabet.put("j", 8);
        alphabet.put("k", 5);
        alphabet.put("l", 1);
        alphabet.put("m", 3);
        alphabet.put("n", 1);
        alphabet.put("o", 1);
        alphabet.put("p", 3);
        alphabet.put("q", 10);
        alphabet.put("r", 1);
        alphabet.put("s", 1);
        alphabet.put("t", 1);
        alphabet.put("u", 1);
        alphabet.put("v", 4);
        alphabet.put("w", 4);
        alphabet.put("x", 8);
        alphabet.put("y", 4);
        alphabet.put("z", 10);
        alphabet.put("blank", 0);
    }

    public void enterWord() {
        wordMultipleBonus = 1;
        pressed1 = false;
        pressed2 = false;
        pressed3 = false;
        pressed4 = false;
        pressed5 = false;
        pressed6 = false;
        pressed7 = false;
        pressed8 = false;
        ImageButton twsreset = findViewById(R.id.tws);
        ImageButton dwsreset = findViewById(R.id.dws);
        ImageButton tls1reset = findViewById(R.id.tls1);
        ImageButton dls1reset = findViewById(R.id.dls1);
        ImageButton tls2reset = findViewById(R.id.tls2);
        ImageButton dls2reset = findViewById(R.id.dls2);
        ImageButton tls3reset = findViewById(R.id.tls3);
        ImageButton dls3reset = findViewById(R.id.dls3);
        ImageButton tls4reset = findViewById(R.id.tls4);
        ImageButton dls4reset = findViewById(R.id.dls4);
        ImageButton tls5reset = findViewById(R.id.tls5);
        ImageButton dls5reset = findViewById(R.id.dls5);
        ImageButton tls6reset = findViewById(R.id.tls6);
        ImageButton dls6reset = findViewById(R.id.dls6);
        ImageButton tls7reset = findViewById(tls7);
        ImageButton dls7reset = findViewById(dls7);
        ImageButton tls8reset = findViewById(tls8);
        ImageButton dls8reset = findViewById(dls8);
        tls1reset.setClickable(true);
        tls2reset.setClickable(true);
        tls3reset.setClickable(true);
        tls4reset.setClickable(true);
        tls5reset.setClickable(true);
        tls6reset.setClickable(true);
        tls7reset.setClickable(true);
        tls8reset.setClickable(true);
        dls1reset.setClickable(true);
        dls2reset.setClickable(true);
        dls3reset.setClickable(true);
        dls4reset.setClickable(true);
        dls5reset.setClickable(true);
        dls6reset.setClickable(true);
        dls7reset.setClickable(true);
        dls8reset.setClickable(true);
        ImageButton bonus = findViewById(R.id.bonus);
        ImageView l1 = findViewById(letter1);
        ImageView l2 = findViewById(letter2);
        ImageView l3 = findViewById(letter3);
        ImageView l4 = findViewById(letter4);
        ImageView l5 = findViewById(letter5);
        ImageView l6 = findViewById(letter6);
        ImageView l7 = findViewById(letter7);
        ImageView l8 = findViewById(letter8);
        EditText eText = findViewById(R.id.userWord);
        playerWord = eText.getText().toString();
        if (playerWord.length() == 0) {
            l1.setImageResource(blank);
            l2.setImageResource(blank);
            l3.setImageResource(blank);
            l4.setImageResource(blank);
            l5.setImageResource(blank);
            l6.setImageResource(blank);
            l7.setImageResource(blank);
            l8.setImageResource(blank);
            tls1reset.setImageResource(tls);
            tls2reset.setImageResource(tls);
            tls3reset.setImageResource(tls);
            tls4reset.setImageResource(tls);
            dls1reset.setImageResource(R.drawable.dls);
            dls2reset.setImageResource(R.drawable.dls);
            dls3reset.setImageResource(R.drawable.dls);
            dls4reset.setImageResource(R.drawable.dls);
            tls5reset.setImageResource(tls);
            tls6reset.setImageResource(tls);
            tls7reset.setImageResource(tls);
            tls8reset.setImageResource(tls);
            dls5reset.setImageResource(R.drawable.dls);
            dls6reset.setImageResource(R.drawable.dls);
            dls7reset.setImageResource(R.drawable.dls);
            dls8reset.setImageResource(R.drawable.dls);
            twsreset.setImageResource(R.drawable.tws);
            dwsreset.setImageResource(R.drawable.dws);
            total = 0;
        } else {
            try {
                le1 = "" + alphabet.get("" + playerWord.charAt(playerWord.length() - 1));
                yesno = false;
                playerWord = playerWord.toLowerCase();
                if (playerWord.length() > 0) {
                    try {
                        le1 = playerWord.substring(0, 1);
                        dls1reset.setImageResource(R.drawable.dlse);
                        tls1reset.setImageResource(R.drawable.tlse);
                        total = alphabet.get(le1);
                    } catch (StringIndexOutOfBoundsException e) {
                        le1 = "blank";
                        dls1reset.setImageResource(R.drawable.dls);
                        tls1reset.setImageResource(R.drawable.tls);
                        dls1reset.setClickable(false);
                        tls1reset.setClickable(false);
                    }
                    try {
                        le2 = playerWord.substring(1, 2);
                        dls2reset.setImageResource(R.drawable.dlse);
                        tls2reset.setImageResource(R.drawable.tlse);
                        twsreset.setImageResource(R.drawable.twse);
                        dwsreset.setImageResource(R.drawable.dwse);
                        total = alphabet.get(le1) + alphabet.get(le2);
                    } catch (StringIndexOutOfBoundsException e) {
                        le2 = "blank";
                        dls2reset.setImageResource(R.drawable.dls);
                        tls2reset.setImageResource(R.drawable.tls);
                        dls2reset.setClickable(false);
                        tls2reset.setClickable(false);
                    }
                    try {
                        le3 = playerWord.substring(2, 3);
                        dls3reset.setImageResource(R.drawable.dlse);
                        tls3reset.setImageResource(R.drawable.tlse);
                        total = alphabet.get(le1) + alphabet.get(le2) + alphabet.get(le3);
                    } catch (StringIndexOutOfBoundsException e) {
                        le3 = "blank";
                        dls3reset.setImageResource(R.drawable.dls);
                        tls3reset.setImageResource(R.drawable.tls);
                        dls3reset.setClickable(false);
                        tls3reset.setClickable(false);
                    }
                    try {
                        le4 = playerWord.substring(3, 4);
                        dls4reset.setImageResource(R.drawable.dlse);
                        tls4reset.setImageResource(R.drawable.tlse);
                        total = alphabet.get(le1) + alphabet.get(le2) + alphabet.get(le3) + alphabet.get(le4);
                    } catch (StringIndexOutOfBoundsException e) {
                        le4 = "blank";
                        dls4reset.setImageResource(R.drawable.dls);
                        tls4reset.setImageResource(R.drawable.tls);
                        dls4reset.setClickable(false);
                        tls4reset.setClickable(false);
                    }
                    try {
                        le5 = playerWord.substring(4, 5);
                        dls5reset.setImageResource(R.drawable.dlse);
                        tls5reset.setImageResource(R.drawable.tlse);
                        total = alphabet.get(le1) + alphabet.get(le2) + alphabet.get(le3) + alphabet.get(le4) + alphabet.get(le5);
                    } catch (StringIndexOutOfBoundsException e) {
                        le5 = "blank";
                        dls5reset.setImageResource(R.drawable.dls);
                        tls5reset.setImageResource(R.drawable.tls);
                        dls5reset.setClickable(false);
                        tls5reset.setClickable(false);
                    }
                    try {
                        le6 = playerWord.substring(5, 6);
                        dls6reset.setImageResource(R.drawable.dlse);
                        tls6reset.setImageResource(R.drawable.tlse);
                        total = alphabet.get(le1) + alphabet.get(le2) + alphabet.get(le3) + alphabet.get(le4) + alphabet.get(le5) + alphabet.get(le6);
                    } catch (StringIndexOutOfBoundsException e) {
                        le6 = "blank";
                        dls6reset.setImageResource(R.drawable.dls);
                        tls6reset.setImageResource(R.drawable.tls);
                        dls6reset.setClickable(false);
                        tls6reset.setClickable(false);
                    }
                    try {
                        le7 = playerWord.substring(6, 7);
                        dls7reset.setImageResource(R.drawable.dlse);
                        tls7reset.setImageResource(R.drawable.tlse);
                        tls6reset.setImageResource(R.drawable.tlse);
                        total = alphabet.get(le1) + alphabet.get(le2) + alphabet.get(le3) + alphabet.get(le4) + alphabet.get(le5) + alphabet.get(le6) + alphabet.get(le7);
                        bonus.setImageResource(R.drawable.fifty);
                        bonus.setClickable(false);
                    } catch (StringIndexOutOfBoundsException e) {
                        le7 = "blank";
                        dls7reset.setImageResource(R.drawable.dls);
                        tls7reset.setImageResource(R.drawable.tls);
                        dls7reset.setClickable(false);
                        tls7reset.setClickable(false);
                    }
                    try {
                        le8 = playerWord.substring(7, 8);
                        dls8reset.setImageResource(R.drawable.dlse);
                        tls8reset.setImageResource(R.drawable.tlse);
                        bonus.setImageResource(R.drawable.fiftye);
                        bonus.setClickable(true);
                        total = alphabet.get(le1) + alphabet.get(le2) + alphabet.get(le3) + alphabet.get(le4) + alphabet.get(le5) + alphabet.get(le6) + alphabet.get(le7) + alphabet.get(le8);
                    } catch (StringIndexOutOfBoundsException e) {
                        le8 = "blank";
                        dls8reset.setImageResource(R.drawable.dls);
                        tls8reset.setImageResource(R.drawable.tls);
                        dls8reset.setClickable(false);
                        tls8reset.setClickable(false);
                        bonus.setClickable(false);
                    }
                    l1.setImageResource(l1.getContext().getResources().getIdentifier(le1, "drawable", l1.getContext().getPackageName()));
                    l2.setImageResource(l2.getContext().getResources().getIdentifier(le2, "drawable", l2.getContext().getPackageName()));
                    l3.setImageResource(l3.getContext().getResources().getIdentifier(le3, "drawable", l3.getContext().getPackageName()));
                    l4.setImageResource(l4.getContext().getResources().getIdentifier(le4, "drawable", l4.getContext().getPackageName()));
                    l5.setImageResource(l5.getContext().getResources().getIdentifier(le5, "drawable", l5.getContext().getPackageName()));
                    l6.setImageResource(l6.getContext().getResources().getIdentifier(le6, "drawable", l6.getContext().getPackageName()));
                    l7.setImageResource(l7.getContext().getResources().getIdentifier(le7, "drawable", l7.getContext().getPackageName()));
                    l8.setImageResource(l8.getContext().getResources().getIdentifier(le8, "drawable", l8.getContext().getPackageName()));
                }
                if (playerWord.length() > 8) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Maximum 8 characters", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (!isAlpha(playerWord)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Word must contain only letters.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (dicon) {
                    txtLooker(playerWord);
                }
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(getApplicationContext(), "No spaces allowed.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        displayTotalWordScore();
    }

    public void twsOnClick(View v) {
        ImageButton twsbutton1 = findViewById(R.id.tws);
        ImageButton dwsbutton1 = findViewById(R.id.dws);
        if (playerWord.length() != 0 && !le1.equals(" ")) {
            if (!twspressed) {
                twsbutton1.setImageResource(R.drawable.twss);
                dwsbutton1.setImageResource(dwse);
                dwspressed = false;
                twspressed = true;
                wordMultipleBonus = 3;
            } else if (twspressed) {
                twsbutton1.setImageResource(R.drawable.twse);
                twspressed = false;
                wordMultipleBonus = 1;
            }
        }
        displayTotalWordScore();
    }

    public void dwsOnClick(View v) {
        ImageButton twsbutton1 = findViewById(R.id.tws);
        ImageButton dwsbutton1 = findViewById(R.id.dws);
        if (playerWord.length() != 0 && !le1.equals(" ")) {
            if (!dwspressed) {
                twsbutton1.setImageResource(R.drawable.twse);
                dwsbutton1.setImageResource(R.drawable.dwss);
                twspressed = false;
                dwspressed = true;
                wordMultipleBonus = 2;
            } else if (dwspressed) {
                dwsbutton1.setImageResource(dwse);
                dwspressed = false;
                wordMultipleBonus = 1;
            }
        }
        displayTotalWordScore();
    }

    public void setTls1Pressed(View v) {
        ImageButton tlsbutton1 = findViewById(tls1);
        ImageButton dlsbutton1 = findViewById(dls1);
        if (!le1.equals(" ") && !pressed1 && playerWord.length() != 0) {
            if (!tls1Pressed) {
                tlsbutton1.setImageResource(R.drawable.tlss);
                dlsbutton1.setImageResource(dlse);
                tls1Pressed = true;
                dls1Pressed = false;
                l1bonus = (alphabet.get(le1) * 2);
            } else if (tls1Pressed) {
                tlsbutton1.setImageResource(tlse);
                tls1Pressed = false;
                l1bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls1Pressed(View v) {
        ImageButton tlsbutton1 = findViewById(tls1);
        ImageButton dlsbutton1 = findViewById(dls1);
        if (!le1.equals(" ") && !pressed1 && playerWord.length() != 0) {
            if (!dls1Pressed) {
                dlsbutton1.setImageResource(R.drawable.dlss);
                tlsbutton1.setImageResource(tlse);
                dls1Pressed = true;
                tls1Pressed = false;
                l1bonus = alphabet.get(le1);
            } else if (dls1Pressed) {
                dlsbutton1.setImageResource(dlse);
                dls1Pressed = false;
                l1bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setTls2Pressed(View v) {
        ImageButton tlsbutton2 = findViewById(tls2);
        ImageButton dlsbutton2 = findViewById(dls2);
        if (!le2.equals(" ") && !pressed2 && playerWord.length() != 0) {
            if (!tls2Pressed) {
                tlsbutton2.setImageResource(R.drawable.tlss);
                dlsbutton2.setImageResource(dlse);
                tls2Pressed = true;
                dls2Pressed = false;
                l2bonus = (alphabet.get(le2) * 2);
            } else if (tls2Pressed) {
                tlsbutton2.setImageResource(tlse);
                tls2Pressed = false;
                l2bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls2Pressed(View v) {
        ImageButton tlsbutton2 = findViewById(tls2);
        ImageButton dlsbutton2 = findViewById(dls2);
        if (!le2.equals(" ") && !pressed2 && playerWord.length() != 0) {
            if (!dls2Pressed) {
                dlsbutton2.setImageResource(R.drawable.dlss);
                tlsbutton2.setImageResource(tlse);
                dls2Pressed = true;
                tls2Pressed = false;
                l2bonus = alphabet.get(le2);
            } else if (dls2Pressed && !le2.equals(" ") && !pressed2) {
                dlsbutton2.setImageResource(dlse);
                dls2Pressed = false;
                l2bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setTls3Pressed(View v) {
        ImageButton tlsbutton3 = findViewById(tls3);
        ImageButton dlsbutton3 = findViewById(dls3);
        if (!le3.equals(" ") && !pressed3 && playerWord.length() != 0) {
            if (!tls3Pressed) {
                tlsbutton3.setImageResource(R.drawable.tlss);
                dlsbutton3.setImageResource(dlse);
                tls3Pressed = true;
                dls3Pressed = false;
                l3bonus = (alphabet.get(le3) * 2);
            } else if (tls3Pressed) {
                tlsbutton3.setImageResource(tlse);
                tls3Pressed = false;
                l3bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls3Pressed(View v) {
        ImageButton tlsbutton3 = findViewById(tls3);
        ImageButton dlsbutton3 = findViewById(dls3);
        if (!le3.equals(" ") && !pressed3 && playerWord.length() != 0) {
            if (!dls3Pressed) {
                dlsbutton3.setImageResource(R.drawable.dlss);
                tlsbutton3.setImageResource(tlse);
                dls3Pressed = true;
                tls3Pressed = false;
                l3bonus = alphabet.get(le3);
            } else if (dls3Pressed) {
                dlsbutton3.setImageResource(dlse);
                dls3Pressed = false;
                l3bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setTls4Pressed(View v) {
        ImageButton tlsbutton4 = findViewById(tls4);
        ImageButton dlsbutton4 = findViewById(dls4);
        if (!le4.equals(" ") && !pressed4 && playerWord.length() != 0) {
            if (!tls4Pressed) {
                tlsbutton4.setImageResource(R.drawable.tlss);
                dlsbutton4.setImageResource(dlse);
                tls4Pressed = true;
                dls4Pressed = false;
                l4bonus = (alphabet.get(le4) * 2);
            } else if (tls4Pressed) {
                tlsbutton4.setImageResource(tlse);
                tls4Pressed = false;
                l4bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls4Pressed(View v) {
        ImageButton tlsbutton4 = findViewById(tls4);
        ImageButton dlsbutton4 = findViewById(dls4);
        if (!le4.equals(" ") && !pressed4 && playerWord.length() != 0) {
            if (!dls4Pressed) {
                dlsbutton4.setImageResource(R.drawable.dlss);
                tlsbutton4.setImageResource(tlse);
                dls4Pressed = true;
                tls4Pressed = false;
                l4bonus = alphabet.get(le4);
            } else if (dls4Pressed) {
                dlsbutton4.setImageResource(dlse);
                dls4Pressed = false;
                l4bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setTls5Pressed(View v) {
        ImageButton tlsbutton5 = findViewById(tls5);
        ImageButton dlsbutton5 = findViewById(dls5);
        if (!le5.equals(" ") && !pressed5 && playerWord.length() != 0) {
            if (!tls5Pressed) {
                tlsbutton5.setImageResource(R.drawable.tlss);
                dlsbutton5.setImageResource(dlse);
                tls5Pressed = true;
                dls5Pressed = false;
                l5bonus = (alphabet.get(le5) * 2);
            } else if (tls5Pressed) {
                tlsbutton5.setImageResource(tlse);
                tls5Pressed = false;
                l5bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls5Pressed(View v) {
        ImageButton tlsbutton5 = findViewById(tls5);
        ImageButton dlsbutton5 = findViewById(dls5);
        if (!le5.equals(" ") && !pressed5 && playerWord.length() != 0) {
            if (!dls5Pressed) {
                dlsbutton5.setImageResource(R.drawable.dlss);
                tlsbutton5.setImageResource(tlse);
                dls5Pressed = true;
                tls5Pressed = false;
                l5bonus = alphabet.get(le5);
            } else if (dls5Pressed) {
                dlsbutton5.setImageResource(dlse);
                dls5Pressed = false;
                l5bonus = 0;
            }
        }
        displayTotalWordScore();
    }


    public void setTls6Pressed(View v) {
        ImageButton tlsbutton6 = findViewById(tls6);
        ImageButton dlsbutton6 = findViewById(dls6);
        if (!le6.equals(" ") && !pressed6 && playerWord.length() != 0) {
            if (!tls6Pressed) {
                tlsbutton6.setImageResource(R.drawable.tlss);
                dlsbutton6.setImageResource(dlse);
                tls6Pressed = true;
                dls6Pressed = false;
                l6bonus = (alphabet.get(le6) * 2);
            } else if (tls6Pressed) {
                tlsbutton6.setImageResource(tlse);
                tls6Pressed = false;
                l6bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls6Pressed(View v) {
        ImageButton tlsbutton6 = findViewById(tls6);
        ImageButton dlsbutton6 = findViewById(dls6);
        if (!le6.equals(" ") && !pressed6 && playerWord.length() != 0) {
            if (!dls6Pressed) {
                dlsbutton6.setImageResource(R.drawable.dlss);
                tlsbutton6.setImageResource(tlse);
                dls6Pressed = true;
                tls6Pressed = false;
                l6bonus = alphabet.get(le6);
            } else if (dls6Pressed) {
                dlsbutton6.setImageResource(dlse);
                dls6Pressed = false;
                l6bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setTls7Pressed(View v) {
        ImageButton tlsbutton7 = findViewById(tls7);
        ImageButton dlsbutton7 = findViewById(dls7);
        if (!le7.equals(" ") && !pressed7 && playerWord.length() != 0) {
            if (!tls7Pressed) {
                tlsbutton7.setImageResource(R.drawable.tlss);
                dlsbutton7.setImageResource(dlse);
                tls7Pressed = true;
                dls7Pressed = false;
                l7bonus = (alphabet.get(le7) * 2);
            } else if (tls7Pressed) {
                tlsbutton7.setImageResource(tlse);
                tls7Pressed = false;
                l7bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls7Pressed(View v) {
        ImageButton tlsbutton7 = findViewById(tls7);
        ImageButton dlsbutton7 = findViewById(dls7);
        if (!le7.equals(" ") && !pressed7 && playerWord.length() != 0) {
            if (!dls7Pressed) {
                dlsbutton7.setImageResource(R.drawable.dlss);
                tlsbutton7.setImageResource(tlse);
                dls7Pressed = true;
                tls7Pressed = false;
                l7bonus = alphabet.get(le7);
            } else if (dls6Pressed) {
                dlsbutton7.setImageResource(dlse);
                dls7Pressed = false;
                l7bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setTls8Pressed(View v) {
        ImageButton tlsbutton8 = findViewById(tls8);
        ImageButton dlsbutton8 = findViewById(dls8);
        if (!le8.equals(" ") && !pressed8 && playerWord.length() != 0) {
            if (!tls8Pressed) {
                tlsbutton8.setImageResource(R.drawable.tlss);
                dlsbutton8.setImageResource(dlse);
                tls8Pressed = true;
                dls8Pressed = false;
                l8bonus = (alphabet.get(le8) * 2);
            } else if (tls8Pressed) {
                tlsbutton8.setImageResource(tlse);
                tls8Pressed = false;
                l8bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void setDls8Pressed(View v) {
        ImageButton tlsbutton8 = findViewById(tls8);
        ImageButton dlsbutton8 = findViewById(dls8);
        if (!le8.equals(" ") && !pressed8 && playerWord.length() != 0) {
            if (!dls8Pressed) {
                dlsbutton8.setImageResource(R.drawable.dlss);
                tlsbutton8.setImageResource(tlse);
                dls8Pressed = true;
                tls8Pressed = false;
                l8bonus = alphabet.get(le8);
            } else if (dls8Pressed) {
                dlsbutton8.setImageResource(dlse);
                dls8Pressed = false;
                l8bonus = 0;
            }
        }
        displayTotalWordScore();
    }

    public void displayTotalWordScore() {
        TextView totalWordScore = findViewById(R.id.totalWordScore);
        TextView wordScoreDisplay = findViewById(R.id.wordScore);
        wordTotal = (((total + l1bonus + l2bonus + l3bonus + l4bonus + l5bonus + l6bonus + l7bonus + l8bonus) * wordMultipleBonus) + bonuspoints);
        totalWordScore.setText(getString(R.string.tws2, wordTotal));
        wordScoreDisplay.setText(getString(R.string.ws2, total));
    }

    //stores turn and change player
    public void turnNameChange(Player player) {
        Context context;
        context = getApplicationContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        player.saveTurn(settings, playerWord, wordTotal);
        editor.putInt("playerTurn", playerTurn);
        editor.putInt("wordcount", wordCount);
        editor.putInt("list_size1", player.getWordScoreArray().size());
        editor.apply();
    }

    public void bonuspoints(View v) {
        if (playerWord.length() == 7 && !bonuspressed && players.get(1).getScore() == 0 && players.get(2).getScore() == 0) {
            ImageView bonus = findViewById(R.id.bonus);
            bonus.setImageResource(R.drawable.fiftys);
            bonuspressed = true;
            bonuspoints = 50;
        } else if (playerWord.length() == 7 && bonuspressed && players.get(1).getScore() == 0 && players.get(2).getScore() == 0) {
            ImageView bonus = findViewById(R.id.bonus);
            bonus.setImageResource(R.drawable.fiftye);
            bonuspoints = 0;
            bonuspressed = false;
        }
        if (playerWord.length() == 8 && !bonuspressed) {
            ImageView bonus = findViewById(R.id.bonus);
            bonus.setImageResource(R.drawable.fiftys);
            bonuspressed = true;
            bonuspoints = 50;
        } else if (playerWord.length() == 8 && bonuspressed) {
            ImageView bonus = findViewById(R.id.bonus);
            bonus.setImageResource(R.drawable.fiftye);
            bonuspoints = 0;
            bonuspressed = false;
        }
        displayTotalWordScore();
    }


    public void addToScore(View v) {
        if (playerWord.length() < 1) {
            exchangeTiles();
        }
        if (yesno || !dicon) {
            addToScore2();
        } else if (dicoverride && playerWord.length() > 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View promptView = layoutInflater.inflate(R.layout.override, nullParent);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            alertD.setCanceledOnTouchOutside(false);
            alertD.setCancelable(false);
            TextView playerName = promptView.findViewById(R.id.playerName);
            playerName.setText(players.get(playerTurn).getName());
            Button yesbutton = promptView.findViewById(R.id.yes);
            yesbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToScore2();
                    alertD.dismiss();
                }

            });
            Button nobutton = promptView.findViewById(R.id.no);
            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertD.dismiss();
                }

            });
            alertD.setView(promptView);
            alertD.show();
        }
    }

    public void addToScore2() {
        if (!le1.equals(" ")) {
            players.get(playerTurn).setScore(wordTotal);
            players.get(playerTurn).addWord(playerWord, wordTotal);
            turnChangeForwards();
            reset();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter word", Toast.LENGTH_SHORT);
            toast.show();
        }
        turnNameChange(players.get(playerTurn));
        wordCount++;
        if (wordCount == 5) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View promptView = layoutInflater.inflate(R.layout.scrabblescoreplatinum, nullParent);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            alertD.setCanceledOnTouchOutside(false);
            alertD.setCancelable(false);
            Button nobutton = promptView.findViewById(R.id.no);
            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertD.dismiss();
                }
            });
            alertD.setView(promptView);
            alertD.show();
        }
        if (wordCount > 4) {
            dicon = false;
        }
        if (dicon) {
            loading.setImageResource(loader);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
            loading.startAnimation(animation);
        } else {
            loading.setVisibility(View.GONE);
            loading.clearAnimation();
        }
        if (wordCount % 5 == 0) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
        }

    }

    public void reset() {
        playerWord = "";
        EditText eText = findViewById(R.id.userWord);
        eText.setText("");
        loading.setImageResource(loader);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        loading.startAnimation(animation);
        ImageView l1 = findViewById(R.id.letter1);
        ImageView l2 = findViewById(letter2);
        ImageView l3 = findViewById(letter3);
        ImageView l4 = findViewById(letter4);
        ImageView l5 = findViewById(letter5);
        ImageView l6 = findViewById(letter6);
        ImageView l7 = findViewById(letter7);
        ImageView l8 = findViewById(letter8);
        l1.setImageResource(blank);
        l2.setImageResource(blank);
        l3.setImageResource(blank);
        l4.setImageResource(blank);
        l5.setImageResource(blank);
        l6.setImageResource(blank);
        l7.setImageResource(blank);
        l8.setImageResource(blank);
        ImageButton twsreset = findViewById(R.id.tws);
        ImageButton dwsreset = findViewById(R.id.dws);
        ImageButton tls1reset = findViewById(R.id.tls1);
        ImageButton dls1reset = findViewById(R.id.dls1);
        ImageButton tls2reset = findViewById(R.id.tls2);
        ImageButton dls2reset = findViewById(R.id.dls2);
        ImageButton tls3reset = findViewById(R.id.tls3);
        ImageButton dls3reset = findViewById(R.id.dls3);
        ImageButton tls4reset = findViewById(R.id.tls4);
        ImageButton dls4reset = findViewById(R.id.dls4);
        ImageButton tls5reset = findViewById(R.id.tls5);
        ImageButton dls5reset = findViewById(R.id.dls5);
        ImageButton tls6reset = findViewById(R.id.tls6);
        ImageButton dls6reset = findViewById(R.id.dls6);
        ImageButton tls7reset = findViewById(tls7);
        ImageButton dls7reset = findViewById(dls7);
        ImageButton tls8reset = findViewById(tls8);
        ImageButton dls8reset = findViewById(dls8);
        ImageButton bonusreset = findViewById(bonus);
        bonusreset.setImageResource(R.drawable.fifty);
        twsreset.setImageResource(R.drawable.tws);
        dwsreset.setImageResource(R.drawable.dws);
        tls1reset.setImageResource(tls);
        tls2reset.setImageResource(tls);
        tls3reset.setImageResource(tls);
        tls4reset.setImageResource(tls);
        dls1reset.setImageResource(R.drawable.dls);
        dls2reset.setImageResource(R.drawable.dls);
        dls3reset.setImageResource(R.drawable.dls);
        dls4reset.setImageResource(R.drawable.dls);
        tls5reset.setImageResource(tls);
        tls6reset.setImageResource(tls);
        tls7reset.setImageResource(tls);
        tls8reset.setImageResource(tls);
        dls5reset.setImageResource(R.drawable.dls);
        dls6reset.setImageResource(R.drawable.dls);
        dls7reset.setImageResource(R.drawable.dls);
        dls8reset.setImageResource(R.drawable.dls);
        pressed1 = false;
        pressed2 = false;
        pressed3 = false;
        pressed4 = false;
        pressed5 = false;
        pressed6 = false;
        pressed7 = false;
        pressed8 = false;
        le1 = " ";
        le2 = " ";
        le3 = " ";
        le4 = " ";
        le5 = " ";
        le6 = " ";
        le7 = " ";
        le8 = " ";
        l1bonus = 0;
        l2bonus = 0;
        l3bonus = 0;
        l4bonus = 0;
        l5bonus = 0;
        l6bonus = 0;
        l7bonus = 0;
        l8bonus = 0;
        twspressed = false;
        dwspressed = false;
        tls1Pressed = false;
        dls1Pressed = false;
        tls2Pressed = false;
        dls2Pressed = false;
        tls3Pressed = false;
        dls3Pressed = false;
        tls4Pressed = false;
        dls4Pressed = false;
        wordTotal = 0;
        bonuspoints = 0;
        wordMultipleBonus = 1;
        total = 0;
        displayTotalWordScore();
    }

    public void scoreSheetBox(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.scoresheet, nullParent);
        final AlertDialog alertD = new AlertDialog.Builder(this).create();
        TextView p1Display = promptView.findViewById(p1);
        TextView p2Display = promptView.findViewById(p2);
        TextView p3Display = promptView.findViewById(R.id.p3);
        TextView p4Display = promptView.findViewById(p4);
        p1Display.setText(players.get(0).getName());
        p2Display.setText(players.get(1).getName());
        if (players.size() > 2) {
            p3Display.setText(players.get(2).getName());
        }
        if (players.size() > 3) {
            p4Display.setText(players.get(3).getName());
        }
        Button exitButton = promptView.findViewById(R.id.exit);
        LinearLayout ll1Display = promptView.findViewById(ll1);
        ImageView ll2Display = promptView.findViewById(ll2);
        LinearLayout ll3Display = promptView.findViewById(ll3);
        ImageView ll7Display = promptView.findViewById(ll7);
        LinearLayout ll4Display = promptView.findViewById(ll4);
        LinearLayout ll5Display = promptView.findViewById(ll5);
        ImageView ll6Display = promptView.findViewById(ll6);
        ImageView ll8Display = promptView.findViewById(ll7);
        if (players.size() > 2) {
            ll1Display.setVisibility(View.VISIBLE);
            ll2Display.setVisibility(View.VISIBLE);
            ll3Display.setVisibility(View.VISIBLE);
            ll7Display.setVisibility(View.VISIBLE);
        }
        if (players.size() == 4) {
            ll4Display.setVisibility(View.VISIBLE);
            ll5Display.setVisibility(View.VISIBLE);
            ll6Display.setVisibility(View.VISIBLE);
            ll8Display.setVisibility(View.VISIBLE);
        }
        TextView player1listofwordsdisplay = promptView.findViewById(p1words);
        TextView player2listofwordsdisplay = promptView.findViewById(p2words);
        TextView player3listofwordsdisplay = promptView.findViewById(R.id.p3words);
        TextView player4listofwordsdisplay = promptView.findViewById(R.id.p4words);
        TextView player1totalscore = promptView.findViewById(p1total);
        TextView player2totalscore = promptView.findViewById(p2total);
        TextView player3totalscore = promptView.findViewById(R.id.p3total);
        TextView player4totalscore = promptView.findViewById(R.id.p4total);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertD.dismiss();
            }
        });
        int i = 0;
        ArrayList<StringBuilder> playerwordsandscore = new ArrayList<>();
        playerwordsandscore.add(new StringBuilder());
        for (int v = 0; v < players.size(); v++) {
            playerwordsandscore.add(new StringBuilder());
            while (i < players.get(v).getWordArray().size()) {
                playerwordsandscore.get(v).append(players.get(v).getWordArray().get(i)).append("    ").append(players.get(v).getWordScoreArray().get(i)).append("\n");
                i++;
            }
            i = 0;
        }
        player1listofwordsdisplay.setText(playerwordsandscore.get(0).toString());
        player2listofwordsdisplay.setText(playerwordsandscore.get(1).toString());
        player1totalscore.setText(String.valueOf(players.get(0).getScore()));
        player2totalscore.setText(String.valueOf(players.get(1).getScore()));

        if (players.size() > 2) {
            player3listofwordsdisplay.setText(playerwordsandscore.get(2).toString());
            player3totalscore.setText(String.valueOf(players.get(2).getScore()));
        }
        if (players.size() > 3) {
            player4listofwordsdisplay.setText(playerwordsandscore.get(3).toString());
            player4totalscore.setText(String.valueOf(players.get(3).getScore()));
        }
        alertD.setView(promptView);
        alertD.show();
    }

    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public void areYouSure2(View v) {
        if (turnChangeBackwards() == 1 && players.get(0).getScore() == 0 || turnChangeBackwards() == 2 && players.get(1).getScore() == 0
                || turnChangeBackwards() == 3 && players.get(2).getScore() == 0 || turnChangeBackwards() == 4 && players.get(3).getScore() == 0) {
            Context context;
            context = getApplicationContext();
            Toast toast = Toast.makeText(context, players.get(turnChangeBackwards() - 1).getName() + ", You have no Words.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View promptView = layoutInflater.inflate(R.layout.areyousure, nullParent);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            alertD.setCanceledOnTouchOutside(false);
            alertD.setCancelable(false);
            TextView playerName = promptView.findViewById(R.id.playerName);
            playerName.setText(players.get(turnChangeBackwards()).getName());
            TextView deleteplayerwordquestion = promptView.findViewById(R.id.areyoursuretext);
            deleteplayerwordquestion.setText(getString(R.string.dlw));
            Button yesbutton = promptView.findViewById(R.id.yes);
            yesbutton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    deleteLastTurn();
                    alertD.dismiss();
                }

            });
            Button nobutton = promptView.findViewById(R.id.no);
            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertD.dismiss();
                }

            });
            alertD.setView(promptView);
            alertD.show();
        }
    }


    //work on this
    public void deleteLastTurn() {
        int previousPlayer = turnChangeBackwards();
        if (players.size() == 2) {
            if (playerTurn == 1 && players.get(previousPlayer).getScore() > 0) {
                players.get(previousPlayer).deleteLastTurn();
                TextView playerScoreDisplay2 = findViewById(playerScore2);
                playerScoreDisplay2.setText(String.valueOf(players.get(previousPlayer).getScore()));
                turnChangeForwards();
            } else if (playerTurn == 1 && players.get(previousPlayer).isOver()) {
                players.get(previousPlayer).deleteLastTurn();
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(players.get(playerTurn).getScore()));
            } else if (playerTurn == 2 && !players.get(previousPlayer).isOver() && players.get(previousPlayer).getScore() > 0) {
                players.get(previousPlayer).deleteLastTurn();
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(players.get(previousPlayer).getScore()));
                turnChangeBackwards();
            } else if (playerTurn == 2 && players.get(previousPlayer).isOver()) {
                players.get(previousPlayer).deleteLastTurn();
                TextView playerScoreDisplay2 = findViewById(playerScore2);
                playerScoreDisplay2.setText(String.valueOf(players.get(playerTurn)));
            }
        }

        //3 player...
//        if (player == 3) {
//            if (playerTurn == 1 && !player3over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 1 && player3over && !player2over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 1 && player3over && player2over && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 2 && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 2 && player1over && !player3over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 2 && player1over && !player2over && player3over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 3 && !player2over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 3 && player2over && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 3 && player2over && !player3over && player1over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 3 && player1over && player2over) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            }
//        }
//        if (player == 4) {
//            if (playerTurn == 4 && !player3over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                p3WordScoreArray.remove(p3WordScoreArray.size() - 1);
//                p3WordArray.remove(p3WordArray.size() - 1);
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 4 && player3over && !player2over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                p2WordScoreArray.remove(p2WordScoreArray.size() - 1);
//                p2WordArray.remove(p2WordArray.size() - 1);
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 4 && player3over && player2over && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                p1WordScoreArray.remove(p1WordScoreArray.size() - 1);
//                p1WordArray.remove(p1WordArray.size() - 1);
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 4 && player3over && player2over && !player4over && player1over && player4score > 0) {
//                player4score = player4score - (p4WordScoreArray.get(p4WordScoreArray.size() - 1));
//                p4WordScoreArray.remove(p4WordScoreArray.size() - 1);
//                p4WordArray.remove(p4WordArray.size() - 1);
//                TextView playerScoreDisplay4 = findViewById(playerScore4);
//                playerScoreDisplay4.setText(String.valueOf(player4score));
//                playerTurn = 4;
//            } else if (playerTurn == 3 && !player2over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                p2WordScoreArray.remove(p2WordScoreArray.size() - 1);
//                p2WordArray.remove(p2WordArray.size() - 1);
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 3 && player2over && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                p1WordScoreArray.remove(p1WordScoreArray.size() - 1);
//                p1WordArray.remove(p1WordArray.size() - 1);
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 3 && player2over && player1over && !player4over && player4score > 0) {
//                player4score = player4score - (p4WordScoreArray.get(p4WordScoreArray.size() - 1));
//                p4WordScoreArray.remove(p4WordScoreArray.size() - 1);
//                p4WordArray.remove(p4WordArray.size() - 1);
//                TextView playerScoreDisplay4 = findViewById(playerScore4);
//                playerScoreDisplay4.setText(String.valueOf(player4score));
//                playerTurn = 4;
//            } else if (playerTurn == 3 && player2over && player1over && player4over && !player3over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                p3WordScoreArray.remove(p3WordScoreArray.size() - 1);
//                p3WordArray.remove(p3WordArray.size() - 1);
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 2 && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                p1WordScoreArray.remove(p1WordScoreArray.size() - 1);
//                p1WordArray.remove(p1WordArray.size() - 1);
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 2 && player1over && !player4over && player4score > 0) {
//                player4score = player4score - (p4WordScoreArray.get(p4WordScoreArray.size() - 1));
//                p4WordScoreArray.remove(p4WordScoreArray.size() - 1);
//                p4WordArray.remove(p4WordArray.size() - 1);
//                TextView playerScoreDisplay4 = findViewById(playerScore4);
//                playerScoreDisplay4.setText(String.valueOf(player4score));
//                playerTurn = 4;
//            } else if (playerTurn == 2 && player1over && player4over && !player3over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                p3WordScoreArray.remove(p3WordScoreArray.size() - 1);
//                p3WordArray.remove(p3WordArray.size() - 1);
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 2 && player1over && player4over && player3over && !player2over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                p2WordScoreArray.remove(p2WordScoreArray.size() - 1);
//                p2WordArray.remove(p2WordArray.size() - 1);
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 1 && !player4over && player4score > 0) {
//                player4score = player4score - (p4WordScoreArray.get(p4WordScoreArray.size() - 1));
//                p4WordScoreArray.remove(p4WordScoreArray.size() - 1);
//                p4WordArray.remove(p4WordArray.size() - 1);
//                TextView playerScoreDisplay4 = findViewById(playerScore4);
//                playerScoreDisplay4.setText(String.valueOf(player4score));
//                playerTurn = 4;
//            } else if (playerTurn == 1 && player4over && !player3over && player3score > 0) {
//                player3score = player3score - (p3WordScoreArray.get(p3WordScoreArray.size() - 1));
//                p3WordScoreArray.remove(p3WordScoreArray.size() - 1);
//                p3WordArray.remove(p3WordArray.size() - 1);
//                TextView playerScoreDisplay3 = findViewById(playerScore3);
//                playerScoreDisplay3.setText(String.valueOf(player3score));
//                playerTurn = 3;
//            } else if (playerTurn == 1 && player4over && player3over && !player2over && player2score > 0) {
//                player2score = player2score - (p2WordScoreArray.get(p2WordScoreArray.size() - 1));
//                p2WordScoreArray.remove(p2WordScoreArray.size() - 1);
//                p2WordArray.remove(p2WordArray.size() - 1);
//                TextView playerScoreDisplay2 = findViewById(playerScore2);
//                playerScoreDisplay2.setText(String.valueOf(player2score));
//                playerTurn = 2;
//            } else if (playerTurn == 1 && player4over && player3over && player2over && !player1over && player1score > 0) {
//                player1score = player1score - (p1WordScoreArray.get(p1WordScoreArray.size() - 1));
//                p1WordScoreArray.remove(p1WordScoreArray.size() - 1);
//                p1WordArray.remove(p1WordArray.size() - 1);
//                TextView playerScoreDisplay1 = findViewById(playerScore1);
//                playerScoreDisplay1.setText(String.valueOf(player1score));
//                playerTurn = 1;
//            } else if (playerTurn == 4 && player1over && player2over && player3over) {
//                player4score = player4score - (p4WordScoreArray.get(p4WordScoreArray.size() - 1));
//                p4WordScoreArray.remove(p4WordScoreArray.size() - 1);
//                p4WordArray.remove(p4WordArray.size() - 1);
//                TextView playerScoreDisplay4 = findViewById(playerScore4);
//                playerScoreDisplay4.setText(String.valueOf(player4score));
//                playerTurn = 4;
//            }
//        }
        turnNameChange(players.get(playerTurn));
    }

    public void areYouSure3(View v) {
        if (turnChangeBackwards() == 1 && players.get(0).getScore() == 0 ||
                turnChangeBackwards() == 2 && players.get(1).getScore() == 0 ||
                turnChangeBackwards() == 3 && players.get(2).getScore() == 0 ||
                turnChangeBackwards() == 4 && players.get(3).getScore() == 0) {
            Context context;
            context = getApplicationContext();
            Toast toast = Toast.makeText(context, "No words have been entered.", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View promptView = layoutInflater.inflate(R.layout.areyousure, nullParent);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            alertD.setCanceledOnTouchOutside(false);
            alertD.setCancelable(false);

            TextView playerName = promptView.findViewById(R.id.playerName);
            playerName.setText(players.get(turnChangeBackwards()).getName());
            TextView deleteplayerwordquestion = promptView.findViewById(R.id.areyoursuretext);
            deleteplayerwordquestion.setText(getString(R.string.aw2));
            Button yesbutton = promptView.findViewById(R.id.yes);
            yesbutton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    turnNameChange(players.get(turnChangeBackwards()));
                    alertD.dismiss();
                }

            });
            Button nobutton = promptView.findViewById(R.id.no);
            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertD.dismiss();
                }

            });
            alertD.setView(promptView);
            alertD.show();
        }
    }

    public int turnChangeBackwards() {
        for (int i = playerTurn; players.get(i).isOver(); i--) {
            if (i == 0) {
                i = players.size();
            }
            playerTurn = i;
        }
        return playerTurn;
    }

    public void turnChangeForwards() {
        TextView p1name = findViewById(R.id.player1name);
        TextView p1score = findViewById(R.id.playerScore1);
        TextView p2name = findViewById(R.id.player2name);
        TextView p2score = findViewById(R.id.playerScore2);
        TextView p3name = findViewById(R.id.player3name);
        TextView p3score = findViewById(R.id.playerScore3);
        TextView p4name = findViewById(R.id.player4name);
        TextView p4score = findViewById(R.id.playerScore4);
        p1name.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p1score.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p2name.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p2score.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p3name.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p3score.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p4name.setBackgroundColor(ContextCompat.getColor(context, transparent));
        p4score.setBackgroundColor(ContextCompat.getColor(context, transparent));
        if (players.size() == 2) {
            if (!players.get(1).isOver() && playerTurn == 0) {
                playerTurn = 1;
                p2name.setBackgroundColor(ContextCompat.getColor(context, appblue));
                p2score.setBackgroundColor(ContextCompat.getColor(context, appblue));
            } else {
                playerTurn = 0;
                p1name.setBackgroundColor(ContextCompat.getColor(context, appblue));
                p1score.setBackgroundColor(ContextCompat.getColor(context, appblue));
            }
        }
//        if(players.size()>2) {
//            if (players.get(0).isOver() && playerTurn == 1) {
//                playerTurn = 2;
//            }
//            if (players.get(1).isOver() && playerTurn == 2) {
//                if (players.size() > 2) {
//                    playerTurn = 3;
//                } else {
//                    playerTurn = 1;
//                }
//            }
//            if (players.get(2).isOver() && playerTurn == 3) {
//                if (players.size() > 3) {
//                    playerTurn = 4;
//                } else {
//                    playerTurn = 1;
//                }
//            }
//            if(players.size()> 3) {
//                if (players.get(3).isOver() && playerTurn == 4) {
//                    playerTurn = 1;
//                }
//            }
//        }
    }

    //can be compressed
    public void noWords() {
        TextView p1name = findViewById(R.id.player1name);
        TextView p2name = findViewById(R.id.player2name);
        TextView p3name = findViewById(R.id.player3name);
        TextView p4name = findViewById(R.id.player4name);
        TextView p1score = findViewById(R.id.playerScore1);
        TextView p2score = findViewById(R.id.playerScore2);
        TextView p3score = findViewById(R.id.playerScore3);
        TextView p4score = findViewById(R.id.playerScore4);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (playerTurn == 0) {
            players.get(playerTurn).setOver(settings, true);
            p1name.setBackgroundColor(ContextCompat.getColor(context, R.color.appgrey));
            p1score.setBackgroundColor(ContextCompat.getColor(context, appgrey));
        } else if (playerTurn == 1) {
            players.get(playerTurn).setOver(settings, true);
            p2name.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            p2score.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            p2score.setText(String.valueOf(players.get(playerTurn).getScore()));
        } else if (playerTurn == 2) {
            players.get(playerTurn).setOver(settings, true);
            p3name.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            p3score.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            p3score.setText(String.valueOf(players.get(playerTurn).getScore()));
        } else if (playerTurn == 3) {
            players.get(playerTurn).setOver(settings, true);
            p4name.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            p4score.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            p4score.setText(String.valueOf(players.get(playerTurn).getScore()));
        }
        for (int i = 1; players.get(i).isOver(); i++) {
            if (i > players.size()) {
                endGameFormat();
            }
        }
        turnNameChange(players.get(playerTurn));
    }

    public void endGameFormat() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        @SuppressLint("InflateParams") View promptView = layoutInflater.inflate(endgame, null);
        final AlertDialog endgame = new AlertDialog.Builder(this).create();
        TextView p1egDisplay = promptView.findViewById(R.id.p1eg);
        TextView p2egDisplay = promptView.findViewById(R.id.p2eg);
        TextView p1scoreegDisplay = promptView.findViewById(R.id.p1scoreeg);
        TextView p2scoreegDisplay = promptView.findViewById(R.id.p2scoreeg);
        p1egDisplay.setText(getString(R.string.colonname, players.get(1).getName()));
        p2egDisplay.setText(getString(R.string.colonname, players.get(2).getName()));
        p1scoreegDisplay.setText(String.valueOf(players.get(1).getScore()));
        p2scoreegDisplay.setText(String.valueOf(players.get(2).getScore()));
        endgame.setCanceledOnTouchOutside(false);
        endgame.setCancelable(false);
        Button newGame = promptView.findViewById(R.id.newgame);
        TextView winnerDisplay = promptView.findViewById(winner);
        String winner;
        SparseArray<String> winnerWord = new SparseArray<>();
        int key = Math.max(players.get(0).getScore(), Math.max(players.get(1).getScore(), Math.max(players.get(2).getScore(), players.get(3).getScore())));
        for (int i = 1; i < players.size(); i++) {
            winnerWord.put(players.get(i).getScore(), players.get(i).getName());
        }
        winner = winnerWord.get(key);
        TextView p3egDisplay = promptView.findViewById(R.id.p3eg);
        TextView p3scoreegDisplay = promptView.findViewById(R.id.p3scoreeg);
        LinearLayout p3vDisplay = promptView.findViewById(R.id.p3v);
        TextView p4egDisplay = promptView.findViewById(R.id.p4eg);
        TextView p4scoreegDisplay = promptView.findViewById(R.id.p4scoreeg);
        LinearLayout p4vDisplay = promptView.findViewById(R.id.p4v);
        if (players.size() > 2) {
            p3vDisplay.setVisibility(View.VISIBLE);
            p3egDisplay.setText(getString(R.string.colonname, players.get(2).getName()));
            p3scoreegDisplay.setText(String.valueOf(players.get(2).getScore()));
        }
        if (players.size() == 4) {
            p4vDisplay.setVisibility(View.VISIBLE);
            p4egDisplay.setText(getString(R.string.colonname, players.get(3).getName()));
            p4scoreegDisplay.setText(String.valueOf(players.get(3).getScore()));
        }
        newGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView player1namedisplayer = findViewById(R.id.player1name);
                TextView player2namedisplayer = findViewById(R.id.player2name);
                TextView player3namedisplayer = findViewById(R.id.player3name);
                TextView player4namedisplayer = findViewById(R.id.player4name);
                TextView player1score = findViewById(playerScore1);
                TextView player2score = findViewById(playerScore2);
                TextView player3score = findViewById(playerScore3);
                TextView player4score = findViewById(playerScore4);
                player1namedisplayer.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player2namedisplayer.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player3namedisplayer.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player4namedisplayer.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player1score.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player2score.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player3score.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                player4score.setBackgroundColor(ContextCompat.getColor(context, dialogbg));
                wordCount = 0;
                dialogBox();
                playerTurn = 0;
                endgame.dismiss();
            }
        });
        winnerDisplay.setText(winner);
        endgame.setView(promptView);
        saved = false;
        endgame.show();
    }

    //exchanging tiles
    public void areYouSure(View v) {
        if (players.get(playerTurn).getScore() > 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View promptView = layoutInflater.inflate(R.layout.areyousure, nullParent);
            final AlertDialog alertD = new AlertDialog.Builder(this).create();
            alertD.setCanceledOnTouchOutside(false);
            alertD.setCancelable(false);
            TextView playerName = promptView.findViewById(R.id.playerName);
            int overint = 0;
            playerName.setText(players.get(playerTurn - overint).getName());
            Button yesbutton = promptView.findViewById(R.id.yes);
            yesbutton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    lastScreen();
                    alertD.dismiss();
                }

            });
            Button nobutton = promptView.findViewById(R.id.no);
            nobutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertD.dismiss();
                }

            });
            alertD.setView(promptView);
            alertD.show();
        } else {
            Toast toast = Toast.makeText(context, "Click Skip Turn to Exchange your Tiles.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void letterclick1(View v) {
        Context context;
        ImageButton l1 = findViewById(letter1);
        context = l1.getContext();
        ImageButton tls1 = findViewById(R.id.tls1);
        ImageButton dls1 = findViewById(R.id.dls1);
        if (playerWord.length() > 1 && !pressed1) {
            total = total - alphabet.get(le1);
            l1.setImageResource(blank);
            le1 = "blank";
            l1bonus = 0;
            pressed1 = true;
            displayTotalWordScore();
            tls1.setImageResource(tls);
            dls1.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 1) {
            le1 = playerWord.substring(0, 1);
            total = total + alphabet.get(le1);
            int id = context.getResources().getIdentifier(le1, "drawable", context.getPackageName());
            l1.setImageResource(id);
            displayTotalWordScore();
            pressed1 = false;
            dls1.setImageResource(R.drawable.dlse);
            tls1.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick2(View v) {
        Context context;
        ImageButton l2 = findViewById(letter2);
        context = l2.getContext();
        ImageButton tls2 = findViewById(R.id.tls2);
        ImageButton dls2 = findViewById(R.id.dls2);
        if (playerWord.length() > 1 && !pressed2) {
            total = total - alphabet.get(le2);
            l2.setImageResource(blank);
            le2 = "blank";
            l2bonus = 0;
            pressed2 = true;
            displayTotalWordScore();
            tls2.setImageResource(tls);
            dls2.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 1) {
            le2 = playerWord.substring(1, 2);
            total = total + alphabet.get(le2);
            int id = context.getResources().getIdentifier(le2, "drawable", context.getPackageName());
            l2.setImageResource(id);
            displayTotalWordScore();
            pressed2 = false;
            dls2.setImageResource(R.drawable.dlse);
            tls2.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick3(View v) {
        Context context;
        ImageButton l3 = findViewById(letter3);
        context = l3.getContext();
        ImageButton tls3 = findViewById(R.id.tls3);
        ImageButton dls3 = findViewById(R.id.dls3);

        if (playerWord.length() > 2 && !pressed3) {
            total = total - alphabet.get(le3);
            l3.setImageResource(blank);
            le3 = "blank";
            l3bonus = 0;
            pressed3 = true;
            displayTotalWordScore();
            tls3.setImageResource(tls);
            dls3.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 2) {
            le3 = playerWord.substring(2, 3);
            total = total + alphabet.get(le3);
            int id = context.getResources().getIdentifier(le3, "drawable", context.getPackageName());
            l3.setImageResource(id);
            displayTotalWordScore();
            pressed3 = false;
            dls3.setImageResource(R.drawable.dlse);
            tls3.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick4(View v) {
        Context context;
        ImageButton l4 = findViewById(letter4);
        context = l4.getContext();
        ImageButton tls4 = findViewById(R.id.tls4);
        ImageButton dls4 = findViewById(R.id.dls4);

        if (playerWord.length() > 3 && !pressed4) {
            total = total - alphabet.get(le4);
            l4.setImageResource(blank);
            le4 = "blank";
            l4bonus = 0;
            pressed4 = true;
            displayTotalWordScore();
            tls4.setImageResource(tls);
            dls4.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 3) {
            le4 = playerWord.substring(3, 4);
            total = total + alphabet.get(le4);
            int id = context.getResources().getIdentifier(le4, "drawable", context.getPackageName());
            l4.setImageResource(id);
            displayTotalWordScore();
            pressed4 = false;
            dls4.setImageResource(R.drawable.dlse);
            tls4.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick5(View v) {
        Context context;
        ImageButton l5 = findViewById(letter5);
        context = l5.getContext();
        ImageButton tls5 = findViewById(R.id.tls5);
        ImageButton dls5 = findViewById(R.id.dls5);

        if (playerWord.length() > 4 && !pressed5) {
            total = total - alphabet.get(le5);
            l5.setImageResource(blank);
            le5 = "blank";
            l5bonus = 0;
            pressed5 = true;
            displayTotalWordScore();
            tls5.setImageResource(tls);
            dls5.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 4) {
            le5 = playerWord.substring(4, 5);
            total = total + alphabet.get(le5);
            int id = context.getResources().getIdentifier(le5, "drawable", context.getPackageName());
            l5.setImageResource(id);
            displayTotalWordScore();
            pressed5 = false;
            dls5.setImageResource(R.drawable.dlse);
            tls5.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick6(View v) {
        Context context;
        ImageButton l6 = findViewById(letter6);
        context = l6.getContext();
        ImageButton tls6 = findViewById(R.id.tls6);
        ImageButton dls6 = findViewById(R.id.dls6);

        if (playerWord.length() > 5 && !pressed6) {
            total = total - alphabet.get(le6);
            l6.setImageResource(blank);
            le6 = "blank";
            l6bonus = 0;
            pressed6 = true;
            displayTotalWordScore();
            tls6.setImageResource(tls);
            dls6.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 5) {
            le6 = playerWord.substring(5, 6);
            total = total + alphabet.get(le6);
            int id = context.getResources().getIdentifier(le6, "drawable", context.getPackageName());
            l6.setImageResource(id);
            displayTotalWordScore();
            pressed6 = false;
            dls6.setImageResource(R.drawable.dlse);
            tls6.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick7(View v) {
        Context context;
        ImageButton l7 = findViewById(letter7);
        context = l7.getContext();
        ImageButton tls7 = findViewById(R.id.tls7);
        ImageButton dls7 = findViewById(R.id.dls7);

        if (playerWord.length() > 6 && !pressed7) {
            total = total - alphabet.get(le7);
            l7.setImageResource(blank);
            le7 = "blank";
            l7bonus = 0;
            pressed7 = true;
            displayTotalWordScore();
            tls7.setImageResource(tls);
            dls7.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 6) {
            le7 = playerWord.substring(6, 7);
            total = total + alphabet.get(le7);
            int id = context.getResources().getIdentifier(le7, "drawable", context.getPackageName());
            l7.setImageResource(id);
            displayTotalWordScore();
            pressed7 = false;
            dls7.setImageResource(R.drawable.dlse);
            tls7.setImageResource(R.drawable.tlse);
        }
    }

    public void letterclick8(View v) {
        Context context;
        ImageButton l8 = findViewById(letter8);
        context = l8.getContext();
        ImageButton tls8 = findViewById(R.id.tls8);
        ImageButton dls8 = findViewById(R.id.dls8);

        if (playerWord.length() > 7 && !pressed8) {
            total = total - alphabet.get(le8);
            l8.setImageResource(blank);
            le8 = "blank";
            l8bonus = 0;
            pressed8 = true;
            displayTotalWordScore();
            tls8.setImageResource(tls);
            dls8.setImageResource(R.drawable.dls);
        } else if (playerWord.length() > 7) {
            le8 = playerWord.substring(7, 8);
            total = total + alphabet.get(le8);
            int id = context.getResources().getIdentifier(le8, "drawable", context.getPackageName());
            l8.setImageResource(id);
            displayTotalWordScore();
            pressed8 = false;
            dls8.setImageResource(R.drawable.dlse);
            tls8.setImageResource(R.drawable.tlse);
        }
    }

    public void exchangeTiles() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.areyousure, nullParent);
        final AlertDialog alertD = new AlertDialog.Builder(this).create();
        alertD.setCanceledOnTouchOutside(false);
        alertD.setCancelable(false);
        TextView playerName = promptView.findViewById(R.id.playerName);
        playerName.setText(players.get(playerTurn).getName());
        TextView deleteplayerwordquestion = promptView.findViewById(R.id.areyoursuretext);
        deleteplayerwordquestion.setText(getString(R.string.skip));
        Button yesbutton = promptView.findViewById(R.id.yes);
        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerTurn == 0 && !players.get(0).isOver()) {
                    players.get(playerTurn).setScore(wordTotal);
                    turnChangeForwards();
                    reset();
                } else if (playerTurn == 1 && !players.get(1).isOver()) {
                    players.get(playerTurn).setScore(wordTotal);
                    if (players.size() > 2) {
                        turnChangeForwards();
                    }
                    reset();
                } else if (playerTurn == 2 && !players.get(2).isOver()) {
                    players.get(playerTurn).setScore(wordTotal);
                    if (players.size() > 3) {
                        turnChangeForwards();
                    }
                    reset();
                } else if (playerTurn == 3 && !players.get(3).isOver()) {
                    players.get(playerTurn).setScore(wordTotal);
                    turnChangeForwards();
                    reset();
                }
                turnNameChange(players.get(playerTurn));
                alertD.dismiss();
            }

        });
        Button nobutton = promptView.findViewById(R.id.no);
        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.dismiss();
                Context context;
                context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Enter your word", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
        alertD.setView(promptView);
        alertD.show();
    }

    public void lastScreen() {
        lastTotal = 0;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View promptView = layoutInflater.inflate(R.layout.letterremainder, nullParent);
        final AlertDialog endgame = new AlertDialog.Builder(this).create();
        TextView playerName = promptView.findViewById(R.id.playerName);
        playerName.setText(players.get(playerTurn).getName());
        Button finishButton = promptView.findViewById(R.id.lastButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText lastWordBox = promptView.findViewById(R.id.minusletters);
                lastWord = lastWordBox.getText().toString();
                if (lastWord.length() == 0) {
                    players.get(playerTurn).add50();
                    players.get(playerTurn).addWord("CLEAR", 50);
                }
                if (lastWord.length() > 0) {
                    if (lastWord.length() > 1) {
                        le1 = lastWord.substring(1, 2);
                        lastTotal = lastTotal + alphabet.get(le1);
                    }
                    if (lastWord.length() > 2) {
                        le2 = lastWord.substring(2, 3);
                        lastTotal = lastTotal + alphabet.get(le2);
                    }
                    if (lastWord.length() > 3) {
                        le3 = lastWord.substring(3, 4);
                        lastTotal = lastTotal + alphabet.get(le3);
                    }
                    if (lastWord.length() > 4) {
                        le4 = lastWord.substring(4, 5);
                        lastTotal = lastTotal + alphabet.get(le4);
                    }
                    if (lastWord.length() > 5) {
                        le5 = lastWord.substring(5, 6);
                        lastTotal = lastTotal + alphabet.get(le5);
                    }
                    if (lastWord.length() > 6) {
                        le6 = lastWord.substring(6, 7);
                        lastTotal = lastTotal + alphabet.get(le6);
                    }
                    if (lastWord.length() > 7) {
                        le7 = lastWord.substring(7, 8);
                        lastTotal = lastTotal + alphabet.get(le7);
                    }
                    if (lastWord.length() > 8) {
                        le8 = lastWord.substring(8, 9);
                        lastTotal = lastTotal + alphabet.get(le8);
                    }
                }
                players.get(playerTurn).subtractRemainingLetters(lastTotal);
                players.get(playerTurn).addWord(lastWord, 0 - lastTotal);
                endgame.dismiss();
                noWords();
            }
        });
        endgame.setView(promptView);
        endgame.show();
    }

    public void launchMarket(View v) {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

    public void txtLooker(String aString) {
        Context context = getApplicationContext();
        InputStream is = context.getResources().openRawResource(R.raw.ospd);
        loading.setImageResource(loader);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        loading.startAnimation(animation);
        if (!dicoption) {
            is = context.getResources().openRawResource(R.raw.sowpods);
        }
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            String currentLine;
            currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                currentLine = bufferedReader.readLine();
                if (currentLine.equals(aString)) {
                    loading.clearAnimation();
                    loading.setImageResource(R.drawable.tick);
                    loading.setBackgroundColor(ContextCompat.getColor(context, transparent));
                    currentLine = null;
                    yesno = true;
                    bufferedReader.close();
                    Button addButton = findViewById(R.id.addWordButton);
                    addButton.setEnabled(true);
                }
            }
            bufferedReader.close();
        } catch (Exception anException) {
            System.out.println("Buffer exception");
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception anException) {
                System.out.println("Buffer exception");
            }
        }
        if (!yesno && playerWord.length() > 0) {
            loading.clearAnimation();
            loading.setImageResource(R.drawable.cross);
            if (dicon && !dicoverride) {
                Button addButton = findViewById(R.id.addWordButton);
                addButton.setEnabled(false);
            } else if (dicon) {
                Button addButton = findViewById(R.id.addWordButton);
                addButton.setEnabled(true);
            }
        }
    }

    public void whichDic(View v) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View promptView = layoutInflater.inflate(R.layout.dicconfig, nullParent);
        final AlertDialog alertD = new AlertDialog.Builder(this).create();
        alertD.setCanceledOnTouchOutside(false);
        alertD.setCancelable(false);
        Button done = promptView.findViewById(R.id.done);
        final Switch dicoverride1 = promptView.findViewById(R.id.override);
        final Switch diconoff = promptView.findViewById(R.id.onoff);
        final RadioButton radio1 = promptView.findViewById(R.id.ospd);
        final RadioButton radio2 = promptView.findViewById(R.id.sowpods);
        if (!dicon) {
            diconoff.setChecked(false);
            dicoverride1.setEnabled(false);
            radio1.setEnabled(false);
            radio2.setEnabled(false);

        }
        if (dicon) {
            diconoff.setChecked(true);
            dicoverride1.setEnabled(true);
            radio1.setEnabled(true);
            radio2.setEnabled(true);
        }
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup dicpicker = promptView.findViewById(R.id.picdic);
                int selectedId = dicpicker.getCheckedRadioButtonId();
                if (selectedId == R.id.ospd) {
                    dicoption = true;
                }
                if (selectedId == R.id.sowpods) {
                    dicoption = false;
                }
                Context context;
                context = getApplicationContext();
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("dicon", dicon).apply();
                editor.putBoolean("dicoverride", dicoverride).apply();
                editor.putBoolean("dicoption", dicoption).apply();
                alertD.dismiss();
            }
        });

        dicoverride1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dicoverride = dicoverride1.isChecked();
            }
        });
        diconoff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean switchState = diconoff.isChecked();
                if (switchState) {
                    dicon = true;
                    dicoverride1.setEnabled(true);
                    radio1.setEnabled(true);
                    radio2.setEnabled(true);
                } else {
                    dicon = false;
                    dicoverride1.setEnabled(false);
                    radio1.setEnabled(false);
                    radio2.setEnabled(false);
                }
            }

        });
        alertD.setView(promptView);
        alertD.show();
    }

    //setup a new game
    public void setup() {
        if (dicon) {
            loading.setVisibility(View.VISIBLE);
        }
        if (!dicon) {
            dicoverride = true;
            loading.setVisibility(View.GONE);
        }
        if (wordCount > 4) {
            dicoverride = true;
            loading.setVisibility(View.GONE);
        }

        TextView player1scoreDisplay = findViewById(playerScore1);
        TextView player2scoreDisplay = findViewById(R.id.playerScore2);
        TextView player3scoreDisplay = findViewById(playerScore3);
        TextView player4scoreDisplay = findViewById(playerScore4);
        TextView player1namedisplayer = findViewById(R.id.player1name);
        TextView player2namedisplayer = findViewById(R.id.player2name);
        TextView player3namedisplayer = findViewById(R.id.player3name);
        TextView player4namedisplayer = findViewById(R.id.player4name);
        player1namedisplayer.setText(players.get(0).getName());
        player2namedisplayer.setText(players.get(1).getName());
        player3namedisplayer.setText(players.get(2).getName());
        player4namedisplayer.setText(players.get(3).getName());
        if (players.get(0).isOver()) {
            player1namedisplayer.setBackgroundColor(ContextCompat.getColor(context, R.color.appgrey));
            player1scoreDisplay.setBackgroundColor(ContextCompat.getColor(context, appgrey));
        }
        if (players.get(1).isOver()) {
            player2namedisplayer.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            player2scoreDisplay.setBackgroundColor(ContextCompat.getColor(context, appgrey));
        }
        if (players.get(2).isOver()) {
            player3namedisplayer.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            player3scoreDisplay.setBackgroundColor(ContextCompat.getColor(context, appgrey));
        }
        if (players.get(3).isOver()) {
            player4namedisplayer.setBackgroundColor(ContextCompat.getColor(context, appgrey));
            player4scoreDisplay.setBackgroundColor(ContextCompat.getColor(context, appgrey));
        }
        if (players.size() == 3) {
            player3namedisplayer.setVisibility(View.VISIBLE);
            player3scoreDisplay.setVisibility(View.VISIBLE);
        }
        if (players.size() == 4) {
            player3namedisplayer.setVisibility(View.VISIBLE);
            player3scoreDisplay.setVisibility(View.VISIBLE);
            player4namedisplayer.setVisibility(View.VISIBLE);
            player4scoreDisplay.setVisibility(View.VISIBLE);
        }
        ImageView loading = findViewById(R.id.loading);
        if (dicon) {
            loading.setImageResource(loader);

        } else {
            loading.clearAnimation();
            loading.setVisibility(View.GONE);
        }
        saved = true;
    }

    public void scrabbleScorePlatinum(View view) {
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=lood.corporatezen.scrabblescoreplatinum"));
        startActivity(i);
    }
}