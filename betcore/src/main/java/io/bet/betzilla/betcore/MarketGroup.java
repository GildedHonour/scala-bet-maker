package io.bet.betzilla.betcore;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class MarketGroup
{
	@Getter @Setter	private int 	id;

	// @Getter @Setter	private boolean	hasTissuePrices;
	@Getter @Setter	private Double	bestFactor;
	@Getter @Setter	private Double	placeBestFactor;	

	@Getter @Setter	private Market 	winMarket;
	@Getter @Setter	private Market 	placeMarket;	

	@Getter @Setter	private ArrayList<BetLog>  betLogs;

	@Getter @Setter	private Enviroment enviroment;

	@Getter @Setter	private Setting settings;

	@Getter @Setter private HashMap<String,Tolerance>	tolerances;

	@Getter @Setter	private Date updateTime;

	@Getter @Setter	private Date previousUpdateTime;

	@Getter @Setter	private Date lastUpdateBetLogs;

	@Getter @Setter	private Date  	 ratingUpdatedAt;
	@Getter @Setter	private Double   raceRating;
	@Getter @Setter	private boolean  hasRatings;	

	@Getter @Setter	private String  countryCode;	

	@Getter @Setter private ArrayList<NonRunner> 	winMarketNonRunners = new ArrayList<NonRunner>();
	@Getter @Setter private ArrayList<NonRunner> 	placeMarketNonRunners = new ArrayList<NonRunner>();

	@Getter @Setter private boolean status;
	@Getter @Setter private boolean pricesChanged = false;

	public int winMarketId()
	{
		return getWinMarket().getId();
	}

	public int placeMarketId()
	{
		return getPlaceMarket().getId();
	}	


	public Runner getRunner(String marketType,BetSelector bs)
	{
		return getRunnerById(marketType,bs.getSelectionId());
	}

	public Runner getRunnerById(String marketType,int selectionId)
	{
		if (marketType.equals(MarketType.WIN))
		{
			return winMarket.getRunner(selectionId);
		}
		else
		{
			return placeMarket.getRunner(selectionId);
		}
	}	

	public Market getMarket(int marketId)
	{
		if (marketId == winMarketId())
			return getWinMarket();
		else
			return getPlaceMarket();
	}

	public ArrayList<BetAction> generateBets()
	{
		 ArrayList<BetAction> list = new  ArrayList<BetAction>();
		 return list;
	}

	


}