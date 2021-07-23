package com.zambaapple.MobileTopUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.*;

import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareMessengerURLActionButton;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.GooglePayConfig;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.zambaapple.MobileTopUp.FoireAuxQuestions.FoireAuxQuestions;
import com.zambaapple.MobileTopUp.FoireAuxQuestions.User;
import com.zambaapple.MobileTopUp.Sdk.RelodlyTokenCls;
import com.zambaapple.MobileTopUp.Sdk.createtoken;
import com.zambaapple.MobileTopUp.OperatorByPhoneIso.OperatorValue;
import com.zambaapple.MobileTopUp.Sdk.APIClient;
import com.zambaapple.MobileTopUp.countries.Country;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Logout";
    DatabaseReference MyRefDatabase = FirebaseDatabase.getInstance("https://mobiletopup-2f33c-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    createtoken createtoken = APIClient.getClient().create(createtoken.class);
    private JSONArray jsonArray;
    Spinner spinner;
    TextView countriephonecodeiso, countriephonecode, operatorname;
    ImageView imagecountrieimage, logoimage;
    Button buttonrequest;
    EditText phone_number;
    CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    ProfileTracker profileTracker;
    Toolbar toolbar;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.countriecode);
        countriephonecodeiso = findViewById(R.id.countriephonecodeiso);
        imagecountrieimage = findViewById(R.id.imagecountrieimage);
        countriephonecode = findViewById(R.id.countriephonecode);
        buttonrequest = findViewById(R.id.buttonrequest);
        phone_number = findViewById(R.id.phone_number);
        logoimage = findViewById(R.id.countrieimage);
        operatorname = findViewById(R.id.operatorname);
        toolbar = findViewById(R.id.toolbarcontext);

        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        buttonrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestphonedatas();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (phonecode(position) != null) {
                    System.out.println(phonecode(position));
                    countriephonecode.setText(phonecode(position));
                    System.out.println(countriescode(position));
                    ReloadlyCountriecodes(countriescode(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                System.out.println("gh thjkj <<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>");
            }
        };
        countries();
    }
    @Override
    protected void onActivityResult(int restCode, int resulCode, Intent dataf) {
        super.onActivityResult(restCode, resulCode, dataf);
        mCallbackManager.onActivityResult(restCode, resulCode, dataf);
    }
    private void requestphonedatas() {
        String phonecallscode = countriephonecode.getText().toString();
        String phonecallnumber = phone_number.getText().toString();
        String countrieiso = countriephonecodeiso.getText().toString();
        String countrie = spinner.getSelectedItem().toString();
        String phoneNumber = phonecallscode.replace("+", "") + phonecallnumber;
        Call<OperatorValue> call = createtoken.getCountrieOperator(phoneNumber, countrie, countrieiso);
        call.enqueue(new Callback<OperatorValue>() {
            @Override
            public void onResponse(Call<OperatorValue> call, retrofit2.Response<OperatorValue> response) {
                OperatorValue root = response.body();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.reseautelephonie, null, false);
                builder.setView(view);
                ImageView imageView = view.findViewById(R.id.imageviewlogo);
                TextView textView = view.findViewById(R.id.textviewoperator);
                String continuer = "";
                if (root.getName() != null || root.getLogoUrls() != null) {
                    operatorname.setText(root.getName());
                    System.out.println(root.getLogoUrls().get(1));
                    Picasso.get().load(root.getLogoUrls().get(2)).into(logoimage);
                    textView.setText(root.getName());
                    Picasso.get().load(root.getLogoUrls().get(2)).into(imageView);
                    continuer = "continuer";
                    builder.setPositiveButton(continuer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, TarifActivity.class);
                            intent.putExtra("PhoneCde", phonecallscode);
                            intent.putExtra("Phonetically", phonecallnumber);
                            intent.putExtra("PhoneCde", phonecallscode);
                            intent.putExtra("operator", root.getOperatorId());
                            intent.putExtra("countries", countrieiso);
                            intent.putExtra("countriesName", countrie);
                            intent.putExtra("commission", String.valueOf(root.getCommission()));
                            intent.putExtra("discount", String.valueOf(root.getLocalDiscount()));
                            intent.putExtra("currencysymbol", root.getSenderCurrencySymbol());
                            startActivity(intent);
                        }
                    }).setNegativeButton("Abandonner", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    builder.setNegativeButton("Abandonner", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create();
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<OperatorValue> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void ReloadlyCountriecodes(String countriescode) {
        Call<Country> call = createtoken.getRelodlyCountries(countriescode);
        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, retrofit2.Response<Country> response) {
                Country root = response.body();
                System.out.println(root.getFlag());
                if (root.getFlag() != null) {
                    Utils.fetchSvg(MainActivity.this, root.getFlag(), imagecountrieimage);
                }
                countriephonecodeiso.setText(root.getIsoName());
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
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
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    public void getListOfCountries(JSONObject response) {
        try {
            jsonArray = response.getJSONArray("data");
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String countrie = jsonObject.getString("nom_en_gb");
                strings.add(countrie);
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, strings);
                spinner.setAdapter(arrayAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String phonecode(int poste) {
        String coutrieIS = "";
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(poste);
            coutrieIS = jsonObject.getString("codephonecalls");
            System.out.println(coutrieIS);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
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
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.foireauxquestions:
                foireauxquestions();
                return true;
            case R.id.contacteznous:
                contacteznous();
                return true;
            case R.id.touslesoprerateurs:
                alloperators();
                return true;
            case R.id.deconnexionitem:
                deconnexion();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void alloperators() {
        Intent intent=new Intent(MainActivity.this,OperatorsActivity.class);
        startActivity(intent);
    }

    private void deconnexion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        mAuth.signOut();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        builder.create();
        builder.show();
    }

    private void foireauxquestions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setPositiveButton("continuer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, FoireauxquestionsActivity.class);
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
        startActivity(new Intent(MainActivity.this, ContactezNousActivity.class));

    }
}
