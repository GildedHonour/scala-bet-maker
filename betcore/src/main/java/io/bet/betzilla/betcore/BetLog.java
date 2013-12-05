package io.bet.betzilla.betcore;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class BetLog {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private Date timeSent;

    @Getter
    @Setter
    private int marketId;

    @Getter
    @Setter
    private int selectionId;

    @Getter
    @Setter
    private String market;

    @Getter
    @Setter
    private String direction;

    @Getter
    @Setter
    private Double odds;

    @Getter
    @Setter
    private Double amount;

    @Getter
    @Setter
    private String betStatus;

    @Getter
    @Setter
    private Double amountFilled;

    @Getter
    @Setter
    private String betfairBetId;

    @Getter
    @Setter
    private String betDescription;

    @Getter
    @Setter
    private int externalMarketId;

    @Getter
    @Setter
    private String externalCode;

    @Getter
    @Setter
    private String betSource;

    @Getter
    @Setter
    private int referenceBetId;

    @Getter
    @Setter
    private int exchangeId;

    @Getter
    @Setter
    private int fillCount;

    @Getter
    @Setter
    private String globalToleranceReason;

    @Getter
    @Setter
    private Date matchedAt;

    @Getter
    @Setter
    private Date updatedAt;

    @Getter
    @Setter
    private Date createdAt;


    public boolean isMatched() {
        return getBetStatus().equals("MATCHED");
    }

    public boolean isCancelled() {
        return getBetStatus().equals("CANCELLED");
    }

    public boolean isActive() {
        return getBetStatus().equals("PARTIAL") || getBetStatus().equals("UNMATCHED");
    }

    public boolean winMarket() {
        return (this.market.equals(MarketType.WIN));
    }

    public boolean placeMarket() {
        return (this.market.equals(MarketType.PLACE));
    }

    public boolean backBet() {
        return (this.direction.equals(Direction.BACK));
    }

    public boolean layBet() {
        return (this.direction.equals(Direction.LAY));
    }

    public long createdAtTime() {
        return createdAt.getTime();
    }

    public long updatedAtTime() {
        return updatedAt.getTime();
    }

    public boolean hasBetBeenSent() {
        if (this.betfairBetId == null)
            return false;
        return (!this.betfairBetId.equals(""));
    }
}
