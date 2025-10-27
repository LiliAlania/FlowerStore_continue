package ua.edu.ucu.apps.lab6.payment;

public interface Payment {
    void pay(double amount);
    String getPaymentMethod();
}
