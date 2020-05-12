package lood.corporatezen.scrabblescore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<String> wordArray;
    ArrayList<String> wordScoreArray;
    int score;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, ArrayList<String> wordArray, ArrayList<String> wordScoreArray, int score) {
        this.name = name;
        this.wordArray = wordArray;
        this.wordScoreArray = wordScoreArray;
        this.score = score;
    }

    public void saveTurn(Context context, SharedPreferences settings,
                         int score, int playerTurn, int wordCount, int listSize, int wordScore,
    String word){
        //method for locally saving and storage saving
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getWordArray() {
        return wordArray;
    }

    public void setWordArray(ArrayList<String> wordArray) {
        this.wordArray = wordArray;
    }

    public ArrayList<String> getWordScoreArray() {
        return wordScoreArray;
    }

    public void setWordScoreArray(ArrayList<String> wordScoreArray) {
        this.wordScoreArray = wordScoreArray;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
