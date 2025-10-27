package ua.edu.ucu.apps.lab6.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ucu.apps.lab6.FLowerType;
import ua.edu.ucu.apps.lab6.Flower;
import ua.edu.ucu.apps.lab6.FlowerColor;

@RestController
public class FlowerController {
    @GetMapping("/flowers")
    public List<Flower> getFlowers(){
        return List.of(
            new Flower(FlowerColor.RED, 50, 10, FLowerType.TULIP)
        );
    }

}
