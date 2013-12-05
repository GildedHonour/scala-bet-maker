package io.bet.betzilla.betcore;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class Setting {

    @Getter
    @Setter
    private HashMap<String, Double> settings = new HashMap<String, Double>();
}