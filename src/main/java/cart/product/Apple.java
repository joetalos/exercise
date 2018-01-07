package cart.product;

import java.math.BigDecimal;

public class Apple implements Product {

    private static final BigDecimal PRICE = new BigDecimal(0.60);

    public BigDecimal getPrice() { return PRICE; }
}
