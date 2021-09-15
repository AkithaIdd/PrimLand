package com.example.primland.data;

import android.app.Application;


import com.example.primland.model.CustomerPhoto;
import com.example.primland.util.CustomerInfoRoomDB;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by Akitha Iddamalgoda on 8/23/2021.
 **/
public class CustomerPhotoRepository {

    private CustomerPhotoDao customerPhotoDao;
    private LiveData<List<CustomerPhoto>> allCustomersphoto;

    public CustomerPhotoRepository(Application application) {

        CustomerInfoRoomDB db = CustomerInfoRoomDB.getDatabase(application);
        customerPhotoDao = db.customerPhotoDao();

        allCustomersphoto = customerPhotoDao.getAllContactsphoto();


    }

    public LiveData<List<CustomerPhoto>> getAllData(){
        return allCustomersphoto;
    }

    public void insertPhoto(CustomerPhoto customerPhoto){
        CustomerInfoRoomDB.databaseWriteExecutor.execute(()->{
            customerPhotoDao.addCustomerImage(customerPhoto);
        });
    }
}
