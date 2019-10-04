package com.nts.newspapersbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView textToolbar;
    WebViewFragment webViewFragment = new WebViewFragment();
    AboutFragment aboutFragment = new AboutFragment();
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");

        init();

//        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
//        adView.loadAd(adRequest);

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);

        if (savedInstanceState == null) {
            setFragment(new BanglaFragment());
            navigationView.setCheckedItem(R.id.nav_Bangla);
        }
    }

    private void init() {
        drawer = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        textToolbar = findViewById(R.id.textViewToolbar);
        navigationView = findViewById(R.id.navigationView);
        //adView = findViewById(R.id.adView);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
        drawer.closeDrawers();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_Bangla:
                setFragment(new BanglaFragment());
                textToolbar.setText("Bangla Newspapers");
                return true;
            case R.id.nav_English:
                setFragment(new EnglishFragment());
                textToolbar.setText("English Newspapers");
                return true;
            case R.id.nav_Online:
                setFragment(new OnlineFragment());
                textToolbar.setText("Online Newspapers");
                return true;
            case R.id.aboutInfo:
                setFragment(new AboutFragment());
                textToolbar.setText("BD Newspapers");
                return true;
            case R.id.exit:
                exitSystem();
                drawer.closeDrawers();
                return true;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void exitSystem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.exit_alert_dialog, null);
        builder.setView(v);
        final Dialog dialog = builder.create();
        dialog.show();

        TextView yes = v.findViewById(R.id.yesAlertTV);
        TextView no = v.findViewById(R.id.noAlertTV);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (fragment instanceof WebViewFragment){
            ((WebViewFragment) fragment).getFragmentManager().popBackStack();
        }
        else if(fragment instanceof AboutFragment){
            ((AboutFragment) fragment).startActivity(new Intent(this,MainActivity.class));
        }
        else {
            exitSystem();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_rateApp:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + "com.android.chrome")));
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?=" +getPackageName())));
                }
                break;
            case R.id.menu_about:
                setFragment(new AboutFragment());
                textToolbar.setText("BD Newspapers");
                default:
        }

        return super.onOptionsItemSelected(item);
    }
}
