package com.twodays.jungleclicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by nader on 11/30/16.
 */

public class Tree {

    int[] clickUpgrades = {1, 0, 0, 0, 0};
    int[] genUpgrades = {0, 0, 0, 0, 0};
    private int totalCoconuts;
    private int timesClicked;
    private int coconutsSpent;
    private int coconuts;

    private TreeListener activity;
    private boolean running;
    private int prevCoconuts;
    private int maxRate, curRate;

    public Tree(TreeListener activity) {
        this.activity = activity;

        coconuts = 0;
        prevCoconuts = 0;
        maxRate = 0;
        curRate = 0;

        totalCoconuts = 0;
        timesClicked = 0;
        coconutsSpent = 0;

        load();

        running = true;
        TreeAsyncTask treeAsyncTask = new TreeAsyncTask();
        treeAsyncTask.execute();
    }

    public void save() {
        SharedPreferences sharedPref = activity.getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("pref_key_total_coconuts", totalCoconuts);
        editor.putInt("pref_key_coconuts", coconuts);
        editor.putInt("pref_key_times_clicked", timesClicked);
        editor.putInt("pref_key_coconuts_spent", coconutsSpent);
        for(int i = 0;i<5;i++){
            editor.putInt("pref_key_click_upgrades"+i, clickUpgrades[i]);
            editor.putInt("pref_key_generate_spent"+i, genUpgrades[i]);
        }
        editor.apply();
    }

    public void load() {
        SharedPreferences sharedPref = activity.getCurrentActivity().getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 0;
        totalCoconuts = sharedPref.getInt("pref_key_total_coconuts", defaultValue);
        Log.d("tree", totalCoconuts + "total nuts");
        coconuts = sharedPref.getInt("pref_key_coconuts", defaultValue);
        coconutsSpent = sharedPref.getInt("pref_key_coconuts_spent", defaultValue);
        timesClicked = sharedPref.getInt("pref_key_times_clicked", defaultValue);
        for(int i = 0;i<5;i++){
            if (i==0){
                clickUpgrades[i]=sharedPref.getInt("pref_key_click_upgrades"+i, 1);
                genUpgrades[i]=sharedPref.getInt("pref_key_generate_spent"+i, 1);
            }
            else {
                clickUpgrades[i] = sharedPref.getInt("pref_key_click_upgrades" + i, defaultValue);
                genUpgrades[i] = sharedPref.getInt("pref_key_generate_spent" + i, defaultValue);
            }
        }
    }

    public void click() {
        coconuts += calcClick();
        totalCoconuts += calcClick();

        timesClicked++;
        activity.updateView();
    }

    public int calcClick() {
        return (clickUpgrades[0] * 10) + (clickUpgrades[1] * 100) + (clickUpgrades[2] * 500)
                + (clickUpgrades[3] * 1000) + (clickUpgrades[4] * 10000);
    }

    public void generate() {
        coconuts += calcGen();
        totalCoconuts += calcGen();
        activity.updateView();
    }

    public int calcGen() {
        return (genUpgrades[0] * 10) + (genUpgrades[1] * 100) + (genUpgrades[2] * 500)
                + (genUpgrades[3] * 1000) + (genUpgrades[4] * 10000);
    }


    public int getCoconuts() {
        return coconuts;
    }

    public int getPrevCoconuts(){
        return prevCoconuts;
    }

    public void setPrevCoconuts(int currentCoconuts){
        this.prevCoconuts = currentCoconuts;
    }

    public int getRate(){
        return coconuts - prevCoconuts;
    }

    public int getMaxRate(){
        return maxRate;
    }

    public void setMaxRate(int rate){
        this.maxRate = rate;
    }

    public void rateIncreased(){
        Toast.makeText(activity.getCurrentActivity(), "You've just generated coconuts faster than ever! :)",
                Toast.LENGTH_SHORT).show();
    }

    public boolean canAfford(String str) {
        if (Objects.equals(str, "c1")) {
            return coconuts >= 10;
        }
        if (Objects.equals(str, "c2")) {
            return coconuts >= 100;
        }
        if (Objects.equals(str, "c3")) {
            return coconuts >= 1000;
        }
        if (Objects.equals(str, "c4")) {
            return coconuts >= 10000;
        }
        if (Objects.equals(str, "g1")) {
            return coconuts >= 100;
        }
        if (Objects.equals(str, "g2")) {
            return coconuts >= 1000;
        }
        if (Objects.equals(str, "g3")) {
            return coconuts >= 10000;
        }
        if (Objects.equals(str, "g4")) {
            return coconuts >= 100000;
        }
        if (Objects.equals(str, "g5")) {
            return coconuts >= 1000000;
        }
        return false;
    }

    public void subtractCoconuts(int cost) {
        coconuts = coconuts - cost;
        coconutsSpent += cost;
    }

    public int getCoconutsSpent() {
        return coconutsSpent;
    }

    public void setCoconutsSpent(int coconutsSpent) {
        this.coconutsSpent = coconutsSpent;
    }

    public interface TreeListener {
        void updateView();
        Activity getCurrentActivity();
    }

    public class TreeAsyncTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            int interval = 1000;

            while (running) {
                try {
                    publishProgress();
                    Thread.sleep(interval);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            MainActivity.tree.generate();
            super.onProgressUpdate(values);
        }
    }
}
