package com.example.primland;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.primland.api.ApiClient;
import com.example.primland.model.db.CustomerResponse;
import com.example.primland.model.db.LoginResponse;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {

    private ImageView sign_out_btn;
    private CardView cus_btn, unsold_btn, reser_btn , pay_btn, deposit_btn, transfer_btn, report_btn;
    private LinearLayout customers_btn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);

        customers_btn = findViewById(R.id.customer_btn);
//        sign_out_btn = findViewById(R.id.dash_board_sign_out_btn);
        cus_btn = findViewById(R.id.custom_card);
        unsold_btn = findViewById(R.id.unsold_card);
        toolbar = findViewById(R.id.toolbar1);
        reser_btn = findViewById(R.id.reservation_card);
        pay_btn = findViewById(R.id.payment_card);
        deposit_btn = findViewById(R.id.deposit_card);
        transfer_btn = findViewById(R.id.tansfer_card);
        report_btn = findViewById(R.id.report_card);
        //these lines add in to oncreate method in ur dashboard activity
        //toolbar id form dashboard tool bar
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        //this line use to hide title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        customers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, CustomerActivity.class));

            }
        });
//        sign_out_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertBox();
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //get SP data
        SharedPreferences getSharedPreferences = getSharedPreferences(MainActivity.TOKEN_ID,MODE_PRIVATE);
        String token_type = getSharedPreferences.getString("token_type","");
        String access_token = getSharedPreferences.getString("access_token" , "");

        Log.d("token", "onCreate: " + token_type);

        Call<CustomerResponse> customerResponseCall = ApiClient.getUserService().getCustomerModule(token_type + " " + access_token);



        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(@NotNull Call<CustomerResponse> call, @NotNull Response<CustomerResponse> response) {

                CustomerResponse customerResponse = response.body();

                if (CustomerResponse.SecurityModule.class != null && customerResponse.moduleLists != null) {



                for (CustomerResponse.SecurityModule securityModule : customerResponse.moduleLists) {

                    switch (securityModule.uMobiScreen) {
                        case "Customer":
                            if (securityModule.uAuthType.equals("Full Authrization")) {
                                cus_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                        case "Unsold List":
                            if (securityModule.uAuthType.equals("Full Authrization")) {
                                unsold_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                        case "Reservation":
                            if (securityModule.uAuthType.equals("Fulla Authrization")) {
                                reser_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                        case "Payments":
                            if (securityModule.uAuthType.equals("Full Authrization")) {
                                pay_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                        case "Payments Deposit":
                            if (securityModule.uAuthType.equals("Full Authrization")) {
                                deposit_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                        case "Transfer to ASM":
                            if (securityModule.uAuthType.equals("Full Authrization")) {
                                transfer_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                        case "Daily All Transaction Report":
                            if (securityModule.uAuthType.equals("Full Authrization")) {
                                report_btn.setVisibility(View.VISIBLE);
                                break;
                            }
                    }
                }
                } else {
                    cus_btn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });



    }

    private void alertBox() {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are you sure want to log out ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(DashBoardActivity.this, MainActivity.class));
                        finish();

                    }
                })
                .setNegativeButton("NO", null)
                .show();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_logout) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Are you sure want to log out ?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(DashBoardActivity.this, MainActivity.class));
                            finish();

                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_menu,menu);
        return true;
    }
}