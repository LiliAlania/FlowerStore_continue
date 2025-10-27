package ua.edu.ucu.apps.lab6.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PayPalPaymentStrategy implements Payment {
    private String email;
    private String password;

    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using PayPal account: " + email);
        // In real implementation, would integrate with PayPal API
    }

    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
}