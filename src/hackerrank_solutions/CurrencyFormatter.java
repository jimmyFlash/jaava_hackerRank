package hackerrank_solutions;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CurrencyFormatter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();

    
        String us = formateWithCurrencySymb(Locale.US, payment, "");
        
        String india = formateWithCurrencySymb(Locale.ENGLISH, payment, "IN");
        
        String china = formateWithCurrencySymb(Locale.CHINA, payment, "");
        
        String france = formateWithCurrencySymb(Locale.FRANCE, payment, "");
        
        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }

     private static String formateWithCurrencySymb(Locale locale, double payment, String country){

         Locale loco = locale;
        if(country.trim().length() > 0){
            //System.out.println(locale.getLanguage());
            loco = new Locale(locale.getLanguage(), country );
        }
        NumberFormat nf = NumberFormat.getCurrencyInstance(loco);
        Currency currency = nf.getCurrency();
        Currency currencySymbole = Currency.getInstance(currency.toString());
        return  nf.format(payment);
    }
}

