package com.zambaapple.MobileTopUp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class SingleActivity extends AppCompatActivity {
    private JSONArray jsonArray;
    ListView singleoperator;
    ImageView logiimageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

       // ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
       // ImageLoader.getInstance().init(config);
        logiimageview=findViewById(R.id.logiimageview);
        singleoperator=findViewById(R.id.singleoperator);
        String operatorid=getIntent().getStringExtra("operatorid");
        getJsonsObjets(operatorid);
    }
    private void getJsonsObjets(String operatorid) {


StringRequest stringRequest=new StringRequest(Request.Method.POST,"https://webhostzamba.000webhostapp.com/wallets/ReloadlyAPI/operatorbyid.php", new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
System.out.println(response);
        List<String> strings = new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(response);
          String  id = jsonObject.getString("operatorId");
            String  name = jsonObject.getString("name");
            String  bundle = jsonObject.getString("bundle");
            String  data = jsonObject.getString("data");
            String  pin = jsonObject.getString("pin");
            String  supportsLocalAmounts = jsonObject.getString("supportsLocalAmounts");
            strings.add(id);
            strings.add(name);
            strings.add(data);
            strings.add(bundle);
            strings.add(pin);
            strings.add(supportsLocalAmounts);
            String  supportsGeographicalRechargePlans = jsonObject.getString("supportsGeographicalRechargePlans");
            String  denominationType = jsonObject.getString("denominationType");
            String  senderCurrencyCode = jsonObject.getString("senderCurrencyCode");
            String   senderCurrencySymbol = jsonObject.getString("senderCurrencySymbol");
            String  destinationCurrencyCode = jsonObject.getString("destinationCurrencyCode");
            String  destinationCurrencySymbol = jsonObject.getString("destinationCurrencySymbol");
            strings.add(supportsGeographicalRechargePlans);
            strings.add(denominationType);
            strings.add(senderCurrencyCode);
            strings.add(senderCurrencySymbol);
            strings.add(destinationCurrencyCode);
            strings.add(destinationCurrencySymbol);
            String  commission = jsonObject.getString("commission");
            String  internationalDiscount = jsonObject.getString("internationalDiscount");
            String  localDiscount = jsonObject.getString("localDiscount");
            String  mostPopularAmount = jsonObject.getString("mostPopularAmount");
            String  mostPopularLocalAmount = jsonObject.getString("mostPopularLocalAmount");
            String  minAmount = jsonObject.getString("minAmount");
            strings.add(commission);
            strings.add(internationalDiscount);
            strings.add(localDiscount);
            strings.add(mostPopularAmount);
            strings.add(mostPopularLocalAmount);
            strings.add(minAmount);
            String  maxAmount = jsonObject.getString("maxAmount");
            String  localMinAmount = jsonObject.getString("localMinAmount");
            String  localMaxAmount = jsonObject.getString("localMaxAmount");
            strings.add(maxAmount);
            strings.add(localMinAmount);
            strings.add(localMaxAmount);
            String  country = jsonObject.getString("country");
            JSONObject jsonObject1=new JSONObject(country);
            String isoName=jsonObject1.getString("isoName");
            String countryname=jsonObject1.getString("name");
            strings.add(isoName);
            strings.add(countryname);

            String  fx = jsonObject.getString("fx");
            JSONObject jsonObject2=new JSONObject(fx);
            String rate=jsonObject2.getString("rate");
            String currencyCode=jsonObject2.getString("currencyCode");
            String  logoUrls = jsonObject.getString("logoUrls");
            strings.add(rate);
            strings.add(currencyCode);
            //strings.add(logoUrls);
            JSONArray jsonObject3=new JSONArray(logoUrls);
            Picasso.get().load(jsonObject3.get(2).toString()).into(logiimageview);
            for (int i=0;i< jsonObject3.length();i++){
            // strings.add(jsonObject3.get(i).toString());
              System.out.println("<<<<<<>"+jsonObject3.length());
               }
            String  fixedAmounts = jsonObject.getString("fixedAmounts");
            JSONArray jsonArray=new JSONArray(fixedAmounts);
            for (int i=0;i< jsonArray.length();i++){
                strings.add(""+jsonArray.get(i)+" "+senderCurrencySymbol);
                System.out.println("<<<<<<>"+jsonArray.get(i));
            }

            String  fixedAmountsDescriptions = jsonObject.getString("fixedAmountsDescriptions");
            String  localFixedAmounts = jsonObject.getString("localFixedAmounts");
            JSONArray jsonArray2=new JSONArray(localFixedAmounts);
            for (int i=0;i< jsonArray2.length();i++){
               // strings.add(jsonArray2.get(i).toString());
                strings.add(""+jsonArray2.get(i));
                System.out.println("<<<<<<>"+jsonArray2.get(i));
            }
            String  localFixedAmountsDescriptions = jsonObject.getString("localFixedAmountsDescriptions");

            String suggestedAmounts = jsonObject.getString("suggestedAmounts");

            JSONArray jsonArray1=new JSONArray(suggestedAmounts);
            for (int i=0;i< jsonArray1.length();i++){
                //strings.add(jsonArray1.get(i).toString());
                strings.add(""+jsonArray1.get(i)+" "+senderCurrencySymbol);
                System.out.println("<<<<<<>"+jsonArray1.get(i));
            }

            String  suggestedAmountsMap = jsonObject.getString("localMaxAmount");
            String  geographicalRechargePlans = jsonObject.getString("geographicalRechargePlans");
            String  promotions = jsonObject.getString("localMaxAmount");
            strings.add(fixedAmountsDescriptions);
            strings.add(localFixedAmountsDescriptions);
            strings.add(suggestedAmountsMap);
            strings.add(geographicalRechargePlans);
            strings.add(promotions);
            ArrayAdapter arrayAdapter = new ArrayAdapter(SingleActivity.this, R.layout.support_simple_spinner_dropdown_item, strings);
            singleoperator.setAdapter(arrayAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.getMessage());
    }
}) {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map map = new HashMap();
        map.put("operatorid", operatorid);
        return map;
    }
};

        RequestQueue requestQueue = Volley.newRequestQueue(SingleActivity.this);
        requestQueue.add(stringRequest);
    }

}