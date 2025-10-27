package ua.edu.ucu.apps.lab6;
import lombok.Getter;
import lombok.Setter;
import ua.edu.ucu.apps.lab6.delivery.Delivery;
import ua.edu.ucu.apps.lab6.payment.Payment;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Order {
    private List<Item> items;
    private Payment payment;
    private Delivery delivery;
    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotalPrice() {
        double itemsTotal = items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
        return itemsTotal;
    }

    public double calculateTotalWithDelivery() {
        double itemsTotal = calculateTotalPrice();
        if (delivery != null) {
            return itemsTotal + delivery.delivery(itemsTotal);
        }
        return itemsTotal;
    }

    public void processOrder() {
        if (payment == null) {
            throw new IllegalStateException("Payment method not set");
        }
        if (delivery == null) {
            throw new IllegalStateException("Delivery method not set");
        }

        double total = calculateTotalWithDelivery();
        payment.pay(total);
    }
}