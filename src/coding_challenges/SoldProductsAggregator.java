package coding_challenges;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SoldProductsAggregator {

    private final EURExchangeService exchangeService;

    public static final String CURR = "EUR";


    public  static void main(String[] args) {

        Stream<SoldProduct> soldProds =  Stream.of(
                new SoldProduct("books", new BigDecimal("12.0987654321")),
                new SoldProduct("cds",   new BigDecimal("124.0987654321")),
                new SoldProduct("laptop", new BigDecimal("12456.0987654321")),
                new SoldProduct(null, new BigDecimal("124567890.0987654321")),
                new SoldProduct("null", null),
                null
        );

        SoldProductsAggregator soldProductsAggregator = new SoldProductsAggregator(
                (sumPrices, currency) -> {
                    DecimalFormat decimalFormat = formatWithCurrency(currency);
                    Optional<BigDecimal> finalVal = Optional.empty();
                    try {
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

    private static DecimalFormat formatWithCurrency(String currency){
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setParseBigDecimal(true);
        Currency currencySymbol = Currency.getInstance(currency);
        decimalFormat.setCurrency(currencySymbol);
        return decimalFormat;
    }


    private SoldProductsAggregator(EURExchangeService EURExchangeService) {
        this.exchangeService = EURExchangeService;
    }

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

interface EURExchangeService{
    Optional<BigDecimal> rate(BigDecimal sumPrices, String currency);
}