package com.example.ambb.Search;


import com.example.ambb.Search.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

  @GET("bins/lwn3s")
  Call<JSONResponse> getJSON();
}

