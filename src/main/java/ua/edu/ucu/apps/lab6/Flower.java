package ua.edu.ucu.apps.lab6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Flower {
    FlowerColor color;
    double price;
    double sepalLength;
    FLowerType flowerType;
    public Flower(Flower other){
        this.color = other.color;
        this.flowerType = other.flowerType;
        this.sepalLength = other.sepalLength;
        this.price = other.price;
    }
    public String getColor() {
        return color.getCode();
    }
}
