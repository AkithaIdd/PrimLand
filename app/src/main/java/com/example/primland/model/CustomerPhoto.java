package com.example.primland.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Akitha Iddamalgoda on 8/23/2021.
 **/
@Entity(tableName = "tbl_customer_image")
public class CustomerPhoto {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long customerCode;
    private String path;

//    @Ignore
//    public CustomerPhoto() {
//    }

    public CustomerPhoto(long customerCode, String path) {
        this.customerCode = customerCode;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        this.customerCode = customerCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
