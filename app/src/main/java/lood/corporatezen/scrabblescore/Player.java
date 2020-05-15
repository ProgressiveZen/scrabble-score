package lood.corporatezen.scrabblescore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<String> wordArray;
    private ArrayList<Integer> wordScoreArray;
    private int score;
    private boolean over;

    public Player(String name) {
        this.name = name;
        wordArray = new ArrayList<>();
        wordScoreArray = new ArrayList<>();
        score = 0;
        over = false;
    }

    public Player(String name, ArrayList<String> wordArray, ArrayList<Integer> wordScoreArray, int score, boolean over) {
        this.name = name;
        this.wordArray = wordArray;
        this.wordScoreArray = wordScoreArray;
        this.score = score;
        this.over = over;
    }

    public void saveTurn(Context context, SharedPreferences settings, String name,
                         int score, int wordScore
                         ){
        //method for locally saving and storage saving    }
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(name + "player1score", wordScore);
        for (int i = 0; i < wordScoreArray.size(); i++) {
            editor.putInt(name + "p1wordscore" + i, wordScoreArray.get(i));
        }
        for (int i = 0; i < wordArray.size(); i++) {
            editor.putString(name + "p1word" + i, wordArray.get(i));
        }
editor.apply();
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public ArrayList<String> getWordArray() {
        return wordArray;
    }

    public void setWordArray(ArrayList<String> wordArray) {
        this.wordArray = wordArray;
    }

    public ArrayList<Integer> getWordScoreArray() {
        return wordScoreArray;
    }

    public void setWordScoreArray(ArrayList<Integer> wordScoreArray) {
        this.wordScoreArray = wordScoreArray;
    }

    public void deleteLastTurn(){
        score = score - (wordScoreArray.get(wordScoreArray.size() - 1));
        wordScoreArray.remove(wordScoreArray.size() - 1);
        wordArray.remove(wordArray.size() - 1);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score  = this.score + score;
    }
}
