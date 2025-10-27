package ua.edu.ucu.apps.lab6.delivery;

public class DHLDeliveryStrategy implements Delivery {
    private static final double BASE_PRICE = 50;
    private static final double PREMIUM_THRESHOLD = 500;
    private static final double PREMIUM_PRICE = 30;

    @Override
    public double delivery(double orderPrice) {
        if (orderPrice > PREMIUM_THRESHOLD) {
            return PREMIUM_PRICE;
        }
        return BASE_PRICE;
    }
}