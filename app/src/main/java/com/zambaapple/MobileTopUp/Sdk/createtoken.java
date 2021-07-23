package com.zambaapple.MobileTopUp.Sdk;


import com.zambaapple.MobileTopUp.MobileTopUp.MobileTopUp;
import com.zambaapple.MobileTopUp.OperatorByPhoneIso.OperatorValue;
import com.zambaapple.MobileTopUp.PayMethod.PayMethod;
import com.zambaapple.MobileTopUp.countries.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface createtoken {
    @GET("create.php")
    Call<PayMethod>getToken(@Query("amount")String amount, @Query("cardnumber")String cardnumber, @Query("expiredate")String expiredate, @Query("cvc")String cvc);

     @GET("relodlytoken.php")
    Call<Root>getToken();

    @GET("operatorbyphone.php")
    Call<OperatorValue>getCountrieOperator(@Query("phonenumber")String PhoneNumber, @Query("countrie")String countrie, @Query("countrieIso")String CountryIso);

    @GET("Countriesisocodes.php")
    Call<Country>getRelodlyCountries(@Query("countrieiso")String countrieiso);

    @GET("paydirectMethod.php")
    Call<PayMethod>getPayMethod(@Query("cardnumber")String cardnumber,
                                @Query("expiremonth")String expiremonth,
                                @Query("expireyear")String expireyear,
                                @Query("cvcnumber")String cvcnumber,
                                @Query("amount")String amount,
                                @Query("currency")String currency);

    @GET("mobiletopup.php")
    Call<MobileTopUp>TopUp(@Query("operatorcode")String operatorcode,
                           @Query("messageavotrerecepteur")String messageavotrerecepteur,
                           @Query("countrieIso")String countrieIso,
                           @Query("phonenumber")String phonenumber,
                           @Query("amount")String amount,
                           @Query("currency")String currency);


}
