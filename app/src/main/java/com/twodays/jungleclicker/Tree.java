package com.twodays.jungleclicker;

/**
 * Created by nader on 11/30/16.
 */

public class Tree {

    int coconuts;
    int[] clickUpgrades = {0, 0, 0, 0, 0};
    int[] genUpgrades = {0, 0, 0, 0, 0};

    public Tree() {
        coconuts = 0;

        load();
    }

    public void save() {

    }

    public void load() {

    }

    public void inc(int amount) {
        coconuts += amount;
    }


}