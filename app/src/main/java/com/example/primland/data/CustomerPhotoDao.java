package com.example.primland.data;

import com.example.primland.model.CustomerPhoto;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by Akitha Iddamalgoda on 8/23/2021.
 **/

@Dao
public interface CustomerPhotoDao {

    @Insert
    public void addCustomerImage(CustomerPhoto customerPhoto);

    @Query("DELETE FROM tbl_customer_image")
    void deteleAll();

    @Query("SELECT * FROM tbl_customer_image ")
    LiveData<List<CustomerPhoto>> getAllContactsphoto();
}
