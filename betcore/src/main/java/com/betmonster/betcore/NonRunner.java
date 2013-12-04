package com.betmonster.betcore;

// import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class NonRunner
{
	@Getter @Setter  private Runner 		runner;
	@Getter @Setter	 private Date 			nonRunnerAt;

	public Long getNonRunnerAtTime()
	{
		return nonRunnerAt.getTime();
	}
}