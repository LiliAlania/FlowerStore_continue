package ua.edu.ucu.apps.lab6;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket implements Item{
    private List<FlowerPack> flowerPacks;
    public FlowerBucket(){
        this.flowerPacks = new ArrayList<FlowerPack>();
    }
    public void add(FlowerPack flowerpack){
        flowerPacks.add(flowerpack);
    }
    @Override
    public double getPrice(){
        return flowerPacks.stream()
            .mapToDouble(pack ->pack.getPrice()).sum();

    }
    public List<FlowerPack> getFlowerPacks() {
    return flowerPacks;
    }

}
