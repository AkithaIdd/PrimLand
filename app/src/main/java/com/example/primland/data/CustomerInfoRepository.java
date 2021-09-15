package com.example.primland.data;

import android.app.Application;

import com.example.primland.model.CustomerInfo;
import com.example.primland.util.CustomerInfoRoomDB;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by Akitha Iddamalgoda on 8/13/2021.
 **/
public class CustomerInfoRepository {

    private CustomerInfoDao customerInfoDao;
    private LiveData<List<CustomerInfo>> allCustomers;
    private LiveData<CustomerInfo> code;

    public CustomerInfoRepository(Application application) {

        CustomerInfoRoomDB db = CustomerInfoRoomDB.getDatabase(application);
        customerInfoDao = db.customerInfoDao();

        allCustomers = customerInfoDao.getAllContacts();
    }


    //get all data
    public LiveData<List<CustomerInfo>> getAllData(){
        return allCustomers;
    }


    //create
    public void insert(CustomerInfo customerInfo){
        CustomerInfoRoomDB.databaseWriteExecutor.execute(() -> {
            customerInfoDao.insert(customerInfo);
        });
    }

    public LiveData<CustomerInfo> getCode(){
        return code;
    }


}
