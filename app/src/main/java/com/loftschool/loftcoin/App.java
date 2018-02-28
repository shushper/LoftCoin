package com.loftschool.loftcoin;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit.Builder builder = new Retrofit.Builder();

        Retrofit retrofit = builder
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }
}
