package com.example.primland;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

//import com.example.primland.model.CustomerInfoVIewModel;

import com.example.primland.model.CustomerInfo;
import com.example.primland.model.CustomerInfoVIewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class SplashScreen extends AppCompatActivity {

private CustomerInfoVIewModel customerInfoVIewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        customerInfoVIewModel = new ViewModelProvider.AndroidViewModelFactory(SplashScreen.this
                .getApplication())
                .create(CustomerInfoVIewModel.class);

        customerInfoVIewModel.getAllCustomers().observe(this, customerInfos -> {
            for (CustomerInfo customerInfo : customerInfos){
                Log.d("test", "onCreate: " + customerInfo.getName());
            }
//            Log.d("test", "onChanged: "+ customerInfos.get(0).getName());

        });


        new Handler().postDelayed(new Runnable() {  //delay main activity
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        },3000);
    }
}