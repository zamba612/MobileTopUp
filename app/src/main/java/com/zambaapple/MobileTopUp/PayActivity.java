package com.zambaapple.MobileTopUp;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stripe.android.Stripe;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.Token;


import com.zambaapple.MobileTopUp.Conversions.Numbers;
import com.zambaapple.MobileTopUp.MobileTopUp.BalanceInfo;
import com.zambaapple.MobileTopUp.MobileTopUp.MobileTopUp;
import com.zambaapple.MobileTopUp.PayMethod.PayMethod;
import com.zambaapple.MobileTopUp.Sdk.APIClient;
import com.zambaapple.MobileTopUp.Sdk.createtoken;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PayActivity extends AppCompatActivity {
    public static String currencysymbol = "";
    DatabaseReference MyRefDatabase = FirebaseDatabase.getInstance("https://mobiletopup-2f33c-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    TextView tariftopaydirect, phonenumber_to_send, commissionamount, discountamount, TotalTopay, fraisdeservice;
    Token tok;
    EditText cardNumberField;
    EditText monthField;
    EditText yearField;
    EditText cvcField;
    Button submitButton, Abandonner;
    createtoken create = APIClient.getStripeMethods().create(createtoken.class);
    createtoken cretaed = APIClient.getClient().create(createtoken.class);
    private FirebaseAuth mAuth;
    private OkHttpClient httpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" +
                getIntent().getStringExtra("operator") +
                getIntent().getStringExtra("countryside") +
                getIntent().getStringExtra("linenumber") +
                getIntent().getStringExtra("commission") +
                getIntent().getStringExtra("linenumber") +
                getIntent().getStringExtra("currencysymbol") +
                getIntent().getStringExtra("amounted") +
                getIntent().getStringExtra("currency").toLowerCase() +
                getIntent().getStringExtra("discount"));
        mAuth = FirebaseAuth.getInstance();
        cardNumberField = findViewById(R.id.cardNumber);
        monthField = findViewById(R.id.month);
        yearField = findViewById(R.id.year);
        cvcField = findViewById(R.id.cvc);
        commissionamount = findViewById(R.id.commissionamount);
        discountamount = findViewById(R.id.discountamount);
        TotalTopay = findViewById(R.id.TotalTopay);
        Abandonner = findViewById(R.id.payementcancelbutton);
        tariftopaydirect = findViewById(R.id.tariftopaydirect);
        phonenumber_to_send = findViewById(R.id.phonenumber_to_send);
        fraisdeservice = findViewById(R.id.fraisdeservice);
        currencysymbol = getIntent().getStringExtra("currencysymbol");
        tariftopaydirect.setText(getIntent().getStringExtra("amounted") + " " + currencysymbol);
        double montantachat = Double.parseDouble(getIntent().getStringExtra("amounted"));
        double max = 100.00;
        double commission = Double.parseDouble(getIntent().getStringExtra("commission"));
        double discount = Double.parseDouble(getIntent().getStringExtra("discount"));

        double fraiscommission = (montantachat / max) * commission;
        double fraisdiscount = (montantachat / max) * discount;
        double fraisstripe = (montantachat / 100) * 2.9;
        double fraisdecarte = fraisstripe + 0.25;
        commissionamount.setText(Numbers.digit(fraiscommission )+ currencysymbol);
        discountamount.setText(String.valueOf(fraisdiscount) + currencysymbol);
        TotalTopay.setText(Numbers.digit(montantachat+fraiscommission+fraisdecarte+fraisstripe)+ currencysymbol);
        fraisdeservice.setText(String.valueOf(fraisdecarte) + currencysymbol);
        phonenumber_to_send.setText(getIntent().getStringExtra("linenumber"));
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCard();
            }
        });
        Abandonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoMainMenu();
            }
        });


    }

    private void GotoMainMenu() {
        String buttoncancel = Abandonner.getText().toString().toUpperCase();
        if (buttoncancel.equals("FINISH")) {

            Intent intent = new Intent(PayActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (buttoncancel.equals("ABANDONNER")) {

            Intent intent = new Intent(PayActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    public void submitCard() {

        Call<PayMethod> call = create.getPayMethod(
                cardNumberField.getText().toString(),
                monthField.getText().toString(),
                yearField.getText().toString(),
                cvcField.getText().toString(),
                TotalTopay.getText().toString().replace(currencysymbol, ""),
                getIntent().getStringExtra("currency").toLowerCase());
        call.enqueue(new Callback<PayMethod>() {
            @Override
            public void onResponse(Call<PayMethod> call, retrofit2.Response<PayMethod> response) {
                PayMethod root = response.body();
                if (root.getId() != null) {
                    PayMethodResultactions(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<PayMethod> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    private void PayMethodResultactions(PayMethod body) {
if (body.getStatus() != null){
    AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
    builder.setMessage("Paiement accepté");
    View view = getLayoutInflater().inflate(R.layout.messageinput, null, false);
    builder.setView(view);
    EditText messagetext = view.findViewById(R.id.messageinputtext);
    Random random = new Random();

    messagetext.setText("" + random.nextInt());
    builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ConfirmationActions(getIntent().getStringExtra("operator"),
                    messagetext.getText().toString(),
                    getIntent().getStringExtra("countryside"),
                    getIntent().getStringExtra("linenumber"),
                    getIntent().getStringExtra("amounted"),
                    getIntent().getStringExtra("currency"));

        }
    }).setNegativeButton("ABandonner", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });
    builder.create();
    builder.show();
}

    }

    private void ConfirmationActions(String operatorid, String s, String countryisocode, String phonenumber, String amounted, String currency) {
        Call<MobileTopUp> calls = cretaed.TopUp(
                operatorid,
                s,
                countryisocode,
                phonenumber,
                amounted,
                currency.toLowerCase());
        calls.enqueue(new Callback<MobileTopUp>() {
            @Override
            public void onResponse(Call<MobileTopUp> call, Response<MobileTopUp> response) {
                MobileTopUp mobileTopUp = response.body();


                if (mobileTopUp.getTransactionId() != null) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PayActivity.this);
                    View view = getLayoutInflater().inflate(R.layout.recepte, null, false);
                    builder1.setView(view);
                    TextView transactionid, countrycode, customeridentifientid, delivredyamount, discountrate, transactionsdate, operatorname,
                            operatortransactionid, senderphone, requestamount, recepientphone;
                    recepientphone = view.findViewById(R.id.getRecipientPhone);
                    transactionid = view.findViewById(R.id.TransactionId);
                    countrycode = view.findViewById(R.id.getCountryCode);
                    customeridentifientid = view.findViewById(R.id.getCustomIdentifier);
                    delivredyamount = view.findViewById(R.id.getDeliveredAmount);
                    discountrate = view.findViewById(R.id.getDiscount);
                    transactionsdate = view.findViewById(R.id.TransactionId);
                    operatorname = view.findViewById(R.id.getOperatorName);
                    operatortransactionid = view.findViewById(R.id.getOperatorTransactionId);
                    senderphone = view.findViewById(R.id.getSenderPhone);
                    requestamount = view.findViewById(R.id.getRequestedAmount);


                    transactionid.setText("" + mobileTopUp.getTransactionId());
                    countrycode.setText(mobileTopUp.getCountryCode());
                    System.out.println(" yyyyyyyyyyyyyyyyyyy>>>>>>>>>" + mobileTopUp.getCountryCode());
                    customeridentifientid.setText(mobileTopUp.getCustomIdentifier());
                    delivredyamount.setText(mobileTopUp.getDeliveredAmount() + " " + mobileTopUp.getDeliveredAmountCurrencyCode());
                    discountrate.setText(mobileTopUp.getDiscount() + " " + mobileTopUp.getDiscountCurrencyCode());
                    transactionsdate.setText(mobileTopUp.getTransactionDate());
                    operatorname.setText(mobileTopUp.getOperatorName());
                    operatortransactionid.setText(mobileTopUp.getOperatorTransactionId());
                    senderphone.setText(String.valueOf(mobileTopUp.getSenderPhone()));
                    recepientphone.setText(mobileTopUp.getRecipientPhone());
                    requestamount.setText(mobileTopUp.getRequestedAmount() + " " + mobileTopUp.getRequestedAmountCurrencyCode());
                    sendtoFirebaseDatabase(mobileTopUp.getTransactionId(),mobileTopUp.getRecipientPhone());

                    builder1.setTitle(mobileTopUp.getTransactionDate());
                    builder1.setPositiveButton("Envoyer de nouveau", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            submitCard();
                            Abandonner.setText("Finish");
                        }
                    }).setNegativeButton("Quitter, essayer un autre transfert", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GotoMainMenu();
                        }
                    });
                    builder1.create();
                    builder1.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                    View view = getLayoutInflater().inflate(R.layout.errormessage, null, false);
                    TextView messageerrorui = view.findViewById(R.id.messageerror);
                    builder.setView(view);
                    messageerrorui.setText(mobileTopUp.getTimeStamp() + "\n" + mobileTopUp.getMessage() + "\n" + mobileTopUp.getDetails());
                    builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create();
                    builder.show();

                }


            }

            @Override
            public void onFailure(Call<MobileTopUp> call, Throwable t) {
                System.out.println("Error envois credit : " + t.getMessage());

                // ResponseError responseError=t.getMessage();
                // System.out.println(responseError);
            }
        });
    }

    private void sendtoFirebaseDatabase(Integer transactionId, String recipientPhone) {
        Map map=new HashMap();
        map.put("transactionid",transactionId);
        map.put("recipientPhoneNumber",recipientPhone);
        MyRefDatabase.child("Transanctions").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.e("save transactions info","success");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e("save transactions info",e.getMessage());
            }
        });


    }


    private void deconnexion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
        mAuth.signOut();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

        } else {
            Intent intent = new Intent(PayActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        builder.create();
        builder.show();
    }

    private void foireauxquestions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
        builder.setCancelable(true);
        builder.setPositiveButton("continuer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(PayActivity.this, FoireauxquestionsActivity.class);
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
        startActivity(new Intent(PayActivity.this, ContactezNousActivity.class));

    }

}
