package com.betmonster.betcore;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Date;

public class Market
{
	@Getter @Setter	private int 	id;
	@Getter @Setter	private String 	name;

	@Getter @Setter private Integer	bfMarketId;
	@Getter @Setter private Integer	marketId;

	@Getter @Setter private String	marketStatus;
	@Getter @Setter private String	marketName;
	@Getter @Setter private String	marketDescription;
	@Getter @Setter private String 	betDaqId;

	@Getter @Setter	private Date bfLastUpdateTime;
	@Getter @Setter	private Date bqLastUpdateTime;

	@Getter @Setter	private Date bfPreviousUpdateTime;
	@Getter @Setter	private Date bqPreviousUpdateTime;	

	@Getter @Setter	private int  numberOfWinners;

	// @Getter @Setter private MarketGroup	marketPair;

	@Getter @Setter private ArrayList<Runner>	runners;
  	@Getter @Setter private Date       eventDate; 

	public ArrayList<Runner>	activeRunners()
	{
		ArrayList<Runner>	ret = new ArrayList<Runner>();
		for(Runner r: runners)
		{
			if (r.valid())
			{
				ret.add( r );
			}
		}
		return ret;
	}

	public Runner getRunner(int selectionId)
	{
		for(Runner r: runners)
		{
			if (r.getSelectionId() == selectionId)
			{
				return r;
			}
		}
		return null;		
	}

}