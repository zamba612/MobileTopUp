package com.zambaapple.MobileTopUp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zambaapple.MobileTopUp.Sdk.APIClient;
import com.zambaapple.MobileTopUp.Sdk.createtoken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OperatorsActivity extends AppCompatActivity{
createtoken createtoken= APIClient.getClient().create(createtoken.class);
Button buttonrequest;
    private JSONArray jsonArray;
ListView listoperators;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
        buttonrequest=findViewById(R.id.buttonrequest);
        listoperators=findViewById(R.id.listoperators);
       listoperators.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               System.out.println(operatorID(i));
               String operatorid=operatorID(i);
               System.out.println(adapterView.getItemIdAtPosition(i));
               AlertDialog.Builder builder=new AlertDialog.Builder(OperatorsActivity.this);
               builder.setCancelable(true);
               String operator=operator(i);
               builder.setTitle(operator);
               builder.setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Intent intent=new Intent(OperatorsActivity.this,SingleActivity.class);
                       intent.putExtra("operatorid",operatorid);
                       startActivity(intent);
                   }
               }).create().show();
           }
       });

        buttonrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getJsonsObjets();
            }
        });
getJsonsObjets();
    }

    private void getJsonsObjets() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://webhostzamba.000webhostapp.com/wallets/ReloadlyAPI/alloperators.php", null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              System.out.println(response);
              getNanem(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(OperatorsActivity.this);
        requestQueue.add(jsonObjectRequest);
    }
    public void getNanem(JSONObject response) {
        try {
            jsonArray = response.getJSONArray("content");
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                strings.add(name);
               ArrayAdapter arrayAdapter = new ArrayAdapter(OperatorsActivity.this, R.layout.support_simple_spinner_dropdown_item, strings);
                listoperators.setAdapter(arrayAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String operatorID(int poste) {
        String coutrieIS = "";
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(poste);
            coutrieIS = jsonObject.getString("operatorId");
            System.out.println("Operator response "+coutrieIS);
        } catch (JSONException e) {
            System.out.println("Operator error code "+e.getMessage());
        }
        return coutrieIS;
    }
    public String operator(int poste) {
        String coutrieIS = "";
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(poste);
            coutrieIS = jsonObject.getString("name");
            System.out.println("Operator response "+coutrieIS);
        } catch (JSONException e) {
            System.out.println("Operator error code "+e.getMessage());
        }
        return coutrieIS;
    }
}