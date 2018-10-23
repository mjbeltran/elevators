package com.sqli.test.beans;

import com.sqli.test.config.Config;

public class Elevator implements Comparable<Elevator> {

	private String idElevator;
	private int currentFloor;
	private String state;
	
	
	public Elevator(String id, int floor) {
		this.idElevator = id;
		this.currentFloor = floor;
		this.state = Config.DEFAULT_STATE;
	}
	
	public String getIdElevator() {
		return idElevator;
	}
	public void setIdElevator(String idElevator) {
		this.idElevator = idElevator;
	}
	public int getCurrentFloor() {
		return currentFloor;
	}
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public int compareTo(Elevator o) {
		return this.currentFloor - o.getCurrentFloor();
	}
	
	
}
