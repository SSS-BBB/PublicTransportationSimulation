package vehicles;

import stops.*;

public class Van extends AirConditionedVehicle {

	public Van(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed,
			double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/busIcon.png");
		setSpeedVariance(1.2);
	}
	
	@Override
	public double fee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			// TODO: show error dialog
			System.out.println("Cannot find " + from.getStopID() + " and/or " + to.getStopID());
			return -1.0;	
		}
		int floor = (int) Math.ceil(numberStation / 4.0);
		if (floor == 0) return 0.0; // same from and to
		
		return 15.0 + floor*3.0;
	}

}