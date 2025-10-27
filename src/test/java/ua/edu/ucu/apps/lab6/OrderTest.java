package ua.edu.ucu.apps.lab6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.lab6.delivery.DHLDeliveryStrategy;
import ua.edu.ucu.apps.lab6.delivery.PostDeliveryStrategy;
import ua.edu.ucu.apps.lab6.payment.CreditCardPaymentStrategy;
import ua.edu.ucu.apps.lab6.payment.PayPalPaymentStrategy;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private FlowerBucket flowerBucket;
    private Flower flower;

    @BeforeEach
    void setUp() {
        order = new Order();
        flower = new Flower(FlowerColor.RED, 100, 10, FLowerType.ROSE);
        FlowerPack pack = new FlowerPack(flower, 5);
        flowerBucket = new FlowerBucket();
        flowerBucket.add(pack);
    }

    @Test
    void testOrderInitialization() {
        assertNotNull(order.getItems());
        assertEquals(0, order.getItems().size());
        assertNull(order.getDelivery());
        assertNull(order.getPayment());
    }

    @Test
    void testAddItem() {
        order.addItem(flowerBucket);
        assertEquals(1, order.getItems().size());
        assertTrue(order.getItems().contains(flowerBucket));
    }

    @Test
    void testAddMultipleItems() {
        order.addItem(flowerBucket);
        
        FlowerBucket anotherBucket = new FlowerBucket();
        Flower tulip = new Flower(FlowerColor.YELLOW, 50, 8, FLowerType.TULIP);
        anotherBucket.add(new FlowerPack(tulip, 3));
        order.addItem(anotherBucket);
        
        assertEquals(2, order.getItems().size());
    }

    @Test
    void testRemoveItem() {
        order.addItem(flowerBucket);
        assertEquals(1, order.getItems().size());
        
        order.removeItem(flowerBucket);
        assertEquals(0, order.getItems().size());
    }

    @Test
    void testCalculateTotalPrice() {
        order.addItem(flowerBucket);
        // FlowerBucket has 5 flowers at $100 each = $500
        assertEquals(500, order.calculateTotalPrice());
    }

    @Test
    void testCalculateTotalPriceMultipleItems() {
        order.addItem(flowerBucket);
        
        FlowerBucket anotherBucket = new FlowerBucket();
        Flower tulip = new Flower(FlowerColor.YELLOW, 50, 8, FLowerType.TULIP);
        anotherBucket.add(new FlowerPack(tulip, 4));
        order.addItem(anotherBucket);
        
        // First bucket: 5 * 100 = 500
        // Second bucket: 4 * 50 = 200
        // Total: 700
        assertEquals(700, order.calculateTotalPrice());
    }

    @Test
    void testCalculateTotalWithDeliveryPost() {
        order.addItem(flowerBucket);
        order.setDelivery(new PostDeliveryStrategy());
        
        // Order total: 500, delivery: 100
        assertEquals(600, order.calculateTotalWithDelivery());
    }

    @Test
    void testCalculateTotalWithDeliveryPostFree() {
        // Add enough items to get free delivery (over 1000)
        order.addItem(flowerBucket);
        
        FlowerBucket largeBucket = new FlowerBucket();
        Flower expensiveFlower = new Flower(FlowerColor.RED, 200, 12, FLowerType.ROSE);
        largeBucket.add(new FlowerPack(expensiveFlower, 4));
        order.addItem(largeBucket);
        
        order.setDelivery(new PostDeliveryStrategy());
        
        // Order total: 500 + 800 = 1300, delivery: 0
        assertEquals(1300, order.calculateTotalWithDelivery());
    }

    @Test
    void testCalculateTotalWithoutDelivery() {
        order.addItem(flowerBucket);
        
        // No delivery set, should return items total only
        assertEquals(500, order.calculateTotalWithDelivery());
    }

    @Test
    void testProcessOrderSuccess() {
        order.addItem(flowerBucket);
        order.setDelivery(new PostDeliveryStrategy());
        order.setPayment(new CreditCardPaymentStrategy(
            "1234567890123456", "John Doe", "123", "12/25"
        ));
        
        assertDoesNotThrow(() -> order.processOrder());
    }

    @Test
    void testProcessOrderWithoutPayment() {
        order.addItem(flowerBucket);
        order.setDelivery(new PostDeliveryStrategy());
        
        Exception exception = assertThrows(IllegalStateException.class, 
            () -> order.processOrder());
        assertEquals("Payment method not set", exception.getMessage());
    }

    @Test
    void testProcessOrderWithoutDelivery() {
        order.addItem(flowerBucket);
        order.setPayment(new PayPalPaymentStrategy("test@test.com", "pass"));
        
        Exception exception = assertThrows(IllegalStateException.class, 
            () -> order.processOrder());
        assertEquals("Delivery method not set", exception.getMessage());
    }

    @Test
    void testSetDelivery() {
        PostDeliveryStrategy delivery = new PostDeliveryStrategy();
        order.setDelivery(delivery);
        
        assertEquals(delivery, order.getDelivery());
    }

    @Test
    void testSetPayment() {
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy(
            "1234567890123456", "John Doe", "123", "12/25"
        );
        order.setPayment(payment);
        
        assertEquals(payment, order.getPayment());
    }

    @Test
    void testEmptyOrderTotalPrice() {
        assertEquals(0, order.calculateTotalPrice());
        assertEquals(0, order.calculateTotalWithDelivery());
    }
}