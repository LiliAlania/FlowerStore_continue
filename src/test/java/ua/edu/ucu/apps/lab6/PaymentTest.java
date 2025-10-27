package ua.edu.ucu.apps.lab6;

import org.junit.jupiter.api.Test;
import ua.edu.ucu.apps.lab6.payment.PayPalPaymentStrategy;
import ua.edu.ucu.apps.lab6.payment.CreditCardPaymentStrategy;
import ua.edu.ucu.apps.lab6.payment.Payment;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testCreditCardPayment() {
        Payment payment = new CreditCardPaymentStrategy(
                "1234567890123456", 
                "John Doe", 
                "123", 
                "12/25"
        );
        
        assertEquals("Credit Card", payment.getPaymentMethod());
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        payment.pay(100.50);
        
        assertTrue(outContent.toString().contains("Paying $100.5"));
        assertTrue(outContent.toString().contains("3456"));
        
        System.setOut(System.out);
    }

    @Test
    void testPayPalPayment() {
        Payment payment = new PayPalPaymentStrategy(
                "test@example.com", 
                "password123"
        );
        
        assertEquals("PayPal", payment.getPaymentMethod());
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        payment.pay(250.75);
        
        assertTrue(outContent.toString().contains("Paying $250.75"));
        assertTrue(outContent.toString().contains("test@example.com"));
        
        System.setOut(System.out);
    }

    @Test
    void testCreditCardStrategyFields() {
        CreditCardPaymentStrategy payment = new CreditCardPaymentStrategy(
                "1234567890123456", 
                "Jane Smith", 
                "456", 
                "06/26"
        );
        
        assertEquals("1234567890123456", payment.getCardNumber());
        assertEquals("Jane Smith", payment.getCardHolderName());
        assertEquals("456", payment.getCvv());
        assertEquals("06/26", payment.getExpirationDate());
    }

    @Test
    void testPayPalStrategyFields() {
        PayPalPaymentStrategy payment = new PayPalPaymentStrategy(
                "user@paypal.com", 
                "securepass"
        );
        
        assertEquals("user@paypal.com", payment.getEmail());
        assertEquals("securepass", payment.getPassword());
    }
}
