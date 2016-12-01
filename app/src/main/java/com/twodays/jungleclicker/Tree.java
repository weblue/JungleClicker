package com.twodays.jungleclicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by nader on 11/30/16.
 */

class Tree {

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

    Tree(TreeListener activity) {
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


    void save() {
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

    private void load() {
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

    void click() {
        coconuts += calcClick();
        totalCoconuts += calcClick();

        timesClicked++;
        activity.updateView();
    }

    int calcClick() {
        return (clickUpgrades[0] * 10) + (clickUpgrades[1] * 100) + (clickUpgrades[2] * 500)
                + (clickUpgrades[3] * 1000) + (clickUpgrades[4] * 10000);
    }

    private void generate() {
        coconuts += calcGen();
        totalCoconuts += calcGen();
        activity.updateView();
    }

    int calcGen() {
        return (genUpgrades[0] * 10) + (genUpgrades[1] * 100) + (genUpgrades[2] * 500)
                + (genUpgrades[3] * 1000) + (genUpgrades[4] * 10000);
    }


    int getCoconuts() {
        return coconuts;
    }

    private void setPrevCoconuts(int curCoconuts){
        this.prevCoconuts = curCoconuts;
    }

    private int getRate(){
        return coconuts - prevCoconuts;
    }

    private int getMaxRate(){
        return maxRate;
    }

    private void setMaxRate(int newMaxRate){
        this.maxRate = newMaxRate;
    }

    private void rateIncreased(){
        Snackbar.make(activity.getCurrentActivity().findViewById(android.R.id.content).getRootView(),
                "You've generated more coconuts than ever! :)", Snackbar.LENGTH_SHORT).show();
    }

    boolean canAfford(String str) {
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
        return Objects.equals(str, "g5") && coconuts >= 1000000;
    }

    void subtractCoconuts(int cost) {
        coconuts = coconuts - cost;
        coconutsSpent += cost;
    }

    int getCoconutsSpent() {
        return coconutsSpent;
    }

    int getTimesClicked() {
        return timesClicked;
    }

    int[] getClickUpgrades() {
        return clickUpgrades;
    }

    int[] getGenUpgrades() {
        return genUpgrades;
    }

    interface TreeListener {
        void updateView();
        Activity getCurrentActivity();
    }

    private class TreeAsyncTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            int interval = 1000;

            while (running) {
                try {
                    int rateIncreased = 0;
                    if(MainActivity.tree.getRate() > MainActivity.tree.getMaxRate()){
                        rateIncreased = 1;
                        MainActivity.tree.setMaxRate(MainActivity.tree.getRate());
                    }
                    MainActivity.tree.setPrevCoconuts(MainActivity.tree.getCoconuts());
                    publishProgress(rateIncreased);
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
            if(values[0] == 1) {
                MainActivity.tree.rateIncreased();
            }
            MainActivity.tree.generate();
            super.onProgressUpdate(values);
        }
    }
}
