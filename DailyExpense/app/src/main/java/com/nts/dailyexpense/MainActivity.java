package com.nts.dailyexpense;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    replaceFragment(new DashboardFragment());
                    return true;
                case R.id.navigation_expenses:
                    replaceFragment(new ExpenseFragment());
                    return true;
            }
            return false;
        }
    };

    public void replaceFragment(Fragment fragment){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        replaceFragment(new DashboardFragment());
    }
}
