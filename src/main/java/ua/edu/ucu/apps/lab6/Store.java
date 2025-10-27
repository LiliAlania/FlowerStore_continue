package ua.edu.ucu.apps.lab6;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<FlowerBucket> buckets = new ArrayList<>();

    public void addBucket(FlowerBucket bucket) {
        buckets.add(bucket);
    }

    public List<FlowerBucket> search(FlowerPack sample) {
        List<FlowerBucket> result = new ArrayList<>();
        for (FlowerBucket bucket : buckets) {
            for (FlowerPack pack : bucket.getFlowerPacks()) {
                if (pack.getFlower().getClass() == sample.getFlower().getClass()
                        && pack.getQuantity() >= sample.getQuantity()) {
                    result.add(bucket);
                    break;
                }
            }
        }
        return result;
    }
}
