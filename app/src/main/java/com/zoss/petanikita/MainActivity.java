package com.zoss.petanikita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
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
        navigationView = findViewById(R.id.nav_menu);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openNav, R.string.closeNav);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id==R.id.home) {
                    Toast.makeText(MainActivity.this, "Home Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.schedule) {
                    Toast.makeText(MainActivity.this, "Schedule Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.maps) {
                    Toast.makeText(MainActivity.this, "Maps Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.tutorial) {
                    Toast.makeText(MainActivity.this, "Tutorial Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.article) {
                    Toast.makeText(MainActivity.this, "Article Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.qna) {
                    Toast.makeText(MainActivity.this, "QnA Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.disc) {
                    Toast.makeText(MainActivity.this, "Discussion Selected!", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.forum) {
                    Toast.makeText(MainActivity.this, "Discussion Forum Selected!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        TextView textName = headerView.findViewById(R.id.name);
        Button btnlogout = headerView.findViewById(R.id.btn_logout);
//        btnlogout = findViewById(R.id.btn_logout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null) {
            textName.setText(firebaseUser.getDisplayName());
        }else {
            textName.setText("Login gagal!");
        }

        btnlogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
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