package com.analytics.client_sdk;

import android.content.Context;
import android.util.Log;

import java.time.LocalDateTime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalyticsTracker {

    private static String userId;
    private static ApiService api;

    // ⬅️ זו המתודה החסרה שגרמה לשגיאה
    public static void init(Context context, String uid) {
        userId = uid;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.152/") // כתובת השרת שלך
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);
    }

    public static void trackAction(String actionName) {
        UserAction action = new UserAction(actionName, userId, LocalDateTime.now().toString());
        api.trackAction(action).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("AnalyticsTracker", "Action sent!");
            }

            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("AnalyticsTracker", "Failed to send action", t);
            }
        });
    }
}
