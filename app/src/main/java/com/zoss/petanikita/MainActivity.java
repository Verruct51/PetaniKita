package com.zoss.petanikita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private FirebaseUser firebaseUser;
    private Button btnlogout;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        navigationView = findViewById(R.id.nav_menu);
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openNav, R.string.closeNav);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Fragment()).commit();
//            navigationView.setCheckedItem(R.id.tutorial);
//        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();
                if (id==R.id.tutorial) {
                    Toast.makeText(MainActivity.this, "Tutorial Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.article) {
                    Toast.makeText(MainActivity.this, "Article Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.setting) {
                    Toast.makeText(MainActivity.this, "Setting Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.share) {
                    Toast.makeText(MainActivity.this, "Share Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.about) {
                    Toast.makeText(MainActivity.this, "About Us Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.logout) {
//                    Log.d(TAG, "Logout Item Clik");
                    try {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        View headerView = navigationView.getHeaderView(0);
        TextView textName = headerView.findViewById(R.id.name);
        TextView textEmail = headerView.findViewById(R.id.email);
//        Button btnlogout = headerView.findViewById(R.id.btn_logout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null) {
            String fullName = firebaseUser.getDisplayName();
            textEmail.setText(firebaseUser.getEmail());
            if (fullName != null && !fullName.isEmpty()) {
                // Split nama pengguna berdasarkan spasi dan ambil nama depan
                String[] parts = fullName.split(" ");
                String firstName = parts[0];
                // Set nama depan ke TextView
                textName.setText(firstName);
            } else {
                // Handle jika nama pengguna tidak valid
                textName.setText("ZoSS");
                textEmail.setText("Zone of Simple Site (ZoSS) Team");
            }
//            textName.setText(firebaseUser.getDisplayName());
        }
//        else {
//            textName.setText("Login gagal!");
//        }

//        MenuItem logoutItem = navigationView.getMenu().findItem(R.id.logout);
//        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(@NonNull MenuItem item) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
//                return true;
//            }
//        });

//        btnlogout.setOnClickListener(v -> {
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            finish();
//        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}