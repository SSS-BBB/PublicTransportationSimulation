package main_program;

import javax.swing.*;

import maps.*;

public class MapFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MapFrame(MapData map) {
		
		// Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle(map.getName());
		
		
		// Set panel
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		
		MapPanel mapPanel = new MapPanel(map, this, map.getMapScale());
		mapPanel.setLayout(null);
		containerPanel.add(mapPanel);
		
		add(containerPanel);
		
		setVisible(true);
		
		mapPanel.startLoop();
	}
	

}
