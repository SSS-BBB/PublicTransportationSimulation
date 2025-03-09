package vehicles;

import stops.StopSign;

public abstract class AirConditionedVehicle extends Vehicle {
	
	private double temperature;
	
	public AirConditionedVehicle(String vehicleName, String vehicleLicence, StopSign[] stops, double waitInterval,
			double averageSpeed, double temperature, String imagePath) {
		super(vehicleName, vehicleLicence, stops, waitInterval, averageSpeed, imagePath);
		this.temperature = temperature;
		
	}
	
	public double getTemperature() {
		return temperature;
	}

}
