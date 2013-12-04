package com.betmonster.betcore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Date;

import lombok.Data;

public @Data class BetAction
{
	public enum ActionMode { IGNORE, CREATE, REVERSE, CANCEL, FAILED, INFO, DEBUG }

	private ActionMode actionMode;
	private String message;
	private int betId;

	//new bet details
	private Double odds;
	private Double amount;
	
	private String betSource;
	private String marketType;
	private String direction;
	private int	selectionId;
	private int	marketId;

	private int referenceBetId;

	public BetAction()
	{}

	public BetAction(ActionMode actionMode,String message,BetLog betLog)
	{
		message = message.replace("\n", ":").replace(",", " ");

		// System.out.println("new BetAction. " + actionMode.toString() + " : " + message);
		this.actionMode = actionMode;
		this.message = message;

		if (betLog != null)
		{
            this.betId = betLog.getId();
            this.direction = betLog.getDirection();
			this.betSource = betLog.getBetSource();
			this.marketType = betLog.getMarket();
			this.selectionId = betLog.getSelectionId();
			this.marketId = betLog.getMarketId();
		}
	}

	public String toString()
	{
		Double roundedOdds = null;
		if (odds != null)
			roundedOdds = NumberUtils.roundTo2DecimalPlaces(odds);
		Double roundedAmount = null;
		if (amount != null)
			roundedAmount = NumberUtils.roundTo2DecimalPlaces(amount);
		
		String result = actionMode.toString() + "," + message + "," + betId + "," + roundedOdds + "," + roundedAmount + "," + betSource + "," + marketType + "," + direction + "," + selectionId + "," + referenceBetId + "," + marketId;
			
		result = result.replace("null","");

		return result;
	}

	static boolean hasActionForBet(ArrayList<BetAction> betActions,BetLog bet,ActionMode action)
	{
		for (BetAction ba: betActions)
		{
			if (ba.getActionMode() == action && ba.getBetId() == bet.getId())
				return true;
		}		
		return false;
	}

    static public void mergeValidBets(ArrayList<BetAction> source,ArrayList<BetAction> dest)
    {
      for(BetAction ba: source)
      {
        if (ba != null && ba.getActionMode() != BetAction.ActionMode.IGNORE ) 
        {
          dest.add( ba );
        }
      }
    }	

}