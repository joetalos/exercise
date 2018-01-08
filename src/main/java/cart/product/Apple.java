package cart.product;

import java.math.BigDecimal;

public class Apple implements Product {

    // can be moed to a property-file, but I guess it's OK for now...
    public static final BigDecimal PRICE = new BigDecimal(0.60);

    public BigDecimal getPrice() { return PRICE; }
}
