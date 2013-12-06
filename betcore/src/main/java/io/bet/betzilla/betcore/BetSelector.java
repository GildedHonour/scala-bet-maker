package io.bet.betzilla.betcore;

import lombok.Data;

public
@Data
class BetSelector {
    private int winMarketId;
    private int placeMarketId;
    private int marketId;
    private int selectionId;
    private String direction;
    private String betSource;
    private String matched;
    private String marketType;

    public BetSelector() {
        this.winMarketId = -1;
        this.placeMarketId = -1;
    }

    public BetSelector(BetLog bet) {
        this.winMarketId = -1;
        this.placeMarketId = -1;
        this.marketId = bet.getMarketId();
        this.selectionId = bet.getSelectionId();
        this.direction = bet.getDirection();
        this.betSource = bet.getBetSource();
    }

    public BetSelector(int marketId, int selectionId, String direction, String betSource) {
        this.winMarketId = -1;
        this.placeMarketId = -1;
        this.marketId = marketId;
        this.selectionId = selectionId;
        this.direction = direction;
        this.betSource = betSource;
    }

    public BetSelector(BetSelector another) {
        this.winMarketId = another.winMarketId;
        this.placeMarketId = another.placeMarketId;
        this.marketId = another.marketId;
        this.selectionId = another.selectionId;
        this.marketType = another.marketType;
        this.matched = another.matched;
        this.direction = another.direction;
        this.betSource = another.betSource;
    }

    public void setMarketByType(String marketType) {
        setMarketType(marketType);
        if (marketType.equals(MarketType.WIN)) {
            if (this.winMarketId != -1)
                this.marketId = this.winMarketId;
        } else {
            if (this.placeMarketId != -1)
                this.marketId = this.placeMarketId;
        }
    }

    public String toString() {
        return winMarketId + "," + placeMarketId + "," + marketId + "," + selectionId + "," +
                direction + "," + betSource + "," + matched + "," + marketType;
    }
}