package data_mangement;

import stops.StopSign;
import vehicles.NormalBus;
import vehicles.Vehicle;

public class MapData {
	
	protected StopSign[] stops;
	protected Vehicle[] vehicles;
	
	public void updateData() {
		for (Vehicle vehicle : vehicles) {
			vehicle.update(1);
		}
	}
	
	public StopSign[] getAllStopSigns() {
		return stops;
	}
	
	public Vehicle[] getAllVehicles() {
		return vehicles;
	}
	
	public Vehicle toggleVehicleShowRouteStatus(String vehicleLicense) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVehicleLicence().equals(vehicleLicense)) {
				vehicle.updateShowRouteStatus(!vehicle.doShowRoute()); // toggle true -> false, false -> true
				return vehicle;
			}
		}
		return null;
	}
	
	public Vehicle toggleShowVehicleStatus(String vehicleLicense) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVehicleLicence().equals(vehicleLicense)) {
				vehicle.updateShowVehicleStatus(!vehicle.doShowVehicle()); // toggle true -> false, false -> true
				return vehicle;
			}
		}
		return null;
	}
	
}