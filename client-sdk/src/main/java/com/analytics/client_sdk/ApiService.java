package com.analytics.client_sdk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/track")
    Call<Void> trackAction(@Body UserAction action);
}
