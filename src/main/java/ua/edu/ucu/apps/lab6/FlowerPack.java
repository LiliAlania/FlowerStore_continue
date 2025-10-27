package ua.edu.ucu.apps.lab6;

import lombok.Data;

@Data
public class FlowerPack {
    private Flower flower;
    private int quantity;
    public FlowerPack(Flower flower, int quantity){
        this.flower = new Flower(flower);
        this.quantity = quantity;
    }
    public double getPrice(){
        return flower.getPrice() * quantity;
    }
}
