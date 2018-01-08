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
    public void testCart_WhenListOfItemsIsPassed_ThenCheckoutEqualsToSumOfItems() {
        Product[] products = new Product[] {new Orange(), new Orange(), new Apple()};
        assertEquals(new BigDecimal(1.1).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.checkout(Arrays.asList(products)));
    }

    @Test
    public void testCart_WhenBuyingMoreApples_ThenApplyBuyOneGetOneFreeSpecial() {
        theCart.add(new Apple())
                .add(new Apple())
                .add(new Apple());

        assertEquals(3, theCart.getAppleCount());
        assertEquals(new BigDecimal(1.2).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.getSumApplePrice());
    }

    @Test
    public void testCart_WhenBuying2Oranges_ThenDontApplyAnySpecial() {
        theCart.add(new Orange())
                .add(new Orange());

        assertEquals(new BigDecimal(0.5).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.getSumOrangePrice());
    }

    @Test
    public void testCart_WhenBuying3Oranges_ThenApply3For2Special() {
        theCart.add(new Orange())
                .add(new Orange())
                .add(new Orange());

        assertEquals(new BigDecimal(0.5).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.getSumOrangePrice());
    }

    @Test
    public void testCart_WhenBuying4Oranges_ThenApply3For2Special() {
        theCart.add(new Orange())
                .add(new Orange())
                .add(new Orange())
                .add(new Orange());

        assertEquals(new BigDecimal(0.75).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.getSumOrangePrice());
    }

    @Test
    public void testCart_WhenBuying5Oranges_ThenApply3For2Special() {
        theCart.add(new Orange())
                .add(new Orange())
                .add(new Orange())
                .add(new Orange())
                .add(new Orange());

        assertEquals(new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.getSumOrangePrice());
    }

    @Test
    public void testCart_WhenBuying6Oranges_ThenApply3For2Special() {
        theCart.add(new Orange())
                .add(new Orange())
                .add(new Orange())
                .add(new Orange())
                .add(new Orange())
                .add(new Orange());

        assertEquals(new BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.getSumOrangePrice());
    }

    @Test
    public void testCart_WhenBuying3ApplesAnd4Oranges_ThenApplySpecials() {
        theCart.add(new Orange())
                .add(new Orange())
                .add(new Apple())
                .add(new Orange())
                .add(new Apple())
                .add(new Apple())
                .add(new Orange());

        assertEquals(new BigDecimal(1.95).setScale(2, BigDecimal.ROUND_HALF_UP), theCart.checkout());
    }
}
