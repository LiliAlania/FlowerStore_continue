package ua.edu.ucu.apps.lab6;

public class Main {
    public static void main(String[] args) {
        Flower flower = new Flower();
        flower.setColor(FlowerColor.RED);
        flower.setPrice(70);
        flower.setSepalLength(10);
        flower.setFlowerType(FLowerType.ROSE);

        FlowerPack flowerPack = new FlowerPack(flower, 11);
        flowerPack.setFlower(flower);
        flowerPack.setQuantity(11);

        FlowerBucket flowerBucket = new FlowerBucket();
        flowerBucket.add(flowerPack);
        flower.setPrice(100);
        System.out.println(flowerBucket.getPrice());
    }
}