package com.example.primland.api;




import com.example.primland.model.db.CustomerResponse;

import com.example.primland.model.db.LoginResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by Akitha Iddamalgoda on 8/18/2021.
 **/
public interface UserService {


    @FormUrlEncoded
    @POST("/token")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<LoginResponse> userLogin(@FieldMap Map<String , String> options);

    @GET("/api/mobilerequest/v2/GetUserModule")
    Call<CustomerResponse> getCustomerModule(@Header("Authorization")String token);


}
