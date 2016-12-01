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

    public Tree(TreeListener activity) {
        this.activity = activity;

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
//                while(!reset){
//                    if(!clockStopped){
                        try {
                            int coconuts = MainActivity.tree.getCoconuts();
                            publishProgress(coconuts);
                            Thread.sleep(interval);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
//                    }
//                }
                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                /*String restartTime = getTime(0, 0, 0);
                time.setText(restartTime);
                clock.setHours(0);
                clock.setMinutes(0);
                clock.setSeconds(0);*/
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

                /*String curTime = getTime(values[0], values[1], values[2]);
                time.setText(curTime);*/
                super.onProgressUpdate(values);
            }
        }
    }
}
