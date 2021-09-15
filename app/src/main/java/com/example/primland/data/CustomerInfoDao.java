package com.example.primland.data;

import com.example.primland.model.CustomerInfo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Akitha Iddamalgoda on 8/13/2021.
 **/
@Dao
public interface CustomerInfoDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CustomerInfo customerInfo);

    @Query("DELETE FROM customer_info_table1")
    void deteleAll();

    @Query("SELECT * FROM customer_info_table1 ORDER BY code DESC LIMIT 1")
    LiveData<List<CustomerInfo>> getAllContacts();

    @Query("SELECT * FROM customer_info_table1 WHERE customer_info_table1.code == :id")
    LiveData<CustomerInfo> get(int id);

    @Query("SELECT code FROM customer_info_table1 order by code desc limit 1")
    LiveData<CustomerInfo> getCode();



}
