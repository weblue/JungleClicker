package com.twodays.jungleclicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Tree.TreeListener {

    public static Tree tree;
    private DecimalFormat formatter;
    private TextView mTextViewCoconuts, mTextViewClickAmt, mTextViewGenAmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tree = new Tree(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        formatter = new DecimalFormat("###,###,###,###");
        mTextViewCoconuts = (TextView) findViewById(R.id.tv_coconuts);
        mTextViewClickAmt = (TextView) findViewById(R.id.tv_perclick);
        mTextViewGenAmt = (TextView) findViewById(R.id.tv_persecond);

        mTextViewCoconuts.setText(formatter.format(tree.getCoconuts()));

        ImageView imageViewTree = (ImageView) findViewById(R.id.iv_tree);
        assert imageViewTree != null;
        imageViewTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shift));
                tree.click();
            }
        });

        updateView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gupgrade1) {
            if (tree.canAfford("c1")) {
                Log.d("test", "c1 is true");
                tree.clickUpgrades[1]++;
                tree.subtractCoconuts(10);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Snake Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Snake", Snackbar.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gupgrade2) {
            if (tree.canAfford("c2")) {
                tree.clickUpgrades[2]++;
                tree.subtractCoconuts(100);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Giraffe Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Giraffe", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gupgrade3) {
            if (tree.canAfford("c3")) {
                tree.clickUpgrades[3]++;
                tree.subtractCoconuts(1000);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Tiger Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Tiger", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gupgrade4) {
            if (tree.canAfford("c4")) {
                tree.clickUpgrades[4]++;
                tree.subtractCoconuts(10000);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Parrot Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Parrot", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade1) {
            if (tree.canAfford("g1")) {
                tree.genUpgrades[0]++;
                tree.subtractCoconuts(100);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Gloves Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Gloves", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade2) {
            if (tree.canAfford("g2")) {
                tree.genUpgrades[1]++;
                tree.subtractCoconuts(1000);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Shovel Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Shovel", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade3) {
            if (tree.canAfford("g3")) {
                tree.genUpgrades[2]++;
                tree.subtractCoconuts(10000);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Chainsaw Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Chainsaw", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade4) {
            if (tree.canAfford("g4")) {
                tree.genUpgrades[3]++;
                tree.subtractCoconuts(100000);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Bulldozer Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Bulldozer", Snackbar.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade5) {
            if (tree.canAfford("g5")) {
                tree.genUpgrades[4]++;
                tree.subtractCoconuts(1000000);
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Excavator Purchased", Snackbar.LENGTH_SHORT).show();
            } else
                Snackbar.make(findViewById(android.R.id.content).getRootView(), "Cannot Afford Excavator", Snackbar.LENGTH_SHORT).show();
        }
        updateView();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateView() {
        tree.save();
        mTextViewCoconuts.setText("Coconuts: " + formatter.format(tree.getCoconuts()));
        mTextViewClickAmt.setText(formatter.format(tree.calcClick()) + " per click");
        mTextViewGenAmt.setText(formatter.format(tree.calcGen()) + " per second");
    }

    public Activity getCurrentActivity() {
        return this;
    }

}
