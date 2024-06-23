package com.zoss.petanikita;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Article extends Fragment {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private BottomAppBar bottomAppBar;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;
    private ViewPager2 viewPager;

    private TextView articleTitle;
    private ImageView articleImage;
    private TextView articleContent;

    // Data artikel yang akan ditampilkan
    private String[] articleTitles = {"Super KAYA", "PETANI UNTUNG 20JT ??", "SUPLAI JERUK HINGGA 1,2 TON ??"};
    private int[] articleImages = {R.drawable.artikel1, R.drawable.artikel2, R.drawable.artikel3};
    private String[] articleContents = {
            "Padang, 20 Juni 2018 – Sandi Octa Susila, yang dikenal sebagai petani milenial sukses, kembali menjadi sorotan. Dengan semangat dan dedikasi tinggi, Sandi tidak hanya berhasil dalam bidang pertanian tetapi juga diangkat sebagai Duta Petani Milenial",
            "Jakarta, 23 Juni 2017 – Wisnu Saepudin, seorang petani muda yang sukses, kembali menarik perhatian dengan pencapaiannya yang luar biasa. Dengan keuntungan bersih sebesar 20 juta rupiah per bulan, Wisnu menjadi contoh inspiratif bagi banyak orang",
            "Solo, 12 Juli 2019 – Rizal Fahreza, seorang petani muda yang sukses, dikenal sebagai penyedia jeruk terbesar dengan pasokan harian mencapai 1,2 ton atau setara dengan 400 dus. Kesuksesannya dalam sektor pertanian buah-buahan membuat Rizal.."
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article, container, false);

        // Inisialisasi komponen
        drawerLayout = view.findViewById(R.id.drawer_layout);
        toolbar = view.findViewById(R.id.toolbar);
        navigationView = view.findViewById(R.id.nav_article);
        bottomAppBar = view.findViewById(R.id.bottomAppBar);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        fab = view.findViewById(R.id.fab);
        viewPager = view.findViewById(R.id.viewPager);

        // Setup ViewPager2
        ArticleSlideAdapter adapter = new ArticleSlideAdapter(articleTitles, articleImages, articleContents);
        viewPager.setAdapter(adapter);

        // Setup click listener untuk NavigationView
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.article1) {
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.article2) {
                    viewPager.setCurrentItem(1);
                } else if (id == R.id.article3) {
                    viewPager.setCurrentItem(2);
                }
                drawerLayout.closeDrawers(); // Tutup drawer setelah item dipilih
                return true;
            }
        });

        return view;
    }
}