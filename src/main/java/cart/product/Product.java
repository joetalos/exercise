package cart.product;

import java.math.BigDecimal;

// I just realized, that the specs says: "only selling apples and oranges"...
// I guess I can leave it like this as this model is extensible
public interface Product {

    BigDecimal getPrice();
}
