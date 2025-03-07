package maps;

import stops.StopSign;
import vehicles.NormalBus;
import vehicles.Vehicle;

public class MapData {
	
	protected StopSign[] stops;
	protected Vehicle[] vehicles;
	
	public MapData() {
		
	}
	
	public MapData(StopSign[] stops, Vehicle[] vehicles) {
		this.stops = stops;
		this.vehicles = vehicles;
	}
	
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
	
	public void showOnly(Vehicle selectedVehicle) {
		// show only specific vehicle on the map
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVehicleLicence().equals(selectedVehicle.getVehicleLicence())) {
				// show this vehicle on the map
				vehicle.updateShowVehicleStatus(true);
				vehicle.updateShowRouteStatus(true);
			}
			else {
				// don't show this vehicle on the map
				vehicle.updateShowVehicleStatus(false);
				vehicle.updateShowRouteStatus(false);
			}
		}
	}
	
	public void showAll() {
		// show all vehicle on the map
		for (Vehicle vehicle : vehicles) {
			vehicle.updateShowVehicleStatus(true);
			vehicle.updateShowRouteStatus(false);
		}
	}
	
}