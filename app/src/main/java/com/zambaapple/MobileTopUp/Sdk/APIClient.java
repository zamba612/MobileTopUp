package com.zambaapple.MobileTopUp.Sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class APIClient {
public static String URL="https://webhostzamba.000webhostapp.com/wallets/ReloadlyAPI/";

    public static String URLU="https://webhostzamba.000webhostapp.com/wallets/Stripe/";

   public static Retrofit getClient() {
     //  https://abhiandroid.com/programming/volley
       Gson gson = new GsonBuilder()
               .setLenient()
               .create();

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(URL)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build();

        return retrofit;
    }

    public static Retrofit getStripeMethods() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLU)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

}