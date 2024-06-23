package com.zoss.petanikita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private FirebaseUser firebaseUser;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navigationView = findViewById(R.id.nav_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openNav, R.string.closeNav);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.tutorial) {
                Toast.makeText(MainActivity.this, "Tutorial Selected!", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.article) {
                Toast.makeText(MainActivity.this, "Article Selected!", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.setting) {
                Toast.makeText(MainActivity.this, "Setting Selected!", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.share) {
                Toast.makeText(MainActivity.this, "Share Selected!", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.about) {
                Toast.makeText(MainActivity.this, "About Us Selected!", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.logout) {
                try {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true; // Changed to true to indicate the event was handled
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                // Handle home navigation
                Toast.makeText(MainActivity.this, "Home Selected!", Toast.LENGTH_SHORT).show();
                // Replace with your desired action for Home
                loadArticleFragment();
            } else if (id == R.id.schedule) {
                // Handle schedule navigation
                Toast.makeText(MainActivity.this, "Schedule Selected!", Toast.LENGTH_SHORT).show();
                // Replace with your desired action for Schedule
            } else if (id == R.id.maps) {
                // Load map fragment
                loadMapFragment();
            } else if (id == R.id.community) {
                // Load community fragment
                loadCommunityMenuFragment();
            }
            return true;
        });

        View headerView = navigationView.getHeaderView(0);
        TextView textName = headerView.findViewById(R.id.name);
        TextView textEmail = headerView.findViewById(R.id.email);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            String fullName = firebaseUser.getDisplayName();
            textEmail.setText(firebaseUser.getEmail());
            if (fullName != null && !fullName.isEmpty()) {
                String[] parts = fullName.split(" ");
                String firstName = parts[0];
                textName.setText(firstName);
            } else {
                textName.setText("ZoSS");
                textEmail.setText("Zone of Simple Site (ZoSS) Team");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadMapFragment() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, mapFragment)
                .commit();
        mapFragment.getMapAsync(googleMap -> {
            LatLng location = new LatLng(-0.91482985, 100.45880158);
            googleMap.addMarker(new MarkerOptions().position(location).title("Auditorium UNAND"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
        });
    }

    private void loadCommunityMenuFragment() {
        CommunityMenuFragment communityMenuFragment = new CommunityMenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, communityMenuFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void loadArticleFragment() {
        Article articleFragment = new Article();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, articleFragment)
                .commit();
    }
}
