package com.ingrid.mundoanimal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.fragments.main.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setHomeButtonEnabled(true);

        toggle.syncState();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragments_container, new MainFragment())
                .commit();

        NavigationView menuNavigationView = (NavigationView) findViewById(R.id.nav_view);
        menuNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Boolean result = false;
        int itemId = item.getItemId();

        if (itemId == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
            result = true;
        } else if (itemId == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            result = true;
        }else if(itemId == R.id.menu_help){
            startActivity(new Intent(this, HelpActivity.class));
            result = true;
        }

        drawer.closeDrawers();

        return result;
    }

}