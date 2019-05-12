package com.example.ambb.Search;


import com.example.ambb.Search.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

  @GET("bins/177ruu")
  Call<JSONResponse> getJSON();

  //@GET("bins/103mem")
  //Call<JSONResponse> getDetail(@Query("name") String serverProductName);

}

