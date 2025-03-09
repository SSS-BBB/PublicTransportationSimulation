package vehicles;

import java.awt.Color;

import program_designs.ProgramColor;
import stops.StopSign;

public class MiniBus extends Vehicle {

	public MiniBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, "/icons/busIcon.png");
		// TODO: add image
		setSpeedVariance(1.5);
		this.routeColor = Color.red;
	}

	@Override
	public double fee(StopSign from, StopSign to) {
		return 8.0;
	}

}
