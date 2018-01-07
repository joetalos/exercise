import cart.application.ShoppingCart;
import cart.product.Apple;
import cart.product.Orange;
import cart.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.*;


public class ShoppingCartTest {

    private static ShoppingCart theCart;

    @Before
    public void setUp() {
        theCart = new ShoppingCart();
    }

    @Test
    public void testCart_WhenCartIsNew_ThenItsEmpty() {
        assert theCart.isEmpty();
    }

    @Test
    public void testCart_WhenItemAdded_ThenCartIsNotEmpty() {
        theCart.add(new Orange());
        assertFalse(theCart.isEmpty());
    }

    @Test
    public void testCart_WhenItemAdded_ThenCartContainsItem() {
        Orange theOrange = new Orange();
        theCart.add(theOrange);
        assertTrue(theCart.contains(theOrange));
    }


    @Test
    public void testCart_WhenItemRemoved_ThenCartDoesNotContainItem() {
        Orange theOrange = new Orange();
        theCart.add(theOrange);

        theCart.remove(theOrange);
        assertFalse(theCart.contains(theOrange));
    }

    @Test
    public void testCart_WhenOneItemAdded_ThenCheckoutEqualsToItemPrice() {
        Orange theOrange = new Orange();
        theCart.add(theOrange);

        assertEquals(new BigDecimal(0.25), theCart.checkout());
    }

    @Test
    public void testCart_WhenManyItemsAdded_ThenCheckoutEqualsToSumOfItems() {
        theCart.add(new Orange())
                .add(new Apple())
                .add(new Apple())
                .add(new Orange());

        // rounding is left to be cleaned up...
        assertEquals(new BigDecimal(1.7).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.checkout());
    }

    @Test
    public void testCart_WhenListOfItemsIsPassed_ThenCheckoutEqualsToSumOfItems() {
        Product[] products = new Product[] {new Orange(), new Orange(), new Apple()};
        assertEquals(new BigDecimal(1.1).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.checkout(Arrays.asList(products)));
    }
}
