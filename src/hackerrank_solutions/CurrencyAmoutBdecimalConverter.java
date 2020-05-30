package hackerrank_solutions;

import java.math.BigDecimal;
import java.util.Locale;
import java.text.*;
public class CurrencyAmoutBdecimalConverter{

    public static void main(String []args)throws ParseException{
        final String dollarsA = "$199.00";
        final String real = "R$ 399,00";
        final String dollarsB = "£25.00";
        final String tailingEuro = "90,83 €";
        final String dollarsC = "199.00";
        final String dirham = "AED 449.00";
        final String noCurrencyFrenchformat = "3 220 550,00";
        final String noCurrency = "22550.00";


        System.out.println(BigdecimalConverter.parse(dollarsA, Locale.US));
        System.out.println(BigdecimalConverter.parse(real, Locale.FRANCE));
        System.out.println(BigdecimalConverter.parse(dollarsB, Locale.US));
        System.out.println(BigdecimalConverter.parse(tailingEuro, Locale.FRANCE));
        System.out.println(BigdecimalConverter.parse(dollarsC, Locale.US));
        System.out.println(BigdecimalConverter.parse(dirham, Locale.US));
        System.out.println(BigdecimalConverter.parse(noCurrencyFrenchformat, Locale.FRANCE));
        System.out.println("BigDecimal to double " + BigdecimalConverter.parse(noCurrency, Locale.US).doubleValue());

    }

}

class BigdecimalConverter{
    public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));
    }
}