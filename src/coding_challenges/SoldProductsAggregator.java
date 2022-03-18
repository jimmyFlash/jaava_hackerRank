package coding_challenges;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SoldProductsAggregator {

    private final EURExchangeService exchangeService; // interface

    public static final String CURR = "EUR"; // default currency label


    public  static void main(String[] args) {

//        sample stream of SoldProduct type
        Stream<SoldProduct> soldProds =  Stream.of(
                new SoldProduct("books", new BigDecimal("12.0987654321")),
                new SoldProduct("cds",   new BigDecimal("124.0987654321")),
                new SoldProduct("laptop", new BigDecimal("12456.0987654321")),
                new SoldProduct(null, new BigDecimal("124567890.0987654321")),
                new SoldProduct("null", null),
                null
        );

        // instantiate the SoldProductsAggregator and implement the EURExchangeService interface inline
        // as lambda function
        SoldProductsAggregator soldProductsAggregator = new SoldProductsAggregator(
                (sumPrices, currency) -> {
                    // format based on currency string
                    DecimalFormat decimalFormat = formatWithCurrency(currency);
                    // optional with default empty value
                    Optional<BigDecimal> finalVal = Optional.empty();
                    try {
                        // try to format the sum of product prices then parse that tp produce a number
                        // or in case null return optional empty
                        finalVal = Optional.ofNullable((BigDecimal) decimalFormat.parse(decimalFormat.format(sumPrices)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return finalVal;
                }
        );

        SoldProductsAggregate soldProd = soldProductsAggregator.aggregate(soldProds);
        System.out.println("Valid products list: " + soldProd.getSimpleProductsList());
        System.out.println("Total sum: " + (formatWithCurrency(CURR).format(soldProd.getSumProducts())));
        System.out.println("Total sum Euro: " + DecimalFormat.getCurrencyInstance(Locale.GERMANY)
                .format(soldProd.getSumProducts()));
    }

    /**
     * utility method formats a BigDecimal value according to give currency iso string
     * @param currency the currency iso string
     * @return an instance of DecimalFormat with defined currency base
     */
    private static DecimalFormat formatWithCurrency(String currency){
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setParseBigDecimal(true);
        Currency currencySymbol = Currency.getInstance(currency);
        decimalFormat.setCurrency(currencySymbol);
        return decimalFormat;
    }


    // constructor
    private SoldProductsAggregator(EURExchangeService EURExchangeService) {
        this.exchangeService = EURExchangeService;
    }

    /**
     * processes the stream of sold products into a list of SimpleSoldProduct
     * then calculates the sum of the SimpleSoldProduct price
     * @param products the stream of SoldProduct
     * @return an instance of SoldProductsAggregate with list of SimpleSoldProduct and their sum value
     */
    private SoldProductsAggregate aggregate(Stream<SoldProduct> products) {

        List<SimpleSoldProduct> simpleProducts = products
                .filter(Objects::nonNull)
                .filter(product -> product.getPrice() != null && product.getName() != null)
                .map(sp -> new SimpleSoldProduct (sp.getName(), applyRateconversion(sp.getPrice())) )
                .collect(Collectors.toList());

        BigDecimal sumProducts =  simpleProducts.stream()
                .map(SimpleSoldProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SoldProductsAggregate(simpleProducts, sumProducts);
    }

    /**
     * helper method calls the implementation of the EURExchangeService interface
     * @param prodPrice the price property of SoldProduct instance
     * @return a bigDecimal representing formatted number based on currency or the original price if error
     */
    private BigDecimal applyRateconversion(BigDecimal prodPrice){
        Optional<BigDecimal> euroRate =  this.exchangeService.rate(prodPrice,CURR);
        return euroRate.orElse(prodPrice);
    }
}

class SoldProductsAggregate{


    private final List<SimpleSoldProduct> simpleProductsList;
    private final BigDecimal sumProducts;

    public SoldProductsAggregate(List<SimpleSoldProduct> simpleProductsList, BigDecimal sumProducts) {
        this.simpleProductsList = simpleProductsList;
        this.sumProducts = sumProducts;
    }

    public List<SimpleSoldProduct> getSimpleProductsList() {
        return simpleProductsList;
    }

    public BigDecimal getSumProducts() {
        return sumProducts;
    }
}

class SimpleSoldProduct{

    private final String name;
    private final BigDecimal price;

    SimpleSoldProduct(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "coding_challenges.SimpleSoldProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

/**
 * nested class represents a product
 * has product name and product price in BigDecimal properties
 * getters for both properties
 *
 */
class SoldProduct{

    private String name;

    private BigDecimal price;

    SoldProduct(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    BigDecimal getPrice() {
        return price;
    }

}

// interface implemented by class with single abstract interface
interface EURExchangeService{
    Optional<BigDecimal> rate(BigDecimal sumPrices, String currency);
}