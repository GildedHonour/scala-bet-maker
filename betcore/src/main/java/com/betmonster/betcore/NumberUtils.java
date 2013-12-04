package com.betmonster.betcore;

import java.text.DecimalFormat;

public class NumberUtils
{
	static	public Double roundTo2DecimalPlaces(Double d)
	{
		// System.out.println("d = " + d);
		DecimalFormat twoDForm = new DecimalFormat("#.##");
    	return Double.valueOf(twoDForm.format(d));	
    }
}

