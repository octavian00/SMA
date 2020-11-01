package com.example.tema1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tema1.ui.dashboard.DashboardFragment;
import com.example.tema1.ui.home.HomeFragment;
import com.example.tema1.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.Serializable;

public class BottomNavigationExampleActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, Serializable {
    private DashboardFragment dashboardFragment;
    private HomeFragment homeFragment;
    private NotificationsFragment notificationsFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment activeFragment;
    private BottomNavigationView navView;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_example);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        getData();
        initialize();
        LoadFragment();
    }

    private void initialize(){
        dashboardFragment = new DashboardFragment();
        homeFragment = new HomeFragment();
        notificationsFragment = new NotificationsFragment();
        fragmentManager = getSupportFragmentManager();
        activeFragment = homeFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                activeFragment = homeFragment;
                return true;

            case R.id.navigation_dashboard:
                fragmentManager.beginTransaction().hide(activeFragment).show(dashboardFragment).commit();
                activeFragment = dashboardFragment;
                return true;

            case R.id.navigation_notifications:
                fragmentManager.beginTransaction().hide(activeFragment).show(notificationsFragment).commit();
                activeFragment = notificationsFragment;
                return true;
        }
        return false;
    }

    private void LoadFragment() {
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, notificationsFragment, "3").hide(notificationsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, dashboardFragment, "2").hide(dashboardFragment).commit();
        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, homeFragment, "1").commit();
    }

    private void getData(){
        Intent intent= getIntent();
        username = intent.getStringExtra("username");
    }
}