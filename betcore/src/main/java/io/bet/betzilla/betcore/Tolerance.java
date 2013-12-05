package io.bet.betzilla.betcore;

import lombok.Getter;
import lombok.Setter;

public class Tolerance {
    @Getter
    @Setter
    private String key;

    @Getter
    @Setter
    private Double value;

    @Getter
    @Setter
    private boolean alert;

    public String toString() {
        return key + ":" + value + ":" + alert;
    }
}