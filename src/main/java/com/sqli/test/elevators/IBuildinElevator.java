package com.sqli.test.elevators;

public interface IBuildinElevator {

	String requestElevator();

	void move(String idElevator, String direction);

	void stopAt(String idElevator, int floor);
}
