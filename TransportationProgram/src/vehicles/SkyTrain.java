package vehicles;

import java.awt.Color;

import stops.*;
import stops.TrainStopSign;

public class SkyTrain extends AirConditionedVehicle {

	public SkyTrain(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/skyTrainIcon.png");
		// TODO: add image
		setSpeedVariance(0);
		stationWait = 0.0167;
		this.routeColor = Color.blue;
		
		this.iconWidth = 25;
		this.iconHeight = 25;
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
	
	@Override
	public boolean validStop() {
		for (StopSign stop : stops) {
			// sky train must use only sky train stop
			if (!(stop instanceof SkyTrainStopSign)) return false;
		}
		return true;
	}

}
