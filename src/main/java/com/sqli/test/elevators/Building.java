package com.sqli.test.elevators;

import java.util.Stack;

import com.sqli.test.beans.Elevator;

public class Building implements IBuildinElevator{

//	private int numberOfFloors;
	private Stack<Elevator> allElevators;
	private BuildingManager buildingManager;

	public Building(int numberOfFloors, String... elevators) {
		
		buildingManager = BuildingManager.getInstance();
//		this.numberOfFloors = numberOfFloors;
		allElevators = buildingManager.initElevators(numberOfFloors,elevators);
	}

	public String requestElevator() {
		
		return buildingManager.getCloserElevator(allElevators);
	}

	public void move(String idElevator, String direction) {

		buildingManager.moveDirection(idElevator, direction,allElevators);

	}


	public void stopAt(String idElevator, int floor) {
		
		buildingManager.stopAtFloor(idElevator, floor,allElevators);
	
	}

	public String requestElevator(int floor) {

		return buildingManager.requestElevatorClosedFloor(floor,allElevators);
		
	
	}

}
