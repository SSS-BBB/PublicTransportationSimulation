package data_mangement;

import stops.StopSign;
import vehicles.NormalBus;
import vehicles.Vehicle;

public class MapData {
	
	protected StopSign[] stops;
	protected Vehicle[] vehicles;
	
	public void updateData() {
		for (Vehicle vehicle : vehicles) {
			vehicle.update(0.1);
		}
	}
	
	public StopSign[] getAllStopSigns() {
		return stops;
	}
	
	public Vehicle[] getAllVehicles() {
		return vehicles;
	}
	
}