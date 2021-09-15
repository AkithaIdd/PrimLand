package com.example.primland.model;

import android.app.Application;

import com.example.primland.data.CustomerPhotoRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Akitha Iddamalgoda on 8/23/2021.
 **/
public class CustomerPhotoViewModel extends AndroidViewModel {

    public static CustomerPhotoRepository repository;
    public final LiveData<List<CustomerPhoto>> allphotos;


    public CustomerPhotoViewModel(@NonNull Application application) {
        super(application);
        repository = new CustomerPhotoRepository(application);
        allphotos = repository.getAllData();
    }

    public LiveData<List<CustomerPhoto>> getAllphotos() {
        return allphotos;
    }

    public static void insertPhoto(CustomerPhoto customerPhoto){
        repository.insertPhoto(customerPhoto);
    }
}
