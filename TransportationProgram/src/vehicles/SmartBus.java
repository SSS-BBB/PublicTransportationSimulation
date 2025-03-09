package vehicles;

import java.util.Random;

import stops.StopSign;

public class SmartBus extends AirConditionedVehicle {
	
	private int peopleOnBus;
	private int maxPeopleOnBus; // maximum number of people on the bus
	private int maxSeatAmount; // maximum number of seat on the bus
	private int seatLeft; // how many seat left
	
	public SmartBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/busIcon.png");
		// TODO: add image
		Random rand = new Random();
		this.maxPeopleOnBus = 40;
		this.maxSeatAmount = 30;
		this.peopleOnBus = rand.nextInt(maxPeopleOnBus + 1);
		this.seatLeft = Math.max(0, maxSeatAmount - peopleOnBus);
		setSpeedVariance(0.5);
	}
	
	@Override
	public double fee(StopSign from, StopSign to) {
		// No subscription
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			// TODO: show error dialog
			System.out.println("Cannot find " + from.getStopID() + " and/or " + to.getStopID());
			return 0.0;	
		}
		int floor = (int) Math.ceil(numberStation / 4.0);
		if (floor == 0) return 0.0; // same from and to
		
		return 20.0 + floor*2.5;
	}
	
	public double subsriptionFee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == 0) return 0.0;
		return 15.0; // 15 baht throughout the line
	}
	
	@Override
	public void onUpdateCurrentStop() {
		super.onUpdateCurrentStop();
		
		// random people going in and out on each stop (assuming the tendency for people to get on and off each stop is equal)
		Random rand = new Random();
		
		// random people get off the bus
		peopleOnBus -= rand.nextInt(peopleOnBus + 1);
		// random people get on the bus
		peopleOnBus += rand.nextInt(maxPeopleOnBus - peopleOnBus + 1);
		
		seatLeft = Math.max(0, maxSeatAmount - peopleOnBus);
	}
	
	public int getNumberOfPeopleOnBus() {
		return peopleOnBus;
	}
	
	public int getSeatLeft() {
		return seatLeft;
	}
	
}
