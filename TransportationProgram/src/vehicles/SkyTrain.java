package vehicles;

import java.awt.Color;

import stops.StopSign;

public class SkyTrain extends AirConditionedVehicle {

	public SkyTrain(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/busIcon.png");
		// TODO: add image
		setSpeedVariance(0);
		stationWait = 0.0167;
		this.routeColor = Color.blue;
	}
	
	@Override
	public double fee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			// TODO: show error dialog
			System.out.println("Cannot find " + from.getStopID() + " and/or " + to.getStopID());
			return -1.0;	
		}
		int floor = (int) Math.ceil(numberStation / 1.0);
		if (floor == 0) return 0.0; // same from and to
		
		return 20.0 + floor*4.0;
	}

}
