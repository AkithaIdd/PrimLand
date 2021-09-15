package com.example.primland.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.primland.R;
import com.example.primland.SplashScreen;
import com.example.primland.data.CustomerInfoDao;
import com.example.primland.model.CustomerInfo;
import com.example.primland.model.CustomerInfoVIewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;


public class InfoFragment extends Fragment {

    private EditText name,nic,mobile,address,info,email;
    private Button save,cancel;
    private CustomerInfoVIewModel customerInfoVIewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        name = view.findViewById(R.id.name_edit_id);
        nic = view.findViewById(R.id.nic_edit_id);
        mobile = view.findViewById(R.id.mobile_edit_id);
        address = view.findViewById(R.id.address_edit_id);
        info = view.findViewById(R.id.info_edit_id);
        email = view.findViewById(R.id.email_edit_id);

        save = view.findViewById(R.id.save_btn_id);

        //initializing viewmodle in a fragment
        customerInfoVIewModel  = new ViewModelProvider(this).get(CustomerInfoVIewModel.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCustomer();


            }
        });

        return view;


    }

    public void addCustomer() {
        String enterName,enterEmail,enterNIC,enterAddress,enterInfo,enterMobile;

        enterName = name.getText().toString().trim();
        enterEmail = email.getText().toString().trim();
        enterNIC = nic.getText().toString().trim();
        enterAddress = address.getText().toString().trim();
        enterInfo = info.getText().toString().trim();
        enterMobile = mobile.getText().toString().trim();

        CustomerInfo customerInfo = new CustomerInfo(enterName,enterMobile,enterEmail,enterAddress,enterInfo,enterNIC);// customerinfo constructor

        Log.d("photo", "addCustomer: " + customerInfo.getCode());
        CustomerInfoVIewModel.insert(customerInfo);
        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
        


    }

    public InfoFragment() {
        // Required empty public constructor
    }

}