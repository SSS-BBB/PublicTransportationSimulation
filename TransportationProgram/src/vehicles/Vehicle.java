package vehicles;

import java.util.Random;

import stops.StopSign;
import useful.*;

public abstract class Vehicle {
	
	protected String vehicleName;
	protected String vehicleLicence;
	
	protected StopSign[] stops; // every stop from the first(0th index) to the last stop((n-1)th index)
	protected double[] stopDistance; // distance between the stop and the next stop
	protected StopSign currentStop;
	protected StopSign nextStop;
	
	protected boolean forward; // direction of the bus going either forward(first -last) or backward(last - first)
	protected double averageSpeed;
	protected double waitInterval; // how long does it take for the vehicle to start driving when it just arrives at the 0th stop
	protected double waiting; // time left before driver starts driving again
	protected double currentSpeed[]; // [vx, vy]
	protected double position[]; // exact position of the vehicle
	
	
	
	// Calculating fee according to the stop
	public abstract double fee(StopSign from, StopSign to);
	
	public Vehicle(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed) {
		this.vehicleName = vehicleName;
		this.vehicleLicence = vehicleLicence;
		this.stops = stops;
		this.waitInterval = waitInterval;
		this.averageSpeed = averageSpeed;
		
		
		// randomize where the bus is
		Random rand = new Random();
		int stopIndex = rand.nextInt(stops.length);
		currentStop = this.stops[stopIndex];
		if (stopIndex == stops.length - 1) {
			// going backward when the vehicle is at the last stop
			forward = false;
		}
		else if (stopIndex == 0) {
			// going forward when the vehicle is at the first stop
			forward = true;
		}
		else {
			// random direction
			forward = rand.nextBoolean();
		}
		
		calculateStopDistance();
		setNextStop();
		setPostionFromCurrentStop();
		setWaitingTime();
		
		currentSpeed = UsefulFunc.getCertainSizeVectorFromTwoPoints(position, nextStop.getPosition(), averageSpeed);
	}
	
	public Vehicle(String vehicleName, String vehicleLicence, StopSign[] stops, 
			double waitInterval, double averageSpeed,
			int stopIndex, boolean forward) {
		
		// set where which stop the bus is, and where it is heading
		this.vehicleName = vehicleName;
		this.vehicleLicence = vehicleLicence;
		this.stops = stops;
		this.waitInterval = waitInterval;
		this.averageSpeed = averageSpeed;
		
		this.currentStop = this.stops[stopIndex];
		this.forward = forward;
		
		// prevent some weird situation Ex. Vehicle is at the first stop, but somehow going backward
		if (stopIndex == 0) forward = true;
		else if (stopIndex == stops.length - 1) forward = false;
		
		calculateStopDistance();
		setNextStop();
		setPostionFromCurrentStop();
		setWaitingTime();
		
		currentSpeed = UsefulFunc.getCertainSizeVectorFromTwoPoints(position, nextStop.getPosition(), averageSpeed);
		
	}
	
	protected void setWaitingTime() {
		// only set if the bus is at the first or last stop
		if (getStopIndex(currentStop) == 0 || getStopIndex(currentStop) == stops.length - 1) {
			waiting = waitInterval;
		}
		else {
			waiting = 0;
		}
	}
	
	protected void setNextStop() {
		int stopIndex = getStopIndex(currentStop);
		
		if (forward) {
			if (stopIndex < stops.length - 1) {
				// not last index
				nextStop = stops[stopIndex + 1];
			}
			else {
				// last index
				nextStop = stops[stopIndex - 1];
				forward = false; // ขากลับ
			}
		}
		else {
			if (stopIndex > 0) {
				// not first index
				nextStop = stops[stopIndex - 1];
			}
			else {
				// first index
				nextStop = stops[stopIndex + 1];
				forward = true; // ขาไป
			}
		}
	}
	
	protected void calculateStopDistance() {
		stopDistance = new double[stops.length - 1];
		// nth index is the distance between nth stop and (n + 1)th stop
		for (int i = 0; i < stopDistance.length; i++) {
			double x_dist = Math.pow(stops[i + 1].getPosition()[0] - stops[i].getPosition()[0], 2.0);
			double y_dist = Math.pow(stops[i + 1].getPosition()[1] - stops[i].getPosition()[1], 2.0);
			
			stopDistance[i] = Math.sqrt(x_dist + y_dist); // distance
		}
	}
	
	protected void setPostionFromCurrentStop() {
		position = new double[2];
		position[0] = currentStop.getPosition()[0];
		position[1] = currentStop.getPosition()[1];
	}
	
	protected void setCurrentStopFromPosition(double minDistanceToStop) {
		for (StopSign stop : stops) {
			// if the vehicle is close enough to some stop, then the current stop will be reset to that stop.
			if (UsefulFunc.distanceBetweenTwoPoints(position, stop.getPosition()) <= minDistanceToStop) {
				currentStop = stop;
				setNextStop();
				break;
			}
		}
	}
	
	
	
	
	public String getVehicleName() {
		return vehicleName;
	}
	
	public double getAverageSpeed() {
		return averageSpeed;
	}
	
	public StopSign getCurrentStop() {
		return currentStop;
	}
	
	public double[] getCurrentSpeed() {
		return currentSpeed;
	}
	
	public double[] getVehiclePostion() {
		return position;
	}
	
	public StopSign getNextStop() {
		return nextStop;
	}
	
	protected double distanceAccumulated(int from, int to) {
		// accumulate the distance from the stopDistance array
		if (to >= stops.length) return 0.0;
		
		if (to < from) {
			// swap value
			int temp = from;
			from = to;
			to = temp;
		}
		
		double dist = 0.0;
		
		for (int i = from; i < to; i++) {
			dist += stopDistance[i];
		}
		
		return dist;
	}
	
	// Calculate distance between the current stop(current stop of the vehicle) and the certain stop(the stop that we want to know the distance)
	// KM ?
	public double distanceToTargetStop(StopSign targetStop) {
		int stopIndex = getStopIndex(targetStop);
		int currentStopIndex = getStopIndex(currentStop);
		if (stopIndex < 0) {
			return -1;
		}
		
		double dist = 0;
		
		// if the stop is the same as the current stop
		if (stopIndex - currentStopIndex == 0) dist = 0;
		
		// if the direction of the bus is heading to the stop
		else if ( ((stopIndex - currentStopIndex) > 0) == forward ) {
			dist = distanceAccumulated(currentStopIndex, stopIndex);
		}
		
		// if the direction of the bus is NOT heading to the stop
		else {
			if (forward) {
				StopSign lastStop = stops[stops.length - 1];
				int lastStopIndex = getStopIndex(lastStop);
				
				// Calculate distance between the current stop to the last stop
				double currentToLastDist = distanceAccumulated(currentStopIndex, lastStopIndex);
				// Calculate distance between last stop to the stop
				double lastToStopDist = distanceAccumulated(lastStopIndex, stopIndex);
				
				dist = currentToLastDist + lastToStopDist;
			}
			else {
				StopSign firstStop = stops[0];
				int firstStopIndex = getStopIndex(firstStop);
				
				// Calculate distance between the current stop to the first stop
				double currentToFirstToCurrentDist = distanceAccumulated(currentStopIndex, firstStopIndex);
				// Calculate distance between first stop to the stop
				double firstToStopDist = distanceAccumulated(firstStopIndex, stopIndex);
				
				dist = currentToFirstToCurrentDist + firstToStopDist;
			}
		}
		
		// For accurate distance
		dist -= UsefulFunc.distanceBetweenTwoPoints(position, currentStop.getPosition());
		
		return Math.abs(dist);
		
	}
	
	// Hour ??
	public double getEstimatedTime(StopSign stop) {
		double time = distanceToTargetStop(stop) / averageSpeed;
		
		int currentStopIndex = getStopIndex(currentStop);
		int stopIndex = getStopIndex(stop);
		// Check if the bus will stop and wait at the 0th stop or last stop
		if (currentStopIndex == 0 || currentStopIndex == stops.length - 1) {
			// Vehicle is at the 0th or last stop meaning it has to stop before driving again
			time += waiting;
		}
		
		else if ( (stopIndex - currentStopIndex) > 0 && !forward ) {
			// Vehicle going backward and forward again to reach the stop that we're at which means it will reach 0th stop and rest at some point
			time += waitInterval;
		}
		
		else if ( (stopIndex - currentStopIndex) < 0 && forward ) {
			// Vehicle going forward and backward again to reach the stop that we're at which means it will reach the last stop and rest at some point
			time += waitInterval;
		}
		
		return time;
	}
	
	public void update(double deltaTime) {
		
		// check if the vehicle is waiting
		double t = deltaTime;
		if (waiting > 0) {
			if (waiting - deltaTime <= 0) {
				waiting = 0;
				t += Math.abs(waiting - deltaTime);
			}
		}
		
		// update speed
		Random rand = new Random();
		currentSpeed = UsefulFunc.getCertainSizeVectorFromTwoPoints(position, nextStop.getPosition(), averageSpeed);
		// speed will not be the same all the time, so there might be some random increase or decrease of speed
		currentSpeed[0] +=  (2*rand.nextDouble() - 1) * rand.nextDouble();
		currentSpeed[1] +=  (2*rand.nextDouble() - 1) * rand.nextDouble();
		
		// update position
		/*
		position[0] += (nextStop.getPosition()[0] - position[0]) * t;
		position[1] += (nextStop.getPosition()[1] - position[1]) * t;
		*/
		
		double prevPos[] = new double[2]; // position before being updated
		prevPos[0] = position[0];
		prevPos[1] = position[1];
		
		position[0] += currentSpeed[0] * t;
		position[1] += currentSpeed[1] * t;
		
		// update current stop
		// calculate the distance between the travel line and the stop
		// double a = position[1] - prevPos[1];
		// double b = prevPos[0] - position[0];
		// double c = (position[0]-prevPos[0])*position[1] - (position[1]-prevPos[1])*position[0];
		// double traveledLineDist = Math.abs(a*nextStop.getPosition()[0] + b*nextStop.getPosition()[1] + c) / Math.sqrt(a*a + b*b);
		
		// only reset if it's close enough
		if (UsefulFunc.distanceBetweenTwoPoints(position, nextStop.getPosition()) <= averageSpeed*deltaTime) {
			int nextStopIndex = getStopIndex(nextStop);
			currentStop = stops[nextStopIndex];
			System.out.println("Update Stop");
			setNextStop();
		}
		
		// setCurrentStopFromPosition(averageSpeed*deltaTime);
		
	}
	
	// get index of an array given the stop
	public int getStopIndex(StopSign stop) {
		for (int i = 0; i < stops.length; i++) {
			if (stops[i].getStopID().equals(stop.getStopID())) {
				return i;
			}
		}
		
		return -1; // cannot find the stop in the array
	}
	
	public String vehicleDirection() {
		return forward ? "forward" : "backward";
	}
	
}
