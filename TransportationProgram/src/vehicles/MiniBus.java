package vehicles;

import java.awt.Color;

import stops.SkyTrainStopSign;
import stops.StopSign;
import stops.TrainStopSign;

public class MiniBus extends Vehicle {

	public MiniBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, "/icons/miniBus.png");
		setSpeedVariance(10);
		this.routeColor = Color.red;
		this.iconWidth = 23;
		this.iconHeight = 23;
	}

	@Override
	public double fee(StopSign from, StopSign to) {
		if (from.getStopID().equals(to.getStopID()))
			return 0; // same stop selected
		
		return 8.0;
	}
	
	@Override
	public boolean validStop() {
		for (StopSign stop : stops) {
			// cannot use train stop sign for non train vehicle
			if (stop instanceof TrainStopSign) return false;
			
			// cannot use sky train stop sign for non sky train vehicle
			else if (stop instanceof SkyTrainStopSign) return false;
		}
		return true;
	}

}
