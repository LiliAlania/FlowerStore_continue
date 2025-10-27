package ua.edu.ucu.apps.lab6;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Lab6Test {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetRandomUUIDs_ReturnsTenUUIDs() {
        ResponseEntity<String[]> response =
                restTemplate.getForEntity("/list", String[].class);
        assertEquals(10, response.getBody().length);
        for (String uuid : response.getBody()) {
            assertDoesNotThrow(() -> java.util.UUID.fromString(uuid));
        }
    }

    @Test
    public void testChamomileAndTulipAreFlowers() {
        Flower chamomile = new Chamomile();
        Flower tulip = new Tulip();

        assertTrue(chamomile instanceof Flower);
        assertTrue(tulip instanceof Flower);
    }

    @Test
    public void testStoreSearchFindsMatchingBucket() {
        Flower rose = new Flower();
        rose.setFlowerType(FLowerType.ROSE);
        rose.setPrice(50);
        rose.setColor(FlowerColor.RED);

        FlowerPack pack = new FlowerPack(rose, 10);
        FlowerBucket bucket = new FlowerBucket();
        bucket.add(pack);

        Store store = new Store();
        store.addBucket(bucket);

        Flower sampleFlower = new Flower();
        sampleFlower.setFlowerType(FLowerType.ROSE);
        FlowerPack samplePack = new FlowerPack(sampleFlower, 5);

        List<FlowerBucket> result = store.search(samplePack);

        assertEquals(1, result.size());
        assertTrue(result.contains(bucket));
    }


    @Test
    public void testFlowerBucketCalculatesTotalPrice() {
        Flower flower = new Flower();
        flower.setPrice(20);
        FlowerPack pack1 = new FlowerPack(flower, 3);
        FlowerPack pack2 = new FlowerPack(flower, 2);

        FlowerBucket bucket = new FlowerBucket();
        bucket.add(pack1);
        bucket.add(pack2);

        assertEquals(100, bucket.getPrice());
    }

    @Test
    public void testMainMethodRunsWithoutException() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
