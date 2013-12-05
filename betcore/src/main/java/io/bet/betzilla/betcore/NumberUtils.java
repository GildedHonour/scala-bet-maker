package io.bet.betzilla.betcore;

import java.text.DecimalFormat;

public class NumberUtils {
    static public Double roundTo2DecimalPlaces(Double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}

