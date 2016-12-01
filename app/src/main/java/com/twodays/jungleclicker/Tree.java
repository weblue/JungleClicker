package com.twodays.jungleclicker;

/**
 * Created by nader on 11/30/16.
 */

public class Tree {

    private int coconuts;
    int[] clickUpgrades = {0, 0, 0, 0, 0};
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

        coconuts++;

        activity.updateView();
    }

    public void generate() {

        activity.updateView();
    }


    public int getCoconuts() {
        return coconuts;
    }

    public interface TreeListener {
        void updateView();
    }
}
