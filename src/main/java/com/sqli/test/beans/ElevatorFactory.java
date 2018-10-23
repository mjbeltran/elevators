package com.sqli.test.beans;

import com.sqli.test.config.Config;

public class ElevatorFactory {

	public static Elevator newElevator(String dataElevator) {
		return new Elevator(dataElevator.split(Config.SEPARATOR_DATA_ELEVATOR)[0],
				Integer.valueOf(dataElevator.split(Config.SEPARATOR_DATA_ELEVATOR)[1]));
	}
}
