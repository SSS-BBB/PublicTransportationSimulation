package main_program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import maps.*;
import program_designs.*;
import stops.*;
import vehicles.*;

public class BusStopDetailPanel extends JPanel  {
	
	private BusStopDetailFrame detailFrame;
	
	private MapData map;
	private Vehicle[] vehicleList;
	private StopSign selectedStop;
	
	// Detail Labels
	JLabel[] namelb, licenselb, vehicleStoplb, directionlb, poslb, distlb, etalb, departorarrivelb;
	JPanel[] vehicleContainer;
	
	public BusStopDetailPanel(BusStopDetailFrame detailFrame, MapData map, Vehicle[] vehicleList, StopSign selectedStop) {
		// Value Setting
		this.detailFrame = detailFrame;
		this.map = map;
		this.vehicleList = vehicleList;
		this.selectedStop = selectedStop;
		
		// Panel setting
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(54, 116, 181));
		
		// Back Button
		JPanel backContainer = new JPanel();
		backContainer.setLayout(new BoxLayout(backContainer, BoxLayout.X_AXIS));
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == backBtn) {
					try {
						// Set frame
						new MapFrame(map);
						detailFrame.dispose();
						
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			
		});
		// set back button icon
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
		backContainer.add(backBtn);
		backContainer.add(Box.createHorizontalGlue());
		backContainer.setBackground(new Color(54, 116, 181));
		
		add(backContainer);
		add(Box.createRigidArea(new Dimension(0, 15)));
		
		// Stop Sign Detail
		JLabel stopNamelb = new JLabel(selectedStop.getStopName());
		stopNamelb.setAlignmentX(CENTER_ALIGNMENT);
		stopNamelb.setFont(new Font("Tahoma", Font.BOLD, 42));
		stopNamelb.setForeground(Color.white);
		add(stopNamelb);
		
		JLabel stopIdlb = new JLabel(selectedStop.getStopID());
		stopIdlb.setAlignmentX(CENTER_ALIGNMENT);
		stopIdlb.setFont(new Font("Tahoma", Font.PLAIN, 30));
		stopIdlb.setForeground(Color.white);
		add(stopIdlb);
		
		// For Debug Purpose
		JLabel stopPoslb = new JLabel(String.format("Bus Stop Position: (%.2f, %.2f)", selectedStop.getPosition()[0], selectedStop.getPosition()[1]));
		stopPoslb.setAlignmentX(CENTER_ALIGNMENT);
		add(stopPoslb);
		// TODO: Remove this before deploy
		
		add(Box.createRigidArea(new Dimension(0, 35)));
		
		// Vehicle panels
		vehicleContainer = new JPanel[vehicleList.length];
		
		// Set components
		namelb = new JLabel[vehicleList.length];
		licenselb = new JLabel[vehicleList.length];
		vehicleStoplb = new JLabel[vehicleList.length];
		directionlb = new JLabel[vehicleList.length];
		poslb = new JLabel[vehicleList.length];
		distlb = new JLabel[vehicleList.length];
		etalb = new JLabel[vehicleList.length];
		departorarrivelb = new JLabel[vehicleList.length];
		for (int i = 0; i < vehicleList.length; i++) {
			if (vehicleList[i].getStopIndex(selectedStop) == -1)
				continue; // This vehicle doesn't pass this stop
			
			vehicleContainer[i] = createVehiclePanel(vehicleList[i], i);
			vehicleContainer[i].setAlignmentX(CENTER_ALIGNMENT);
			add(vehicleContainer[i]);
			
			// Padding
	        add(Box.createRigidArea(new Dimension(0, 25)));
		}
	}
	
	public JPanel createVehiclePanel(Vehicle vehicle, int i) {
		// Color
		Color bgColor = ProgramColor.BLUE;
		Color topicColor = Color.white;
		Color subHeadingColor = Color.white;
		Color detailColor = ProgramColor.LIGHT_GREEN;
		
		// Vehicle Container Panel (BoxLayout(X_AXIS))
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
		containerPanel.setBackground(bgColor);
		
		// Border
		containerPanel.setBorder(BorderFactory.createLineBorder(ProgramColor.LIGHT_BLUE, 2));
		
		// Detail Panel
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
		vehiclePanel.setBackground(bgColor);
		// vehiclePanel.setBackground(Color.blue);
		
		// Decorations
		Font topicFont = new Font("Tahoma", Font.BOLD, 30); // font for vehicle name
		Font subHeadingFont = new Font("Tahoma", Font.PLAIN, 18); // font for license, route
		Font detailFont = new Font("Tahoma", Font.PLAIN, 14); // font for vehicle detail
		int margin = 5; // margin between components within the vehicle panel
		int spacing = 15; // space to separate between group
		
		// Padding within the panel
		containerPanel.add(Box.createRigidArea(new Dimension(15, 0))); // Left
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, 15))); // Top
		
		// Components
		JPanel headingContainer = new JPanel();
		headingContainer.setLayout(new BoxLayout(headingContainer, BoxLayout.X_AXIS));
		
		// Labels
		namelb[i] = new JLabel(vehicle.getVehicleName());
		namelb[i].setFont(topicFont);
		namelb[i].setForeground(topicColor);
		headingContainer.add(namelb[i]);
		headingContainer.add(Box.createRigidArea(new Dimension(200, 0)));
		headingContainer.setBackground(bgColor);
		
		// Vehicle image
		JLabel vehicleImagelb = new JLabel();
		ImageIcon vehicleIcon = new ImageIcon(getClass().getResource(vehicle.getImagePath()));
		if (vehicleIcon != null) {
			Image vehicleImg = vehicleIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			vehicleImagelb.setIcon(new ImageIcon(vehicleImg));
			
			headingContainer.add(vehicleImagelb);
			// vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		}
		headingContainer.setAlignmentX(LEFT_ALIGNMENT);
		vehiclePanel.add(headingContainer);
		
		JLabel routelb = new JLabel(String.format("%s -> %s", vehicle.getFrom().getStopName(), vehicle.getTo().getStopName()));
		routelb.setFont(subHeadingFont);
		routelb.setForeground(subHeadingColor);
		vehiclePanel.add(routelb);
		
		licenselb[i] = new JLabel(vehicle.getVehicleLicence());
		licenselb[i].setFont(subHeadingFont);
		licenselb[i].setForeground(detailColor);
		vehiclePanel.add(licenselb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		/*
		poslb[i] = new JLabel(String.format("Vehicle Positions: (%.2f, %.2f)", vehicle.getVehiclePostion()[0], vehicle.getVehiclePostion()[1]));
		poslb[i].setFont(detailFont);
		poslb[i].setForeground(detailColor);
		vehiclePanel.add(poslb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		*/
		
		vehicleStoplb[i] = new JLabel(String.format("Current stop: %s", vehicle.getCurrentStop().getStopName()));
		vehicleStoplb[i].setFont(detailFont);
		vehicleStoplb[i].setForeground(detailColor);
		vehiclePanel.add(vehicleStoplb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		JLabel nextStoplb = new JLabel(String.format("Next stop: %s", vehicle.getNextStop().getStopName()));
		nextStoplb.setFont(detailFont);
		nextStoplb.setForeground(detailColor);
		vehiclePanel.add(nextStoplb);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		directionlb[i] = new JLabel(String.format("%s is heading %s", vehicle.getVehicleName(), vehicle.vehicleDirection()));
		directionlb[i].setFont(detailFont);
		directionlb[i].setForeground(detailColor);
		vehiclePanel.add(directionlb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		departorarrivelb[i] = new JLabel(String.format("%s is %s", vehicle.getVehicleName(), vehicle.departOrArrive(selectedStop)));
		departorarrivelb[i].setFont(detailFont);
		departorarrivelb[i].setForeground(detailColor);
		vehiclePanel.add(departorarrivelb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		distlb[i] = new JLabel(String.format("Distance: %.2f km", vehicle.distanceToTargetStop(selectedStop)));
		distlb[i].setFont(detailFont);
		distlb[i].setForeground(detailColor);
		vehiclePanel.add(distlb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		etalb[i] = new JLabel(String.format("ETA: %.2f minutes", vehicle.getEstimatedTime(selectedStop)*60.0));
		etalb[i].setFont(detailFont);
		etalb[i].setForeground(detailColor);
		vehiclePanel.add(etalb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		// Vehicle setting button
		JButton settingBtn = new JButton("Setting");
		settingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			if (e.getSource() == settingBtn) {
				new VehicleSettingFrame(map, vehicleList, selectedStop, vehicle);
				detailFrame.dispose();
			}
		}
		});
		ImageIcon settingIcon = new ImageIcon(getClass().getResource("/icons/settingIcon.png"));
		if (settingIcon != null) {
			Image settingImg = settingIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			settingBtn.setIcon(new ImageIcon(settingImg));
			settingBtn.setText("");
			settingBtn.setBackground(null);
			settingBtn.setBorder(null);
			settingBtn.setContentAreaFilled(false);
			
		}
		settingBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		vehiclePanel.add(settingBtn);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		// Padding within the panel
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, 15))); // Bottom
		
		containerPanel.add(vehiclePanel);
		
		containerPanel.add(Box.createRigidArea(new Dimension(15, 0))); // Right
		
		return containerPanel;
	}
}
