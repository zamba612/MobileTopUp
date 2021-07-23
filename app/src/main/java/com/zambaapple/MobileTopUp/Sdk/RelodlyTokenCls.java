package com.zambaapple.MobileTopUp.Sdk;



import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RelodlyTokenCls {
    Response response = null;
    public RelodlyTokenCls() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"client_id\":\"Aqs7NIjQgL09JOtbZlsl1kZe71YZv9Du\",\n\t\"client_secret\":\"9UYHh9bBkM-hz5Wynzxw9bShz4IIP1-aqefN12wnLMrMgHIcZ7TM9XMILGA1aOw\",\n\t\"grant_type\":\"client_credentials\",\n\t\"audience\":\"https://topups.reloadly.com\"\n}");
            Request request = new Request.Builder()
                    .url("https://auth.reloadly.com/oauth/token")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
