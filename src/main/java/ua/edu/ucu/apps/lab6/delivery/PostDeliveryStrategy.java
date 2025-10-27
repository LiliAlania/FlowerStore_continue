package ua.edu.ucu.apps.lab6.delivery;

public class PostDeliveryStrategy implements Delivery{
    @Override
    public double delivery(double orderPrice){
    if (orderPrice > 1000){
        return 0;
    }
    return 100;
    }
    

}
