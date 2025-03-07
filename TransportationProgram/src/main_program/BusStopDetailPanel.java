package main_program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import maps.*;
import stops.StopSign;
import vehicles.Vehicle;

public class BusStopDetailPanel extends JPanel  {
	
	private BusStopDetailFrame detailFrame;
	
	private MapData map;
	private Vehicle[] vehicleList;
	private StopSign selectedStop;
	
	// Detail Labels
	JLabel[] namelb, licenselb, vehicleStoplb, directionlb, poslb, distlb, etalb, departorarrivelb;
	JPanel[] vehicleContainer;
	
	public BusStopDetailPanel(BusStopDetailFrame detailFrame, MapData map, Vehicle[] vehicleList, StopSign selectedStop) {
		
		// TODO: color this window
		// repaint();
		
		// Value Setting
		this.detailFrame = detailFrame;
		this.map = map;
		this.vehicleList = vehicleList;
		this.selectedStop = selectedStop;
		
		// Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
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
		
		add(backContainer);
		
		// Stop Sign Detail
		JLabel stopNamelb = new JLabel(selectedStop.getStopName() + " Stop");
		stopNamelb.setAlignmentX(CENTER_ALIGNMENT);
		stopNamelb.setFont(new Font("Tahoma", Font.BOLD, 42));
		add(stopNamelb);
		
		JLabel stopIdlb = new JLabel(selectedStop.getStopID());
		stopIdlb.setAlignmentX(CENTER_ALIGNMENT);
		stopIdlb.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
		// Vehicle Container Panel (BoxLayout(X_AXIS))
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
		// containerPanel.setBackground(Color.blue);
		
		// Border
		containerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// Detail Panel
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
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
		headingContainer.add(namelb[i]);
		headingContainer.add(Box.createRigidArea(new Dimension(120, 0)));
		
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
		vehiclePanel.add(routelb);
		
		licenselb[i] = new JLabel(vehicle.getVehicleLicence());
		licenselb[i].setFont(subHeadingFont);
		vehiclePanel.add(licenselb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		poslb[i] = new JLabel(String.format("Vehicle Positions: (%.2f, %.2f)", vehicle.getVehiclePostion()[0], vehicle.getVehiclePostion()[1]));
		poslb[i].setFont(detailFont);
		vehiclePanel.add(poslb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		vehicleStoplb[i] = new JLabel(String.format("Current stop %s", vehicle.getCurrentStop().getStopName()));
		vehicleStoplb[i].setFont(detailFont);
		vehiclePanel.add(vehicleStoplb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		JLabel nextStoplb = new JLabel(String.format("Next stop %s", vehicle.getNextStop().getStopName()));
		nextStoplb.setFont(detailFont);
		vehiclePanel.add(nextStoplb);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, spacing)));
		
		directionlb[i] = new JLabel(String.format("Vehicle is heading %s", vehicle.vehicleDirection()));
		directionlb[i].setFont(detailFont);
		vehiclePanel.add(directionlb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		departorarrivelb[i] = new JLabel(String.format("Vehicle is %s", vehicle.departOrArrive(selectedStop)));
		departorarrivelb[i].setFont(detailFont);
		vehiclePanel.add(departorarrivelb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		distlb[i] = new JLabel(String.format("Distance: %.2f", vehicle.distanceToTargetStop(selectedStop)));
		distlb[i].setFont(detailFont);
		vehiclePanel.add(distlb[i]);
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, margin)));
		
		etalb[i] = new JLabel(String.format("ETA: %.2f", vehicle.getEstimatedTime(selectedStop)));
		etalb[i].setFont(detailFont);
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
