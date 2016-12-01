package com.twodays.jungleclicker;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Objects;

/**
 * Created by nader on 11/30/16.
 */

public class Tree {

    int[] clickUpgrades = {1, 0, 0, 0, 0};
    int[] genUpgrades = {1, 0, 0, 0, 0};
    private int totalCoconuts;
    private int timesClicked;
    private int coconutsSpent;
    private int coconuts;
    private TreeListener activity;
    private boolean running;

    public Tree(TreeListener activity) {
        this.activity = activity;

        coconuts = 0;

        totalCoconuts = 0;
        timesClicked = 0;
        coconutsSpent = 0;

        load();

        running = true;
        TreeAsyncTask treeAsyncTask = new TreeAsyncTask();
        treeAsyncTask.execute();
    }

    public void save() {
        //TODO
    }

    public void load() {

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
        Context getContext();
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
