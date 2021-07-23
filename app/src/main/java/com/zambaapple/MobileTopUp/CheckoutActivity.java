package com.zambaapple.MobileTopUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.stripe.android.ApiResultCallback;
import com.stripe.android.GooglePayConfig;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckoutActivity extends AppCompatActivity {
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 53;
    private static final String PUBLISHABLE_KEY = "pk_test_51IKnFMJQ32cgRl5Wtm4OtOiUtt1KzJZZeut4xfKGxDYKvCjoN0RjVuxVbm8EfN7OWXuilxDx7tZHP0J4ZfGZ6Ry600FEuhD8Ma";
    private PaymentsClient paymentsClient;
    private Stripe stripe;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        PaymentConfiguration.init(this, PUBLISHABLE_KEY);

        paymentsClient = Wallet.getPaymentsClient(
                this,
                new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .build()
        );

        stripe = new Stripe(this, "pk_test_51IKnFMJQ32cgRl5Wtm4OtOiUtt1KzJZZeut4xfKGxDYKvCjoN0RjVuxVbm8EfN7OWXuilxDx7tZHP0J4ZfGZ6Ry600FEuhD8Ma");

        try {
            isReadyToPay();
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
        try {
            payWithGoogle();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void payWithGoogle() throws JSONException {
        AutoResolveHelper.resolveTask(
                paymentsClient.loadPaymentData(createPaymentDataRequest()),
                this,
                LOAD_PAYMENT_DATA_REQUEST_CODE
        );
    }
    private void isReadyToPay() throws JSONException {
        final IsReadyToPayRequest request = createIsReadyToPayRequest();
        paymentsClient.isReadyToPay(request)
                .addOnCompleteListener(
                        new OnCompleteListener<Boolean>() {
                            public void onComplete(Task<Boolean> task) {
                                if (task.isSuccessful()) {
                                    // show Google Pay as payment option
                                } else {
                                    // hide Google Pay as payment option
                                }
                            }
                        }
                );
    }

    @NonNull
    private IsReadyToPayRequest createIsReadyToPayRequest() throws JSONException {
        final JSONArray allowedAuthMethods = new JSONArray();
        allowedAuthMethods.put("PAN_ONLY");
        allowedAuthMethods.put("CRYPTOGRAM_3DS");

        final JSONArray allowedCardNetworks = new JSONArray();
        allowedCardNetworks.put("AMEX");
        allowedCardNetworks.put("DISCOVER");
        allowedCardNetworks.put("MASTERCARD");
        allowedCardNetworks.put("VISA");

        final JSONObject cardParameters = new JSONObject();
        cardParameters.put("allowedAuthMethods", allowedAuthMethods);
        cardParameters.put("allowedCardNetworks", allowedCardNetworks);

        final JSONObject cardPaymentMethod = new JSONObject();
        cardPaymentMethod.put("type", "CARD");
        cardPaymentMethod.put("parameters", cardParameters);

        final JSONArray allowedPaymentMethods = new JSONArray();
        allowedPaymentMethods.put(cardPaymentMethod);

        final JSONObject isReadyToPayRequestJson = new JSONObject();
        isReadyToPayRequestJson.put("apiVersion", 2);
        isReadyToPayRequestJson.put("apiVersionMinor", 0);
        isReadyToPayRequestJson.put("allowedPaymentMethods", allowedPaymentMethods);

        return IsReadyToPayRequest.fromJson(isReadyToPayRequestJson.toString());
    }


    @NonNull
    private PaymentDataRequest createPaymentDataRequest() throws JSONException {
        final JSONObject tokenizationSpec =
                new GooglePayConfig(this).getTokenizationSpecification();
        final JSONObject cardPaymentMethod;

        cardPaymentMethod = new JSONObject()
                .put("type", "CARD")
                .put(
                        "parameters",
                        new JSONObject()
                                .put("allowedAuthMethods", new JSONArray()
                                        .put("PAN_ONLY")
                                        .put("CRYPTOGRAM_3DS"))
                                .put("allowedCardNetworks",
                                        new JSONArray()
                                                .put("AMEX")
                                                .put("DISCOVER")
                                                .put("MASTERCARD")
                                                .put("VISA"))

                                // require billing address
                                .put("billingAddressRequired", true)
                                .put(
                                        "billingAddressParameters",
                                        new JSONObject()
                                                // require full billing address
                                                .put("format", "MIN")

                                                // require phone number
                                                .put("phoneNumberRequired", true)
                                )
                )
                .put("tokenizationSpecification", tokenizationSpec);
        final String paymentDataRequest = new JSONObject().put("apiVersion", 2)
                .put("apiVersionMinor", 0)
                .put("allowedPaymentMethods",
                        new JSONArray().put(cardPaymentMethod))
                .put("transactionInfo", new JSONObject()
                        .put("totalPrice", "10.00")
                        .put("totalPriceStatus", "FINAL")
                        .put("currencyCode", "USD")
                ).put("merchantInfo", new JSONObject().put("merchantName", "Example Merchant")).put("emailRequired", true).toString();
        return PaymentDataRequest.fromJson(paymentDataRequest);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOAD_PAYMENT_DATA_REQUEST_CODE: {
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        if (data != null) {
                            try {
                                onGooglePayResult(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        // Canceled
                        break;
                    }
                    case AutoResolveHelper.RESULT_ERROR: {
                        // Log the status for debugging
                        // Generally there is no need to show an error to
                        // the user as the Google Payment API will do that
                        final Status status =
                                AutoResolveHelper.getStatusFromIntent(data);
                        break;
                    }
                    default: {
                        // Do nothing.
                    }
                }
                break;
            }
            default: {
                // Handle any other startActivityForResult calls you may have made.
            }
        }
    }

    private void onGooglePayResult(@NonNull Intent data) throws JSONException {
        final PaymentData paymentData = PaymentData.getFromIntent(data);
        if (paymentData == null) {
            return;
        }

        final PaymentMethodCreateParams paymentMethodCreateParams =
                PaymentMethodCreateParams.createFromGooglePay(
                        new JSONObject(paymentData.toJson()));

        stripe.createPaymentMethod(
                paymentMethodCreateParams,
                new ApiResultCallback<PaymentMethod>() {
                    @Override
                    public void onSuccess(@NonNull PaymentMethod result) {
                        // See https://stripe.com/docs/payments/accept-a-payment?platform=android#android-create-payment-intent
                        // for how to create a PaymentIntent on your backend and use its client secret
                        // to confirm the payment on the client.
                        ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                                .createWithPaymentMethodId(
                                        result.id,
                                        "{{PAYMENT_INTENT_CLIENT_SECRET}}"
                                );
                        stripe.confirmPayment(CheckoutActivity.this, confirmParams, null);
                    }

                    @Override
                    public void onError(@NonNull Exception e) {
                    }
                }
        );
    }

}
