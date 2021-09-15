package com.example.primland.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Akitha Iddamalgoda on 8/12/2021.
 **/
@Entity(tableName = "customer_info_table1")
public class CustomerInfo {
    @PrimaryKey(autoGenerate = true)
    private int code;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "mobile")
    private String mobile;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "nic")
    private String nic;


//    public CustomerInfo() {
//    }

    public CustomerInfo(@NonNull String name, String mobile, String email, String address, String info, String nic) {

        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.info = info;
        this.nic = nic;

        Log.d("test1", "CustomerInfo: " + getName());
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
