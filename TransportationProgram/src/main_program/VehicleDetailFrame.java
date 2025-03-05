package main_program;

import javax.swing.*;

import data_mangement.MapData;
import stops.StopSign;
import vehicles.*;

public class VehicleDetailFrame extends JFrame {
	
	private MapData map;
	private Vehicle[] vehicleList;
	private StopSign selectedStop;
	private Vehicle selectedVehicle;
	
	public VehicleDetailFrame(MapData map, Vehicle[] vehicleList, StopSign selectedStop, 
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
		
		// Set Panel
		VehicleDetailPanel vPanel = new VehicleDetailPanel(this, map, selectedVehicle);
		add(vPanel);
		
		// Show Frame
		setVisible(true);
	}
	
	public void goBack() {
		new BusStopDetailFrame(map, vehicleList, selectedStop);
		dispose();
	}
	
}
