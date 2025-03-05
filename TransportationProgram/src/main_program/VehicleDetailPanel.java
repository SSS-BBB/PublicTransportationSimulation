package main_program;

import java.awt.event.*;

import javax.swing.*;
import vehicles.*;
import data_mangement.*;

public class VehicleDetailPanel extends JPanel implements ActionListener {
	
	private VehicleDetailFrame vehicleFrame;
	private Vehicle selectedVehicle;
	private MapData map;
	JLabel namelb, licenselb, showRoutelb, showVehiclelb;
	JButton showRouteToggleBtn, backBtn;
	
	public VehicleDetailPanel(VehicleDetailFrame vehicleFrame, MapData map, Vehicle selectedVehicle) {
		// Attributes setting
		this.vehicleFrame = vehicleFrame;
		this.selectedVehicle = selectedVehicle;
		this.map = map;
		
		// Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Components
		namelb = new JLabel(selectedVehicle.getVehicleName());
		add(namelb);
		
		licenselb = new JLabel(selectedVehicle.getVehicleLicence());
		add(licenselb);
		
		showRoutelb = new JLabel(String.format("Show route of this vehicle on the map: %s", 
										selectedVehicle.isShowRoute() ? "Yes": "No"));
		add(showRoutelb);
		
		// TODO: Add show vehicle boolean in vehicle class
		showVehiclelb = new JLabel(String.format("Show this vehicle on the map: %s", "Yes"));
		add(showVehiclelb);
		
		// Toggle Buttons
		showRouteToggleBtn = new JButton("Toggle Show Route");
		showRouteToggleBtn.addActionListener(this);
		add(showRouteToggleBtn);
		
		// TODO: Add show vehicle toggle button
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		add(backBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == showRouteToggleBtn) {
			selectedVehicle = map.toggleVehicleShowRouteStatus(selectedVehicle.getVehicleLicence());
			showRoutelb.setText(String.format("Show route of this vehicle on the map: %s", 
								selectedVehicle.isShowRoute() ? "Yes": "No"));
		}
		else if (e.getSource() == backBtn) {
			vehicleFrame.goBack();
		}
		
	}
	
}
