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
import program_designs.*;
import vehicles.*;
import stops.*;

public class VehicleSettingPanel extends JPanel implements ActionListener {
	
	private VehicleSettingFrame vehicleFrame;
	
	private Vehicle selectedVehicle;
	private StopSign selectedStop;
	private MapData map;
	
	private Color bgColor, topicColor, subHeadingColor, detailColor;
	
	private JLabel namelb, licenselb, showRoutelb, showVehiclelb, feelb;
	private JButton showRouteToggleBtn, showVehicleToggleBtn, backBtn, calculateFeeBtn, vehicleBtn; // TODO: decorate button
	private JComboBox<String> fromCombo, toCombo;
	
	private boolean smartBusMember;
	
	public VehicleSettingPanel(VehicleSettingFrame vehicleFrame, MapData map, Vehicle selectedVehicle, 
			StopSign selectedStop) {
		// Attributes setting
		this.vehicleFrame = vehicleFrame;
		this.selectedVehicle = selectedVehicle;
		this.map = map;
		this.selectedStop = selectedStop;
		
		if (selectedVehicle instanceof SmartBus) {
			// Member of smart bus check
			String[] options = {"Yes", "No"};
			int selectedOption = JOptionPane.showOptionDialog(null, "Are you a member of Smart Bus?",
					"Smart Bus Member", 0, 2, null, options, options[0]);
			if (selectedOption == 0) {
				this.smartBusMember = true;
			}
			else {
				this.smartBusMember = false;
			}
		}
		
		// Background
		setBackground(ProgramColor.DARK_BLUE);
		
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
		backBtnContainer.setBackground(ProgramColor.DARK_BLUE);
		add(backBtnContainer);
		add(Box.createRigidArea(new Dimension(0, 50)));
		
		// Colors
		bgColor = ProgramColor.BLUE;
		topicColor = Color.white;
		subHeadingColor = Color.white;
		detailColor = ProgramColor.LIGHT_GREEN;
		
		// Vehicle Setting Details Container
		JPanel xBoxContainer = new JPanel();
		xBoxContainer.setLayout(new BoxLayout(xBoxContainer, BoxLayout.X_AXIS));
		xBoxContainer.setBorder(BorderFactory.createLineBorder(ProgramColor.LIGHT_BLUE, 2));
		xBoxContainer.setBackground(bgColor);
		
		JPanel vehicleSettingContainer = new JPanel();
		vehicleSettingContainer.setLayout(new BoxLayout(vehicleSettingContainer, BoxLayout.Y_AXIS));
		vehicleSettingContainer.setBackground(bgColor);
		
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, 20))); // margin top
		
		// Margin
		int margin = 15; // margin between components within the vehicle setting container
		int spacing = 30; // space to separate group of components
		
		// Fonts
		Font topicFont = new Font("Tahoma", Font.BOLD, 36); // font for vehicle name
		Font subHeadingFont = new Font("Tahoma", Font.PLAIN, 24); // font for license
		Font detailFont = new Font("Tahoma", Font.PLAIN, 14); // font for vehicle detail
		
		namelb = new JLabel(selectedVehicle.getVehicleName());
		namelb.setAlignmentX(CENTER_ALIGNMENT);
		namelb.setFont(topicFont);
		namelb.setForeground(topicColor);
		vehicleSettingContainer.add(namelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		// Vehicle image
		JPanel imageContainer = new JPanel();
		imageContainer.setLayout(new BoxLayout(imageContainer, BoxLayout.X_AXIS));
		imageContainer.setBackground(bgColor);
		JLabel vehicleImagelb = new JLabel();
		ImageIcon vehicleIcon = new ImageIcon(getClass().getResource(selectedVehicle.getImagePath()));
		if (vehicleIcon != null) {
			Image vehicleImg = vehicleIcon.getImage().getScaledInstance(
					selectedVehicle.getIconWidth() + 15, selectedVehicle.getIconHeight() + 15, 
					Image.SCALE_DEFAULT);
			vehicleImagelb.setIcon(new ImageIcon(vehicleImg));
			imageContainer.add(vehicleImagelb);
			vehicleSettingContainer.add(imageContainer); // for some reason you need jpanel to center the icon
			vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		}
		
		licenselb = new JLabel(selectedVehicle.getVehicleLicence());
		licenselb.setAlignmentX(CENTER_ALIGNMENT);
		licenselb.setFont(subHeadingFont);
		licenselb.setForeground(subHeadingColor);
		vehicleSettingContainer.add(licenselb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		JLabel selectlb = new JLabel("Please select your destination to calculate fee");
		selectlb.setAlignmentX(CENTER_ALIGNMENT);
		selectlb.setFont(detailFont);
		selectlb.setForeground(detailColor);
		vehicleSettingContainer.add(selectlb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		JPanel comboBoxContainer = new JPanel();
		comboBoxContainer.setLayout(new BoxLayout(comboBoxContainer, BoxLayout.X_AXIS));
		comboBoxContainer.setBackground(bgColor);
		String[] stopList = getStopNameList();
		/* fromCombo = new JComboBox<>(stopList);
		fromCombo.setPreferredSize(new Dimension(150, 40));
		fromCombo.setMaximumSize(fromCombo.getPreferredSize());
		comboBoxContainer.add(fromCombo); */
		toCombo = new JComboBox<>(stopList);
		toCombo.setPreferredSize(new Dimension(150, 30));
		toCombo.setMaximumSize(toCombo.getPreferredSize());
		toCombo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		toCombo.setBackground(ProgramColor.DROPDOWN_BACKGROUND);
		toCombo.setForeground(Color.black);
		toCombo.setBorder(BorderFactory.createEmptyBorder());
		toCombo.setFont(detailFont);
		toCombo.setFocusable(false);
		comboBoxContainer.add(toCombo);
		comboBoxContainer.add(Box.createRigidArea(new Dimension(margin, 0)));
		calculateFeeBtn = new JButton("Calculate fee");
		calculateFeeBtn.addActionListener(this);
		ComponentDesign.buttonDesign(calculateFeeBtn, 118, 30);
		comboBoxContainer.add(calculateFeeBtn);
		vehicleSettingContainer.add(comboBoxContainer);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		StopSign destinationStop = selectedVehicle.getStopFromName(toCombo.getSelectedItem().toString());
		if (destinationStop != null) {
			feelb = new JLabel(String.format("Fee: %.2f", getFee(selectedStop, destinationStop)));
			feelb.setForeground(detailColor);
		}
		else {
			feelb = new JLabel("Fee: cannot calculate fee!");
			feelb.setForeground(Color.red);
		}
		feelb.setAlignmentX(CENTER_ALIGNMENT);
		feelb.setFont(detailFont);
		vehicleSettingContainer.add(feelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		boolean space = false;
		if (selectedVehicle instanceof AirConditionedVehicle) {
			// Air-Conditioned Bus components
			AirConditionedVehicle airBus = (AirConditionedVehicle) selectedVehicle;
			JLabel templb = new JLabel(String.format("Temperature: %.2f°C", airBus.getTemperature()));
			templb.setFont(detailFont);
			templb.setAlignmentX(CENTER_ALIGNMENT);
			templb.setForeground(detailColor);
			vehicleSettingContainer.add(templb);
			vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
			space = true;
		}
		if (selectedVehicle instanceof SmartBus && smartBusMember) {
			// Air-Conditioned Bus components
			SmartBus smartBus = (SmartBus) selectedVehicle;
			
			JLabel peoplelb = new JLabel(String.format("People on the bus: %d", smartBus.getNumberOfPeopleOnBus()));
			peoplelb.setFont(detailFont);
			peoplelb.setAlignmentX(CENTER_ALIGNMENT);
			peoplelb.setForeground(detailColor);
			vehicleSettingContainer.add(peoplelb);
			vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
			
			JLabel seatlb = new JLabel("Seat Left: " + smartBus.getSeatLeft());
			seatlb.setFont(detailFont);
			seatlb.setForeground(detailColor);
			seatlb.setAlignmentX(CENTER_ALIGNMENT);
			vehicleSettingContainer.add(seatlb);
			vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
			space = true;
		}
		if (space)
			vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing-margin))); // do space if the vehicle is not normal bus
		
		showRoutelb = new JLabel(String.format("Show route of this vehicle on the map: %s", 
										selectedVehicle.doShowRoute() ? "Yes": "No"));
		showRoutelb.setAlignmentX(CENTER_ALIGNMENT);
		showRoutelb.setFont(detailFont);
		showRoutelb.setForeground(detailColor);
		vehicleSettingContainer.add(showRoutelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		showVehiclelb = new JLabel(String.format("Show this vehicle on the map: %s", 
										selectedVehicle.doShowVehicle() ? "Yes": "No"));
		showVehiclelb.setAlignmentX(CENTER_ALIGNMENT);
		showVehiclelb.setFont(detailFont);
		showVehiclelb.setForeground(detailColor);
		vehicleSettingContainer.add(showVehiclelb);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		// Toggle Buttons
		showRouteToggleBtn = new JButton(String.format("%s show route", 
												selectedVehicle.doShowRoute() ? "Don't" : "Do"));
		showRouteToggleBtn.addActionListener(this);
		showRouteToggleBtn.setAlignmentX(CENTER_ALIGNMENT);
		ComponentDesign.buttonDesign(showRouteToggleBtn, 140, 35);
		vehicleSettingContainer.add(showRouteToggleBtn);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, margin)));
		
		showVehicleToggleBtn = new JButton(String.format("%s show vehicle", 
				selectedVehicle.doShowVehicle() ? "Don't" : "Do"));
		showVehicleToggleBtn.addActionListener(this);
		showVehicleToggleBtn.setAlignmentX(CENTER_ALIGNMENT);
		ComponentDesign.buttonDesign(showVehicleToggleBtn, 150, 35);
		vehicleSettingContainer.add(showVehicleToggleBtn);
		vehicleSettingContainer.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		vehicleBtn = new JButton(selectedVehicle.getVehicleName());
		vehicleBtn.addActionListener(this);
		vehicleBtn.setAlignmentX(CENTER_ALIGNMENT);
		ComponentDesign.buttonDesign(vehicleBtn, 100, 35);
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
				feelb.setText(String.format("Fee: %.2f", getFee(selectedStop, destinationStop)));
				feelb.setForeground(detailColor);
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
	
	private double getFee(StopSign from, StopSign to) {
		
		if (selectedVehicle instanceof SmartBus) {
			SmartBus smartBus = (SmartBus) selectedVehicle;
			// check member
			if (smartBusMember) {
				return smartBus.subsriptionFee(from, to);
			}
		}
		
		return selectedVehicle.fee(from, to);
		
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
