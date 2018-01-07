package cart.product;

import java.math.BigDecimal;

public class Orange implements Product {

    private static final BigDecimal PRICE = new BigDecimal(0.25);

    public BigDecimal getPrice() { return PRICE; }
}
