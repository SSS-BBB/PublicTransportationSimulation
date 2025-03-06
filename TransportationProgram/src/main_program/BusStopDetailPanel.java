package main_program;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import stops.StopSign;
import vehicles.Vehicle;
import data_mangement.*;

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
		
		// Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Back Button
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == backBtn) {
					try {
						// Set frame
						MapFrame frame = new MapFrame();
						
						// Set panel
						MapPanel panel = new MapPanel(map, frame, 4);
						panel.setLayout(null);
						frame.add(panel);
						
						frame.setVisible(true);
						panel.startLoop();
						
						detailFrame.dispose();
						
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			
		});
		// TODO: Back Button To the top left corner
		backBtn.setAlignmentX(CENTER_ALIGNMENT);
		add(backBtn);
		
		// Stop Sign Detail
		JLabel stopIdlb = new JLabel(selectedStop.getStopID());
		stopIdlb.setAlignmentX(CENTER_ALIGNMENT);
		add(stopIdlb);
		
		JLabel stopPoslb = new JLabel(String.format("Bus Stop Position: (%.2f, %.2f)", selectedStop.getPosition()[0], selectedStop.getPosition()[1]));
		stopPoslb.setAlignmentX(CENTER_ALIGNMENT);
		add(stopPoslb);
		
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
	        add(Box.createRigidArea(new Dimension(0, 20)));
		}
	}
	
	public JPanel createVehiclePanel(Vehicle vehicle, int i) {
		// Vehicle Container Panel (BoxLayout(X_AXIS))
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
		
		// Border
		containerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// Detail Panel
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
		
		// Label Decoration
		Font topicFont = new Font("Tahoma", Font.BOLD, 20); // font for vehicle name
		Font detailFont = new Font("Tahoma", Font.PLAIN, 16); // font for vehicle detail
		
		// Padding within the panel
		containerPanel.add(Box.createRigidArea(new Dimension(15, 0))); // Left
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, 15))); // Top
		
		// Components
		
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
		// TODO: Setting Button To the top right corner
		// settingBtn.setAlignmentX(LEFT_ALIGNMENT);
		vehiclePanel.add(settingBtn);
		
		// Labels
		namelb[i] = new JLabel(vehicle.getVehicleName());
		namelb[i].setFont(topicFont);
		vehiclePanel.add(namelb[i]);
		
		licenselb[i] = new JLabel(vehicle.getVehicleLicence());
		licenselb[i].setFont(detailFont);
		vehiclePanel.add(licenselb[i]);
		
		vehicleStoplb[i] = new JLabel(String.format("Vehicle is at %s", vehicle.getCurrentStop().getStopName()));
		vehicleStoplb[i].setFont(detailFont);
		vehiclePanel.add(vehicleStoplb[i]);
		
		directionlb[i] = new JLabel(String.format("Vehicle is heading %s", vehicle.vehicleDirection()));
		directionlb[i].setFont(detailFont);
		vehiclePanel.add(directionlb[i]);
		
		departorarrivelb[i] = new JLabel(String.format("Vehicle is %s", vehicle.departOrArrive(selectedStop)));
		departorarrivelb[i].setFont(detailFont);
		vehiclePanel.add(departorarrivelb[i]);
		
		poslb[i] = new JLabel(String.format("Vehicle Positions: (%.2f, %.2f)", vehicle.getVehiclePostion()[0], vehicle.getVehiclePostion()[1]));
		poslb[i].setFont(detailFont);
		vehiclePanel.add(poslb[i]);
		
		distlb[i] = new JLabel(String.format("Distance: %.2f", vehicle.distanceToTargetStop(selectedStop)));
		distlb[i].setFont(detailFont);
		vehiclePanel.add(distlb[i]);
		
		etalb[i] = new JLabel(String.format("ETA: %.2f", vehicle.getEstimatedTime(selectedStop)));
		etalb[i].setFont(detailFont);
		vehiclePanel.add(etalb[i]);
		
		// Padding within the panel
		vehiclePanel.add(Box.createRigidArea(new Dimension(0, 15))); // Bottom
		
		containerPanel.add(vehiclePanel); // Center
		
		containerPanel.add(Box.createRigidArea(new Dimension(15, 0))); // Right
		
		System.out.println("Label Created"); // TODO: Remove
		
		return containerPanel;
	}
}
