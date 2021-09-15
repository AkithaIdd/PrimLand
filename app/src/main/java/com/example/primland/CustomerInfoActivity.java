package com.example.primland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.primland.adapter.VPAdapter;
import com.example.primland.fragment.InfoFragment;
import com.example.primland.fragment.PhotoFragment;
import com.example.primland.fragment.ReservationFragment;
//import com.example.primland.model.CustomerInfoVIewModel;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class CustomerInfoActivity extends AppCompatActivity {

   private TabLayout tabLayout;
   private ViewPager viewPager;
   private ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_customer_info);

       tabLayout = findViewById(R.id.tabs);
       viewPager = findViewById(R.id.pager);

       tabLayout.setupWithViewPager(viewPager);

        back_btn = findViewById(R.id.customer_info_back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerInfoActivity.this, com.example.primland.CustomerActivity.class));
            }
        });



        //create tabview

       VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager()
               , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

       vpAdapter.addFragment(new  InfoFragment(),"Info");
       vpAdapter.addFragment(new PhotoFragment(),"Photo");
       vpAdapter.addFragment(new ReservationFragment(),"Reservation");
       viewPager.setAdapter(vpAdapter);



    }
}