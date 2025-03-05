package main_program;

import java.awt.Color;
import java.awt.Graphics;
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
		add(backBtn);
		
		// Stop Sign Detail
		JLabel stopIdlb = new JLabel(selectedStop.getStopID());
		add(stopIdlb);
		
		JLabel stopPoslb = new JLabel(String.format("Bus Stop Position: (%.2f, %.2f)", selectedStop.getPosition()[0], selectedStop.getPosition()[1]));
		add(stopPoslb);
		
		// Vehicle panels
		vehicleContainer = new JPanel[vehicleList.length];
		
		namelb = new JLabel[vehicleList.length];
		licenselb = new JLabel[vehicleList.length];
		vehicleStoplb = new JLabel[vehicleList.length];
		directionlb = new JLabel[vehicleList.length];
		poslb = new JLabel[vehicleList.length];
		distlb = new JLabel[vehicleList.length];
		etalb = new JLabel[vehicleList.length];
		departorarrivelb = new JLabel[vehicleList.length];
		for (int i = 0; i < vehicleList.length; i++) {
			vehicleContainer[i] = createVehiclePanel(vehicleList[i], i);
			add(vehicleContainer[i]);
		}
		
	}
	
	public JPanel createVehiclePanel(Vehicle vehicle, int i) {
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
		
		// Border
		vehiclePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		// Components
		namelb[i] = new JLabel(vehicle.getVehicleName());
		vehiclePanel.add(namelb[i]);
		
		licenselb[i] = new JLabel(vehicle.getVehicleLicence());
		vehiclePanel.add(licenselb[i]);
		
		vehicleStoplb[i] = new JLabel(String.format("Vehicle is at %s", vehicle.getCurrentStop().getStopName()));
		vehiclePanel.add(vehicleStoplb[i]);
		
		directionlb[i] = new JLabel(String.format("Vehicle is heading %s", vehicle.vehicleDirection()));
		vehiclePanel.add(directionlb[i]);
		
		departorarrivelb[i] = new JLabel(String.format("Vehicle is %s", vehicle.departOrArrive(selectedStop)));
		vehiclePanel.add(departorarrivelb[i]);
		
		poslb[i] = new JLabel(String.format("Vehicle Positions: (%.2f, %.2f)", vehicle.getVehiclePostion()[0], vehicle.getVehiclePostion()[1]));
		vehiclePanel.add(poslb[i]);
		
		distlb[i] = new JLabel(String.format("Distance: %.2f", vehicle.distanceToTargetStop(selectedStop)));
		vehiclePanel.add(distlb[i]);
		
		etalb[i] = new JLabel(String.format("ETA: %.2f", vehicle.getEstimatedTime(selectedStop)));
		vehiclePanel.add(etalb[i]);
		
		System.out.println("Label Created");
		
		return vehiclePanel;
	}
}
