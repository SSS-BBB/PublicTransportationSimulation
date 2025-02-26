package vehicles;

import stops.StopSign;

public class NormalBus extends Vehicle {

	public NormalBus(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval, double averageSpeed) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed);
	}
	
	public NormalBus(String vehicleName, String vehicleLicence, StopSign[] stops, 
			double waitInterval, double averageSpeed,
			int stopIndex, boolean forward) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, stopIndex, forward);
	}

	@Override
	public double fee(StopSign from, StopSign to) {
		return 8.0; // 8 baht throughout all stops
	}
	
}
