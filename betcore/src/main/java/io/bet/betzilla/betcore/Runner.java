package io.bet.betzilla.betcore;

// import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Runner {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int selectionId;

    @Getter
    @Setter
    private Double backPrice1;

    @Getter
    @Setter
    private Double layPrice1;

    @Getter
    @Setter
    private Double backAmount1;

    @Getter
    @Setter
    private Double layAmount1;

    @Getter
    @Setter
    private Double totalAmountMatched;

    @Getter
    @Setter
    private Double lastPriceMatched;

    @Getter
    @Setter
    private Double reductionFactor;

    @Getter
    @Setter
    private Double bqBackEdge;

    @Getter
    @Setter
    private Double bqLayEdge;

    @Getter
    @Setter
    private String tissueRawRating;

    @Getter
    @Setter
    private Double tissueMcRating;

    @Getter
    @Setter
    private String tissueRatingComment;

    @Getter
    @Setter
    private Double tissueFvCombinedOdds;

    @Getter
    @Setter
    private Double tissueFvCombinedNcdi;

    @Getter
    @Setter
    private Double flop;

    @Getter
    @Setter
    private Double volatility;

    @Getter
    @Setter
    private Double mcPlaceNcdiAdj;

    @Getter
    @Setter
    private boolean usingMedian;

    @Setter
    private Double mcPlaceFv;

    @Setter
    private Double mcWinFv;

    @Getter
    @Setter
    private Double mcTissuePlaceNcdiAdj;

    @Getter
    @Setter
    private Date backPriceChangedAt;

    @Getter
    @Setter
    private Date layPriceChangedAt;

    @Getter
    @Setter
    private Date previousBackPriceChangedAt;

    @Getter
    @Setter
    private Date previousLayPriceChangedAt;

    @Getter
    @Setter
    private Date secondPreviousBackPriceChangedAt;

    @Getter
    @Setter
    private Date secondPreviousLayPriceChangedAt;

    @Getter
    @Setter
    private boolean status;

    @Getter
    @Setter
    private String betDaqId;


    @Getter
    @Setter
    private int marketId;


    @Getter
    @Setter
    private String silk;    //ignore for now

    @Getter
    @Setter
    private String jtoElpd; //ignore for now

    private Runner winRunner;
    private MarketGroup marketPair;

    static private Double epsilon = 0.001;

    public boolean valid() {
        return status;
    }

    public Double backOdds() {
        return getBackPrice1();
    }

    public Double layOdds() {
        return getLayPrice1();
    }

    public Double backAmount() {
        return getBackAmount1();
    }

    public Double layAmount() {
        return getLayAmount1();
    }

}