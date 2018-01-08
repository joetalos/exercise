package cart.application;

import cart.product.Apple;
import cart.product.Orange;
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
        return getSumApplePrice().add(getSumOrangePrice());
    }

    public BigDecimal checkout(List<Product> products) {
        this.products = products;
        return checkout();
    }

    public int getAppleCount() {
        int result = (int)products.stream().filter(p -> p instanceof Apple).count();
        return result;
    }

    public BigDecimal getSumApplePrice() {
        int appleCount = getAppleCount();
        int div = appleCount / 2;
        int mod = appleCount % 2;
        return new BigDecimal(div + mod).multiply(Apple.PRICE).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public int getOrangeCount() {
        int result = (int)products.stream().filter(p -> p instanceof Orange).count();
        return result;
    }

    public BigDecimal getSumOrangePrice() {
        int orangeCount = getOrangeCount();
        int div = orangeCount / 3;
        int mod = orangeCount % 3;
        return new BigDecimal(div * 2 + mod).multiply(Orange.PRICE).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
