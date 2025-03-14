package vehicles;

import java.awt.Color;

import program_designs.ProgramColor;
import stops.SkyTrainStopSign;
import stops.StopSign;
import stops.TrainStopSign;

public class Train extends Vehicle {

	public Train(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, "/icons/trainIcon.png");
		// TODO: add image
		setSpeedVariance(0);
		stationWait = 0.0167;
		this.routeColor = new Color(244,164,96);
		
		this.iconWidth = 60;
		this.iconHeight = 60;
	}

	@Override
	public double fee(StopSign from, StopSign to) {
		int numberStation = numberOfStation(from, to);
		if (numberStation == -1) {
			// TODO: show error dialog
			System.out.println("Cannot find " + from.getStopID() + " and/or " + to.getStopID());
			return -1.0;	
		}
		int floor = (int) Math.ceil(numberStation / 5.0);
		if (floor == 0) return 0.0; // same from and to
		
		return 5.0 + floor*1.0;
	}
	
	@Override
	public boolean validStop() {
		for (StopSign stop : stops) {
			// train must use only train stop
			if (!(stop instanceof TrainStopSign)) return false;
		}
		return true;
	}

}
