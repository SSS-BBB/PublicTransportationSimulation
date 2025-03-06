package main_program;

import java.awt.event.*;

import javax.swing.*;
import vehicles.*;
import data_mangement.*;

public class VehicleSettingPanel extends JPanel implements ActionListener {
	
	private VehicleSettingFrame vehicleFrame;
	private Vehicle selectedVehicle;
	private MapData map;
	JLabel namelb, licenselb, showRoutelb, showVehiclelb;
	JButton showRouteToggleBtn, showVehicleToggleBtn, backBtn;
	
	public VehicleSettingPanel(VehicleSettingFrame vehicleFrame, MapData map, Vehicle selectedVehicle) {
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
										selectedVehicle.doShowRoute() ? "Yes": "No"));
		add(showRoutelb);
		
		showVehiclelb = new JLabel(String.format("Show this vehicle on the map: %s", 
										selectedVehicle.doShowVehicle() ? "Yes": "No"));
		add(showVehiclelb);
		
		// Toggle Buttons
		showRouteToggleBtn = new JButton(String.format("%s show route", 
												selectedVehicle.doShowRoute() ? "Don't" : "Do"));
		showRouteToggleBtn.addActionListener(this);
		add(showRouteToggleBtn);
		
		showVehicleToggleBtn = new JButton(String.format("%s show vehicle", 
				selectedVehicle.doShowVehicle() ? "Don't" : "Do"));
		showVehicleToggleBtn.addActionListener(this);
		add(showVehicleToggleBtn);
		
		// TODO: add show only this vehicle when clicked go back to map and only show selected vehicle
		
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		add(backBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == showRouteToggleBtn) {
			selectedVehicle = map.toggleVehicleShowRouteStatus(selectedVehicle.getVehicleLicence());
			
			if (selectedVehicle == null)
			{
				// TODO: Show Error Dialog That says something went wrong!
				System.out.println("Cannot find " + selectedVehicle.getVehicleLicence());
				return;
			}
			
			// set label text
			showRoutelb.setText(String.format("Show route of this vehicle on the map: %s", 
								selectedVehicle.doShowRoute() ? "Yes": "No"));
			
			// set button text
			showRouteToggleBtn.setText(String.format("%s show route", 
												selectedVehicle.doShowRoute() ? "Don't" : "Do"));
		}
		
		else if (e.getSource() == showVehicleToggleBtn) {
			selectedVehicle = map.toggleShowVehicleStatus(selectedVehicle.getVehicleLicence());
			
			if (selectedVehicle == null)
			{
				// TODO: Show Error Dialog That says something went wrong!
				System.out.println("Cannot find " + selectedVehicle.getVehicleLicence());
				return;
			}
				
			// set label text
			showVehiclelb.setText(String.format("Show this vehicle on the map: %s", 
								selectedVehicle.doShowVehicle() ? "Yes": "No"));
			
			// set button text
			showVehicleToggleBtn.setText(String.format("%s show vehicle", 
				selectedVehicle.doShowVehicle() ? "Don't" : "Do"));
		}
		
		else if (e.getSource() == backBtn) {
			vehicleFrame.goBack();
		}
		
	}
	
}
