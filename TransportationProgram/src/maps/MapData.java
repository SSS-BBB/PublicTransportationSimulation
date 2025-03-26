package maps;

import java.util.ArrayList;

import stops.StopSign;
import vehicles.*;

public class MapData {
	
	protected String mapName;
	protected int mapScale;
	
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
			vehicle.update(0.01);
			// vehicle.update(0.25);
		}
	}
	
	public StopSign[] getAllStopSigns() {
		return stops;
	}
	
	public Vehicle[] getAllVehicles() {
		return vehicles;
	}
	
	public String getName() {
		return mapName;
	}
	
	public int getMapScale() {
		return mapScale;
	}
	
	public StopSign[] getStopsFromIndex(int[] indexes) {
		StopSign[] stopList = new StopSign[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			int index = indexes[i];
			if (index >= stops.length) {
				System.out.println(index + " is out of bounds");
			}
			else {
				stopList[i] = stops[index];
			}
		}
		
		return stopList;
	}
	
	public void setStopSign(StopSign[][] allStops) {
		// combine all stops sign from each list
		ArrayList<StopSign> mapStopList = new ArrayList<>();
		ArrayList<String> stopsId = new ArrayList<>();
		for (StopSign[] vehicleStops : allStops) {
			for (StopSign stop : vehicleStops) {
				if (!stopsId.contains(stop.getStopID())) {
					// this stop is not already on the list
					mapStopList.add(stop);
					stopsId.add(stop.getStopID());
				}
			}
		}
		
		stops = new StopSign[mapStopList.size()];
		for (int i = 0; i < stops.length; i++) {
			stops[i] = mapStopList.get(i);
		}
	}
	
	// scale map position from pixel unit to km unit
	public void scalePixelToKm(double[] scale) {
		for (StopSign stop : stops) {
			double[] scaledPosition = stop.getPosition();
			scaledPosition[0] /= scale[0];
			scaledPosition[1] /= scale[1];
			stop.setPosition(scaledPosition);
		}
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
				vehicle.updateShowRouteStatus(!vehicle.doShowRoute()); // toggle true -> false, false -> true
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
			vehicle.updateShowRouteStatus(true);
		}
	}
	
}