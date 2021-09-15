package com.example.primland;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.primland.api.ApiClient;
import com.example.primland.api.UserService;
import com.example.primland.model.db.CustomerResponse;
import com.example.primland.model.db.LoginRequest;
import com.example.primland.model.db.LoginResponse;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static final String TOKEN_ID = "token";
    private EditText username, password;
    private ProgressBar progressBar;
    public UserService service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        Button signIn = findViewById(R.id.sign_in_btn);




        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){

                    Toast.makeText(MainActivity.this, "Username And Password Required", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    login();
                }


            }
        });
    }

    private void login() {

        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("username",username.getText().toString().trim());
        hashMap.put("password",password.getText().toString().trim());
        hashMap.put("grant_type","password");


        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(hashMap);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    LoginResponse loginResponse = response.body();

                    //saving to SP
                    SharedPreferences sharedPreferences = getSharedPreferences(TOKEN_ID,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token_type",loginResponse.getToken_type());
                    editor.putString("access_token",loginResponse.getAccess_token());
                    editor.apply();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            assert loginResponse != null;
                            Intent i = new Intent(MainActivity.this,DashBoardActivity.class);
                            startActivity(i);
                            finish();

                        }
                    },700);
                }else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {

                Toast.makeText(MainActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}