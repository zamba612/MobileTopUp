package com.zambaapple.MobileTopUp.Conversions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Numbers {
    public static String digit(Double myDouble){
        NumberFormat numberFormatter = new DecimalFormat("##.00");
        String result = numberFormatter.format(myDouble);
        return result.replace(",",".");

    }
}
