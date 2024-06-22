package com.example.yourapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomAppBar bottomAppBar;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;
    private TextView articleTitle;
    private ImageView articleImage;
    private TextView articleContent;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_menu);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        articleTitle = findViewById(R.id.article_title);
        articleImage = findViewById(R.id.article_image);
        articleContent = findViewById(R.id.article_content);
        btnLogout = findViewById(R.id.btnlogout);

        setSupportActionBar(toolbar);

        // Set article data
        articleTitle.setText("Your Article Title");
        articleContent.setText("Your article content goes here...");
        articleImage.setImageResource(R.drawable.article_image);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout button click
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle FAB click
            }
        });

        // Setup bottom navigation view click listeners if needed
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_item1:
                    // Handle nav item 1 click
                    return true;
                case R.id.nav_item2:
                    // Handle nav item 2 click
                    return true;
                case R.id.nav_item3:
                    // Handle nav item 3 click
                    return true;
                default:
                    return false;
            }
        });
    }
}
