package ua.edu.ucu.apps.lab6;

import org.junit.jupiter.api.Test;

import ua.edu.ucu.apps.lab6.delivery.DHLDeliveryStrategy;
import ua.edu.ucu.apps.lab6.delivery.Delivery;
import ua.edu.ucu.apps.lab6.delivery.PostDeliveryStrategy;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void testPostDeliveryStrategyFreeDelivery() {
        Delivery delivery = new PostDeliveryStrategy();
        double orderPrice = 1500;
        assertEquals(0, delivery.delivery(orderPrice));
    }

    @Test
    void testPostDeliveryStrategyWithFee() {
        Delivery delivery = new PostDeliveryStrategy();
        double orderPrice = 500;
        assertEquals(100, delivery.delivery(orderPrice));
    }

    @Test
    void testPostDeliveryStrategyAtThreshold() {
        Delivery delivery = new PostDeliveryStrategy();
        double orderPrice = 1000;
        assertEquals(100, delivery.delivery(orderPrice));
    }

    @Test
    void testDHLDeliveryStrategyBasePrice() {
        Delivery delivery = new DHLDeliveryStrategy();
        double orderPrice = 300;
        assertEquals(50, delivery.delivery(orderPrice));
    }

    @Test
    void testDHLDeliveryStrategyPremiumPrice() {
        Delivery delivery = new DHLDeliveryStrategy();
        double orderPrice = 600;
        assertEquals(30, delivery.delivery(orderPrice));
    }

    @Test
    void testDHLDeliveryStrategyAtThreshold() {
        Delivery delivery = new DHLDeliveryStrategy();
        double orderPrice = 500;
        assertEquals(50, delivery.delivery(orderPrice));
    }

    @Test
    void testDHLDeliveryStrategyAboveThreshold() {
        Delivery delivery = new DHLDeliveryStrategy();
        double orderPrice = 501;
        assertEquals(30, delivery.delivery(orderPrice));
    }
}