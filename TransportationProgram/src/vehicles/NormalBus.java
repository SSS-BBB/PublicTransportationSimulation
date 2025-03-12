package vehicles;

import program_designs.ProgramColor;
import stops.SkyTrainStopSign;
import stops.StopSign;
import stops.TrainStopSign;

public class NormalBus extends Vehicle {

	public NormalBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, "/icons/busIcon.png");
		this.routeColor = ProgramColor.LIGHT_BLUE;
	}
	
	/*
	public NormalBus(String vehicleName, String vehicleLicence, StopSign[] stops, 
			double waitInterval, double averageSpeed,
			int stopIndex, boolean forward) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, stopIndex, forward, "/icons/busIcon.png");
	}
	*/

	@Override
	public double fee(StopSign from, StopSign to) {
		if (from.getStopID().equals(to.getStopID()))
			return 0; // same stop selected
		
		return 8.0; // 8 baht throughout all stops
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
