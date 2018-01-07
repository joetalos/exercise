package cart.application;

import cart.product.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products;

    public ShoppingCart() { products = new ArrayList<Product>(); }

    public boolean isEmpty() { return products.isEmpty(); }

    public ShoppingCart add(Product p) {
        products.add(p);
        return this;
    }

    public boolean contains(Product p) { return products.contains(p); }

    public void remove(Product p) { products.remove(p); }

    public BigDecimal checkout() {
        BigDecimal result = products.stream()
            .map(p -> p.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return result.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal checkout(List<Product> products) {
        this.products = products;
        return checkout();
    }
}
