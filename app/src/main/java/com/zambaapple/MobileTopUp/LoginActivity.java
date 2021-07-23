package com.zambaapple.MobileTopUp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.zambaapple.MobileTopUp.Sdk.APIClient;
import com.zambaapple.MobileTopUp.Sdk.createtoken;
import com.zambaapple.MobileTopUp.countries.Country;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG ="Phone login" ;
    private FirebaseAuth mAuth;
    private String ApikeySecret="6LdQV6sbAAAAAApRMcdwyB41v-iSIFWOOuoqFRIh";
    private String SiteKey="6LdQV6sbAAAAAJcNLzkmKAmiHv4adz_WjioQJhYf";
    JSONArray jsonArray;
    Spinner spinner;

    createtoken createtoken = APIClient.getClient().create(createtoken.class);
    TextView countriephonecodelogin;
    EditText phone_numberlogin;
    Button requestauthcode,idBtnVerify;
    private String verificationId;
    EditText idEdtOtp;
    ProgressDialog progressDialog;
    DatabaseReference MyRefDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner=findViewById(R.id.countriecodelogin);
        countriephonecodelogin=findViewById(R.id.countriephonecodelogin);
        phone_numberlogin=findViewById(R.id.phone_numberlogin);
        requestauthcode=findViewById(R.id.requestauthcode);
        mAuth = FirebaseAuth.getInstance();
        idEdtOtp=findViewById(R.id.idEdtOtp);
        idBtnVerify=findViewById(R.id.idBtnVerify);
        MyRefDatabase= FirebaseDatabase.getInstance("https://mobiletopup-2f33c-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
        progressDialog=new ProgressDialog(LoginActivity.this);

        requestauthcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phonecode=countriephonecodelogin.getText().toString();
                String mobile = phone_numberlogin.getText().toString().trim();
                progressDialog.setTitle("Login progress...");
                progressDialog.setMessage("en attente de l'autorization, merci de patienter.");
                progressDialog.create();
                progressDialog.show();
                if(mobile.isEmpty() || mobile.length() > 9){
                    phone_numberlogin.setError("Enter a valid mobile");
                    phone_numberlogin.requestFocus();
                    progressDialog.dismiss();
                    return;
                }
                if (TextUtils.isEmpty(phone_numberlogin.getText().toString())) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    String phone = phonecode + mobile;
                    sendVerificationCode(progressDialog,phone);
                }
            }
        });
        idBtnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(idEdtOtp.getText().toString())) {

                    Toast.makeText(LoginActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {

                    verifyCode(idEdtOtp.getText().toString());
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (phonecode(position) != null) {
                    System.out.println(phonecode(position));
                    countriephonecodelogin.setText(phonecode(position));
                    System.out.println(countriescode(position));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        countries();
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        Log.d(TAG, "handleFacebookAccessToken:" + credential);
        progressDialog.setTitle("Login progress...");
        progressDialog.setMessage("en attente de l'autorization, merci de patienter.");
        progressDialog.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            updateUI(currentUser);
                            progressDialog.dismiss();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser currentUser) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token=instanceIdResult.getToken();
                SaveTokenCurrentUser(currentUser,token);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d(TAG, "handleFacebookAccessToken:" + e.getMessage());
            }
        });
    }

    private void SaveTokenCurrentUser(FirebaseUser currentUser, String token) {
        progressDialog.setTitle("Registre ...");
        progressDialog.setMessage("en attente de l'autorization, merci de patienter.");
        progressDialog.show();
        System.out.println("Token result :   "+token+"\n"+currentUser.getUid());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("current",currentUser.getUid());
        map.put("phonenumber",currentUser.getPhoneNumber());
        map.put("displayName",currentUser.getDisplayName());
        map.put("photoUrl",currentUser.getPhotoUrl());
        map.put("email",currentUser.getEmail());
        MyRefDatabase.child("MobilesClients").child(currentUser.getUid()).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "SaveSuccess", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendVerificationCode(ProgressDialog progressDialog, String number) {
        progressDialog.dismiss();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        idEdtOtp.setVisibility(View.VISIBLE);
        idBtnVerify.setVisibility(View.VISIBLE);


    }

    private void enableUserManuallyInputCode() {
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            progressDialog.setTitle("Login progress...");
            progressDialog.setMessage("en attente de l'autorization, merci de patienter.");
            progressDialog.show();
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                idEdtOtp.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
                progressDialog.dismiss();
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
    public void countries() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://webhostzamba.000webhostapp.com/wallets/ReloadlyAPI/Countries.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                getListOfCountries(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(jsonObjectRequest);
    }
    public void getListOfCountries(JSONObject response){
        try {
             jsonArray = response.getJSONArray("data");
            List<String> strings=new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String countrie = jsonObject.getString("nom_en_gb");
                strings.add(countrie);
                ArrayAdapter arrayAdapter = new ArrayAdapter(LoginActivity.this, R.layout.support_simple_spinner_dropdown_item, strings);
                spinner.setAdapter(arrayAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String phonecode(int poste) {
        String coutrieIS = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(poste);
            coutrieIS = jsonObject.getString("codephonecalls");
            System.out.println(coutrieIS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return coutrieIS;
    }
    public String countriescode(int poste) {
        String coutrieIS = "";
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(poste);
            coutrieIS = jsonObject.getString("alpha2");
            System.out.println(coutrieIS);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return coutrieIS;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser !=null){
            updateUI(currentUser);
        }else{

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.loginmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.foireauxquestionsl:
                foireauxquestions();
                return true;
            case R.id.contacteznousl:
                contacteznous();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void foireauxquestions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setPositiveButton("continuer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(LoginActivity.this, FoireauxquestionsActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("abandonner", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.show();
    }

    private void contacteznous() {
        startActivity(new Intent(LoginActivity.this, ContactezNousActivity.class));

    }
}

