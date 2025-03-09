package vehicles;

import program_designs.ProgramColor;
import stops.StopSign;

public class AirConditionedBus extends AirConditionedVehicle {

	public AirConditionedBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, temperature, "/icons/busIcon.png");
		// TODO: add image
		setSpeedVariance(0.8);
		this.routeColor = ProgramColor.LIGHT_BLUE;
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
		
		return 10.0 + floor*2.0;
	}

}
