package com.twodays.jungleclicker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
            //TODO create stats screen
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
            if(tree.canAfford("c1")){
                Log.d("test","c1 is true");
                tree.clickUpgrades[1]++;
                tree.subtractCoconuts(10);
                Toast.makeText(getApplicationContext(), "Snake Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Snake", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gupgrade2) {
            if(tree.canAfford("c2")){
                tree.clickUpgrades[2]++;
                tree.subtractCoconuts(100);
                Toast.makeText(getApplicationContext(), "Giraffe Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Giraffe", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gupgrade3) {
            if(tree.canAfford("c3")){
                tree.clickUpgrades[3]++;
                tree.subtractCoconuts(1000);
                Toast.makeText(getApplicationContext(), "Tiger Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Tiger", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gupgrade4) {
            if(tree.canAfford("c4")){
                tree.clickUpgrades[4]++;
                tree.subtractCoconuts(10000);
                Toast.makeText(getApplicationContext(), "Parrot Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Parrot", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade1) {
            if(tree.canAfford("g1")){
                tree.genUpgrades[0]++;
                tree.subtractCoconuts(100);
                Toast.makeText(getApplicationContext(), "Gloves Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Gloves", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade2) {
            if(tree.canAfford("g2")){
                tree.genUpgrades[1]++;
                tree.subtractCoconuts(1000);
                Toast.makeText(getApplicationContext(), "Shovel Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Shovel", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_cupgrade3) {
            if(tree.canAfford("g3")){
                tree.genUpgrades[2]++;
                tree.subtractCoconuts(10000);
                Toast.makeText(getApplicationContext(), "Chainsaw Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Chainsaw", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_cupgrade4) {
            if(tree.canAfford("g4")){
                tree.genUpgrades[3]++;
                tree.subtractCoconuts(100000);
                Toast.makeText(getApplicationContext(), "Bulldozer Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Bulldozer", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_cupgrade5) {
            if(tree.canAfford("g5")){
                tree.genUpgrades[4]++;
                tree.subtractCoconuts(1000000);
                Toast.makeText(getApplicationContext(), "Excavator Purchased", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getApplicationContext(), "Cannot Afford Excavator", Toast.LENGTH_SHORT).show();
        }
        updateView();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateView() {
        mTextViewCoconuts.setText("Coconuts: " + formatter.format(tree.getCoconuts()));
        mTextViewClickAmt.setText(formatter.format(tree.calcClick()) + " per click");
        mTextViewGenAmt.setText(formatter.format(tree.calcGen()) + " per second");
    }
}
