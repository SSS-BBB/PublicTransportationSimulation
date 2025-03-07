package main_program;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import maps.*;
import vehicles.*;
import stops.*;

public class BusStopDetailFrame extends JFrame {
	private MapData map;
	private Vehicle[] vehicleList;
	private StopSign selectedStop;
	
	public BusStopDetailFrame(MapData map, Vehicle[] vehicleList, StopSign selectedStop) {
		// Value settings
		this.map = map;
		this.vehicleList = vehicleList;
		this.selectedStop = selectedStop;
		
		// Frame default settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 800);
		setLocationRelativeTo(null);
		
		// Set Panel
		BusStopDetailPanel busStopPanel = new BusStopDetailPanel(this, map, vehicleList, selectedStop);
		add(busStopPanel);
		
		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane(busStopPanel);
		busStopPanel.setAutoscrolls(true);
		add(scrollPane);
		
		// Show Frame
		setVisible(true);
	}
	
}
