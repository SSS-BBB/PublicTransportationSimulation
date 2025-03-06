package main_program;

import java.awt.Dimension;
import java.awt.Font;
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
		
		// Back Button
		JPanel backBtnContainer = new JPanel();
		backBtnContainer.setLayout(new BoxLayout(backBtnContainer, BoxLayout.X_AXIS));
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		backBtnContainer.add(backBtn);
		backBtnContainer.add(Box.createHorizontalGlue());
		add(backBtnContainer);
		add(Box.createRigidArea(new Dimension(0, 50)));
		
		// Vehicle Setting Details Container
		JPanel xBoxContainer = new JPanel();
		xBoxContainer.setLayout(new BoxLayout(xBoxContainer, BoxLayout.X_AXIS));
		JPanel vehicleSettingContainer = new JPanel();
		vehicleSettingContainer.setLayout(new BoxLayout(vehicleSettingContainer, BoxLayout.Y_AXIS));
		
		// Margin
		int margin = 15; // margin between components within the vehicle setting container
		
		// Fonts
		Font topicFont = new Font("Tahoma", Font.BOLD, 42); // font for vehicle name
		Font subHeadingFont = new Font("Tahoma", Font.PLAIN, 32); // font for license
		Font detailFont = new Font("Tahoma", Font.PLAIN, 18); // font for vehicle detail
		
		namelb = new JLabel(selectedVehicle.getVehicleName());
		namelb.setAlignmentX(CENTER_ALIGNMENT);
		namelb.setFont(topicFont);
		vehicleSettingContainer.add(namelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		licenselb = new JLabel(selectedVehicle.getVehicleLicence());
		licenselb.setAlignmentX(CENTER_ALIGNMENT);
		licenselb.setFont(subHeadingFont);
		vehicleSettingContainer.add(licenselb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		showRoutelb = new JLabel(String.format("Show route of this vehicle on the map: %s", 
										selectedVehicle.doShowRoute() ? "Yes": "No"));
		showRoutelb.setAlignmentX(CENTER_ALIGNMENT);
		showRoutelb.setFont(detailFont);
		vehicleSettingContainer.add(showRoutelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		showVehiclelb = new JLabel(String.format("Show this vehicle on the map: %s", 
										selectedVehicle.doShowVehicle() ? "Yes": "No"));
		showVehiclelb.setAlignmentX(CENTER_ALIGNMENT);
		showVehiclelb.setFont(detailFont);
		vehicleSettingContainer.add(showVehiclelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		// Toggle Buttons
		showRouteToggleBtn = new JButton(String.format("%s show route", 
												selectedVehicle.doShowRoute() ? "Don't" : "Do"));
		showRouteToggleBtn.addActionListener(this);
		showRouteToggleBtn.setAlignmentX(CENTER_ALIGNMENT);
		vehicleSettingContainer.add(showRouteToggleBtn);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		showVehicleToggleBtn = new JButton(String.format("%s show vehicle", 
				selectedVehicle.doShowVehicle() ? "Don't" : "Do"));
		showVehicleToggleBtn.addActionListener(this);
		showVehicleToggleBtn.setAlignmentX(CENTER_ALIGNMENT);
		vehicleSettingContainer.add(showVehicleToggleBtn);
		
		xBoxContainer.add(vehicleSettingContainer);
		
		add(xBoxContainer);
		
		// TODO: add (vehicle name) button, when clicked go back to map and only show vehicle with this name(สายรถ)
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
