package com.example.myapplication.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.PopularAdapter;
import com.example.myapplication.adapter.SlideAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.domain.CategoryDomain;
import com.example.myapplication.domain.ItemsDomain;
import com.example.myapplication.domain.SlideItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class MainActivity extends BaseActivity {
    public ActivityMainBinding binding; //tao binding goi toi cac view trong layout

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initBanner();
        initCategory();
        initPopular();
        bottomNavigation();
    }
    //set su kien cho thanh bottom navigation
    private void bottomNavigation() {
        bottomNavigationView = binding.bottomNavigationView;

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.btn_home) {
                    // Xử lý sự kiện khi nút "Trang chủ" được nhấn
                    Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    return true;
                } else if (id == R.id.btn_love) {
                    // Xử lý sự kiện khi nút "Yêu thích" được nhấn
                    Intent loveIntent = new Intent(MainActivity.this, LoveActivity.class);
                    startActivity(loveIntent);
                    return true;
                } else if (id == R.id.btn_cart) {
                    // Xử lý sự kiện khi nút "Giỏ hàng" được nhấn
                    Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                    startActivity(cartIntent);
                    return true;
                } else if (id == R.id.btn_setting) {
                    // Xử lý sự kiện khi nút "Cài đặt" được nhấn
                    Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(settingIntent);
                    return true;
                }
                return false;
            }
        });
    }


    private void initPopular() {
        DatabaseReference myRef = database.getReference("Items");
        binding.progressBarPopular.setVisibility(View.VISIBLE);

        ArrayList<ItemsDomain> items = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        items.add(issue.getValue(ItemsDomain.class));

                    }
                    if(!items.isEmpty()){
                        binding.recycleViewPopular.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        binding.recycleViewPopular.setAdapter(new PopularAdapter(items));

                    }
                    binding.progressBarOffical.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initCategory() {

        DatabaseReference myRef =  database.getReference("Category");
        binding.progressBarOffical.setVisibility(View.VISIBLE);
        ArrayList<CategoryDomain> items = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        items.add(issue.getValue(CategoryDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.recyclerViewOffical.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                                LinearLayoutManager.HORIZONTAL, false));
                        binding.recyclerViewOffical.setAdapter(new CategoryAdapter(items));
                    }
                    binding.progressBarOffical.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initBanner() {
        DatabaseReference myRef = database.getReference("Banner");
        binding.progressBarBanner.setVisibility(View.VISIBLE);
        ArrayList<SlideItem> items = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        items.add(issue.getValue(SlideItem.class));
                    }
                    banners(items);
                    binding.progressBarBanner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void banners(ArrayList<SlideItem> items){

        binding.viewpaperSlider.setAdapter(new SlideAdapter(items, binding.viewpaperSlider));
        binding.viewpaperSlider.setClipToPadding(false);
        binding.viewpaperSlider.setClipChildren(false);
        binding.viewpaperSlider.setOffscreenPageLimit(3);
        binding.viewpaperSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer =new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        binding.viewpaperSlider.setPageTransformer(compositePageTransformer);
    }



}