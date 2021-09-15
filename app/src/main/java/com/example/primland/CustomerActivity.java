package com.example.primland;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.primland.model.CustomerInfo;
import com.example.primland.model.CustomerInfoVIewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

public class CustomerActivity extends AppCompatActivity {
//    private ImageView back_btn;
    private CustomerInfoVIewModel customerInfoVIewModel;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_customer);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);

//        back_btn = findViewById(R.id.customer_back_btn);
        customerInfoVIewModel  = new ViewModelProvider(this).get(CustomerInfoVIewModel.class);



//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CustomerActivity.this,DashBoardActivity.class));
//
//            }
//        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton_customer);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerActivity.this,CustomerInfoActivity.class));
            }
        });
    }
}