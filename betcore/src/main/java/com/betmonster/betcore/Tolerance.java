package com.betmonster.betcore;

import java.util.Vector;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Tolerance 
{
	@Getter @Setter private String 		key;
	@Getter @Setter private Double  	value;
	@Getter @Setter private boolean		alert;

	public String toString()
	{
		return key + ":" + value + ":" + alert;
	}

}