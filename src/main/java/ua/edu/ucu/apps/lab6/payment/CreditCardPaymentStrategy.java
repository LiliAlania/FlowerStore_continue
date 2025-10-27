package ua.edu.ucu.apps.lab6.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreditCardPaymentStrategy implements Payment {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expirationDate;

    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using Credit Card ending in " 
                + cardNumber.substring(cardNumber.length() - 4));
    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
}