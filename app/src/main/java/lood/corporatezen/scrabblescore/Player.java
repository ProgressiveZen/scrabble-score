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
    private int player;

    public Player(String name, int player) {
        this.name = name;
        this.player = player;
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

    public void addWord(String word, int wordScore){
        wordArray.add(wordArray.size(), word);
        wordScoreArray.add(wordScoreArray.size(), wordScore);
    }

    //end turn/save turn
    public void saveTurn(SharedPreferences settings, String newWord, int wordScore){
        wordScoreArray.add(wordScore);
        wordArray.add(newWord);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(player + "score", score);
        for (int i = 0; i < wordScoreArray.size(); i++) {
            editor.putInt(player + "wordscorearray" + i, wordScoreArray.get(i));
        }
        for (int i = 0; i < wordArray.size(); i++) {
            editor.putString(player + "word" + i, wordArray.get(i));
        }
editor.apply();
    }

    public void add50(){
        setScore(getScore()+50);
    }

    public void subtractRemainingLetters(int lastTotal){
        setScore(score-lastTotal);
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(SharedPreferences settings, boolean over) {
        this.over = over;
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(player + "over", over).apply();
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
