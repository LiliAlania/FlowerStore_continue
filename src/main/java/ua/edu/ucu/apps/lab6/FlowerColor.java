package ua.edu.ucu.apps.lab6;

import lombok.Getter;

@Getter
public enum FlowerColor {
    RED("#FF0000"),
    BLUE(" #0000FF"),
    YELLOW("#FFFF00"),
    WHITE("#FFFFFF");

    private final String code;
    private FlowerColor(String code) {
        this.code = code;
    }
}
