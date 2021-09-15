package com.example.primland.model;

import android.app.Application;
import android.util.Log;

import com.example.primland.data.CustomerInfoRepository;
import androidx.annotation.NonNull;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Akitha Iddamalgoda on 8/13/2021.
 **/
public class CustomerInfoVIewModel extends AndroidViewModel {

    public static CustomerInfoRepository repository;
    public final LiveData<List<CustomerInfo>> allCustomers;
    private LiveData<CustomerInfo> code;


    public CustomerInfoVIewModel(@NonNull Application application) {
        super(application);
        repository = new CustomerInfoRepository(application);
        allCustomers = repository.getAllData();
    }

    //get all from repo
    public LiveData<List<CustomerInfo>> getAllCustomers() {return allCustomers;}

    //insert to repo
    public static void insert(CustomerInfo customerInfo) {
        repository.insert(customerInfo);
    }

    public LiveData<CustomerInfo> getCode(){
        return code;
    }





}
