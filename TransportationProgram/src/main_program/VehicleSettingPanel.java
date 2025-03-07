package main_program;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import maps.*;
import vehicles.*;
import stops.*;

public class VehicleSettingPanel extends JPanel implements ActionListener {
	
	private VehicleSettingFrame vehicleFrame;
	private Vehicle selectedVehicle;
	private StopSign selectedStop;
	private MapData map;
	JLabel namelb, licenselb, showRoutelb, showVehiclelb, feelb;
	JButton showRouteToggleBtn, showVehicleToggleBtn, backBtn, calculateFeeBtn, vehicleBtn; // TODO: decorate button
	JComboBox fromCombo, toCombo;
	
	public VehicleSettingPanel(VehicleSettingFrame vehicleFrame, MapData map, Vehicle selectedVehicle, 
			StopSign selectedStop) {
		// Attributes setting
		this.vehicleFrame = vehicleFrame;
		this.selectedVehicle = selectedVehicle;
		this.map = map;
		this.selectedStop = selectedStop;
		
		// Background
		// repaint();
		setBackground(Color.white);
		
		// Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Back Button
		JPanel backBtnContainer = new JPanel();
		backBtnContainer.setLayout(new BoxLayout(backBtnContainer, BoxLayout.X_AXIS));
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		ImageIcon backIcon = new ImageIcon(getClass().getResource("/icons/backArrowIcon.png"));
		Image backImg = backIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		if (backIcon != null) {
			backBtn.setIcon(new ImageIcon(backImg));
			backBtn.setText("");
			backBtn.setBackground(null);
			backBtn.setBorder(null);
			backBtn.setContentAreaFilled(false);
		}
		backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backBtnContainer.add(backBtn);
		backBtnContainer.add(Box.createHorizontalGlue());
		add(backBtnContainer);
		add(Box.createRigidArea(new Dimension(0, 50)));
		
		// Vehicle Setting Details Container
		JPanel xBoxContainer = new JPanel();
		xBoxContainer.setLayout(new BoxLayout(xBoxContainer, BoxLayout.X_AXIS));
		xBoxContainer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JPanel vehicleSettingContainer = new JPanel();
		vehicleSettingContainer.setLayout(new BoxLayout(vehicleSettingContainer, BoxLayout.Y_AXIS));
		
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, 20))); // margin top
		
		// Margin
		int margin = 15; // margin between components within the vehicle setting container
		int spacing = 50; // space to separate group of components
		
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
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		JLabel selectlb = new JLabel("Please select your destination to calculate fee");
		selectlb.setAlignmentX(CENTER_ALIGNMENT);
		selectlb.setFont(detailFont);
		vehicleSettingContainer.add(selectlb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		JPanel comboBoxContainer = new JPanel();
		comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.X_AXIS));
		String[] stopList = getStopNameList();
		/* fromCombo = new JComboBox<>(stopList);
		fromCombo.setPreferredSize(new Dimension(150, 40));
		fromCombo.setMaximumSize(fromCombo.getPreferredSize());
		comboBoxContainer.add(fromCombo); */
		toCombo = new JComboBox<>(stopList);
		toCombo.setPreferredSize(new Dimension(150, 30));
		toCombo.setMaximumSize(toCombo.getPreferredSize());
		comboBoxContainer.add(toCombo);
		comboBoxContainer.add(Box.createRigidArea(new Dimension(margin, 0)));
		calculateFeeBtn = new JButton("Calculate fee");
		calculateFeeBtn.addActionListener(this);
		comboBoxContainer.add(calculateFeeBtn);
		vehicleSettingContainer.add(comboBoxContainer);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		StopSign destinationStop = selectedVehicle.getStopFromName(toCombo.getSelectedItem().toString());
		if (destinationStop != null) {
			feelb = new JLabel(String.format("Fee: %.2f", selectedVehicle.fee(selectedStop, destinationStop)));
			feelb.setForeground(Color.black);
		}
		else {
			feelb = new JLabel("Fee: cannot calculate fee!");
			feelb.setForeground(Color.red);
		}
		feelb.setAlignmentX(CENTER_ALIGNMENT);
		feelb.setFont(detailFont);
		vehicleSettingContainer.add(feelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing)));
		
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
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		vehicleBtn = new JButton(selectedVehicle.getVehicleName());
		vehicleBtn.addActionListener(this);
		vehicleBtn.setAlignmentX(CENTER_ALIGNMENT);
		vehicleSettingContainer.add(vehicleBtn);
		
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, 20))); // margin bottom
		
		xBoxContainer.add(Box.createRigidArea(new Dimension(20, 0))); // margin left
		xBoxContainer.add(vehicleSettingContainer); // content
		xBoxContainer.add(Box.createRigidArea(new Dimension(20, 0))); // margin right
		
		add(xBoxContainer);
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
		
		else if (e.getSource() == calculateFeeBtn) {
			StopSign destinationStop = selectedVehicle.getStopFromName(toCombo.getSelectedItem().toString());
			if (destinationStop != null) {
				feelb.setText(String.format("Fee: %.2f", selectedVehicle.fee(selectedStop, destinationStop)));
				feelb.setForeground(Color.black);
			}
			else {
				feelb.setText("Fee: cannot calculate fee!");
				feelb.setForeground(Color.red);
			}
		}
		
		else if (e.getSource() == vehicleBtn) {
			map.showOnly(selectedVehicle);
			new MapFrame(map);
			vehicleFrame.dispose();
		}
		
	}
	
	private String[] getStopNameList() {
		StopSign[] vehicleStops = selectedVehicle.getVehicleStops();
		String[] stopNameList = new String[vehicleStops.length];
		for (int i = 0; i < vehicleStops.length; i++) {
			stopNameList[i] = vehicleStops[i].getStopName();
		}
		return stopNameList;
	}
	
	/* 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage bg = null;
		try {
			bg = ImageIO.read(getClass().getResource("/backgrounds/chillguy.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (bg != null) {
			g.drawImage(bg, 0, 0, vehicleFrame.getWidth(), vehicleFrame.getHeight(), null);
		}
		else {
			vehicleFrame.setBackground(Color.white);
		}
	}
	*/
	
}
