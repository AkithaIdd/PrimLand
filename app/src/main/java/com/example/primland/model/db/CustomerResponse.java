package com.example.primland.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Akitha Iddamalgoda on 8/18/2021.
 **/
public class CustomerResponse {
    @SerializedName("SecurityModuleList")
    @Expose
    public List<SecurityModule> moduleLists;

    @SerializedName("Id")
    @Expose
    public int id;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("Code")
    @Expose
    public String code;

    @SerializedName("Role")
    @Expose
    public String role;

    public List<SecurityModule> getModuleLists() {
        return moduleLists;
    }

    public void setModuleLists(List<SecurityModule> moduleLists) {
        this.moduleLists = moduleLists;
    }

    public  class SecurityModule {

        @SerializedName("LineId")
        @Expose
        public int lineId;

        @SerializedName("U_MOBSCREEN")
        @Expose
        public  String uMobiScreen;

        @SerializedName("U_AuthType")
        @Expose
        public String uAuthType;

    }
}
