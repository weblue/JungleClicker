package com.twodays.jungleclicker;

import android.os.AsyncTask;

/**
 * Created by nader on 11/30/16.
 */

public class Tree {

    private int coconuts;
    int[] clickUpgrades = {1, 0, 0, 0, 0};
    int[] genUpgrades = {0, 0, 0, 0, 0};
    private TreeListener activity;
    private TreeListener.TreeAsyncTask treeAsyncTask;

    public Tree(TreeListener activity) {
        this.activity = activity;

        treeAsyncTask = new TreeListener.TreeAsyncTask();
        treeAsyncTask.execute();

        coconuts = 0;

        load();


    }

    public void save() {

    }

    public void load() {

    }

    public void click() {
        coconuts += calcClick();

        activity.updateView();
    }

    public int calcClick() {
        return (clickUpgrades[0] * 10) + (clickUpgrades[1] * 100) + (clickUpgrades[2] * 500)
                + (clickUpgrades[3] * 1000) + (clickUpgrades[4] * 10000);
    }

    public void generate() {
        coconuts += calcGen();
        activity.updateView();
    }

    public int calcGen() {
        return (genUpgrades[0] * 10) + (genUpgrades[1] * 100) + (genUpgrades[2] * 500)
                + (genUpgrades[3] * 1000) + (genUpgrades[4] * 10000);
    }


    public int getCoconuts() {
        return coconuts;
    }

    public interface TreeListener {
        void updateView();

        public class TreeAsyncTask extends AsyncTask<Integer, Integer, Void> {
            @Override
            protected Void doInBackground(Integer... params) {
                int interval = 1000;

                        try {
                            publishProgress();
                            Thread.sleep(interval);
                        } catch (Exception ex) {
                            ex.printStackTrace();
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
    public boolean canAfford(String str){
        if(str == "c1"){
            if(coconuts>=10) return true;
            else return false;
        }
        if (str=="c2"){
            if(coconuts>=100) return true;
            else return false;
        }
        if (str=="c3"){
            if(coconuts>=1000) return true;
            else return false;
        }
        if (str=="c4"){
            if(coconuts>=10000) return true;
            else return false;
        }
        if (str=="g1"){
            if(coconuts>=100) return true;
            else return false;
        }
        if (str=="g2"){
            if(coconuts>=1000) return true;
            else return false;
        }
        if (str=="g3"){
            if(coconuts>=10000) return true;
            else return false;
        }
        if (str=="g4"){
            if(coconuts>=100000) return true;
            else return false;
        }
        if (str=="g5"){
            if(coconuts>=1000000) return true;
            else return false;
        }
        return false;
    }
    public void subtractCoconuts(int cost){
        coconuts = coconuts - cost;
    }
}
