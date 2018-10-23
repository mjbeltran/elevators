package com.sqli.test.elevators;

import java.util.Collections;
import java.util.Stack;

import com.sqli.test.beans.Elevator;
import com.sqli.test.beans.ElevatorFactory;
import com.sqli.test.config.Config;

public class BuildingManager {

	private static BuildingManager buildingManager;
	private int numberOfFloors;

	public static BuildingManager getInstance() {

		if (buildingManager == null) {
			buildingManager = new BuildingManager();
		}
		return buildingManager;
	}

	public Stack<Elevator> initElevators(int numberOfFloors2, String[] elevators) {

		Stack<Elevator> allElevatorsManager = new Stack<Elevator>();

		this.numberOfFloors = numberOfFloors2;
		for (String elevator : elevators) {
			allElevatorsManager.push(ElevatorFactory.newElevator(elevator));
		}

		Collections.sort(allElevatorsManager);

		return allElevatorsManager;
	}

	public String getCloserElevator(Stack<Elevator> allElevators) {
		Stack<Elevator> allElevatorsAux = allElevators;
		Elevator elev;
		while (!allElevatorsAux.isEmpty()) {
			elev = allElevators.pop();
			if (elev.getState().equals(Config.UP_STATE) || elev.getState().equals(Config.DEFAULT_STATE)) {
				return elev.getIdElevator();
			}
		}
		return "";

	}

	private Elevator getElevatorById(String idElevator, Stack<Elevator> allElevators2) {

		for (Elevator elev : allElevators2) {
			if (idElevator.equals(elev.getIdElevator())) {
				return elev;
			}
		}
		return null;
	}

	public void moveDirection(String idElevator, String direction, Stack<Elevator> allElevators) {
		Elevator e = getElevatorById(idElevator, allElevators);

		switch (direction) {
		case Config.UP_STATE:
			if (e.getCurrentFloor() == numberOfFloors) {
				e.setState(Config.DOWN_STATE);
				// e.setCurrentFloor(e.getCurrentFloor() - 1);
			} else {
				e.setCurrentFloor(e.getCurrentFloor() + 1);
				e.setState(Config.UP_STATE);
			}
			break;
		case Config.DOWN_STATE:
			if (e.getCurrentFloor() == 0) {
				// e.setCurrentFloor(e.getCurrentFloor() + 1);
				e.setState(Config.UP_STATE);
			}
			e.setCurrentFloor(e.getCurrentFloor() - 1);
			e.setState(Config.DOWN_STATE);
			break;
		default:
			break;
		}

		Collections.sort(allElevators);

	}

	public void stopAtFloor(String idElevator, int floor, Stack<Elevator> allElevators) {
		Elevator e = getElevatorById(idElevator, allElevators);
		e.setCurrentFloor(floor);
		e.setState(Config.RESTING_STATE);
		Collections.sort(allElevators);

	}

	public String requestElevatorClosedFloor(int floor, Stack<Elevator> allElevators) {
		int minD = numberOfFloors - 1;
		Stack<Elevator> elevatorAux = allElevators;
		Elevator currentElev;
		Elevator resultElev = null;
		while (!elevatorAux.isEmpty()) {
			currentElev = elevatorAux.pop();
			if (Math.abs(currentElev.getCurrentFloor() - floor) < minD) {
				minD = Math.abs(currentElev.getCurrentFloor() - floor);
				resultElev = currentElev;
			}
		}
		if (resultElev != null) {
			return resultElev.getIdElevator();
		} else {
			return "";
		}

	}
}
