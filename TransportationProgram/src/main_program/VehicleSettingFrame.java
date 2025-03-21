package main_program;

import javax.swing.*;

import maps.MapData;
import stops.StopSign;
import vehicles.*;

public class VehicleSettingFrame extends JFrame {
	
	private MapData map;
	private Vehicle[] vehicleList;
	private StopSign selectedStop;
	private Vehicle selectedVehicle;
	
	public VehicleSettingFrame(MapData map, Vehicle[] vehicleList, StopSign selectedStop, 
							Vehicle selectedVehicle) {
		// Attributes setting
		this.map = map;
		this.vehicleList = vehicleList;
		this.selectedStop = selectedStop;
		this.selectedVehicle = selectedVehicle;
		
		// Frame default setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 800);
		setLocationRelativeTo(null);
		setTitle(selectedVehicle.getVehicleName());
		
		// Set Panel
		VehicleSettingPanel vPanel = new VehicleSettingPanel(this, map, selectedVehicle, selectedStop);
		add(vPanel);
		
		// Show Frame
		setVisible(true);
	}
	
	public void goBack() {
		new BusStopDetailFrame(map, vehicleList, selectedStop);
		dispose();
	}
	
	public MapData getMap() {
		return map;
	}
	
	public Vehicle[] getVehicleList() {
		return vehicleList;
	}
	
	public StopSign getSelectedStop() {
		return selectedStop;
	}
	
	public Vehicle getSelectedVehicle() {
		return selectedVehicle;
	}
	
}
