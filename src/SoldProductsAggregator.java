import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SoldProductsAggregator implements EURExchangeService{

    private BigDecimal sumSimpleProductsPrices;

    public static void main(String[] args) {

        Stream<SoldProduct> soldProds =  Stream.of(
                new SoldProduct("books", new BigDecimal("124567890.0987654321")),
                new SoldProduct("cds",   new BigDecimal("124567890.0987654321")),
                new SoldProduct("laptop", new BigDecimal("124567890.0987654321"))
        );

        SoldProductsAggregator soldProductsAggregator = new SoldProductsAggregator();
        BigDecimal sumPrices = soldProductsAggregator.aggregate(soldProds).sumSimpleProductsPrices;
        soldProductsAggregator.rate(sumPrices, "EUR");
    }


    private SoldProductsAggregator() {}



    private SoldProductsAggregator aggregate(Stream<SoldProduct> products) {

        List<SimpleSoldProduct> simpleProducts = products
                .map(sp -> new SimpleSoldProduct (sp.getName(), sp.getPrice()) )
                .collect(Collectors.toList());


        sumSimpleProductsPrices = simpleProducts.stream()
                .map(SimpleSoldProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return this;
    }

    @Override
    public Optional<BigDecimal> rate(BigDecimal sumPrices, String currency) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setParseBigDecimal(true);
        Currency currencySymbole = Currency.getInstance(currency);
        decimalFormat.setCurrency(currencySymbole);
        try {
            decimalFormat.parse(decimalFormat.format(sumPrices));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}


class SimpleSoldProduct{

    private String name;
    private BigDecimal price;

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