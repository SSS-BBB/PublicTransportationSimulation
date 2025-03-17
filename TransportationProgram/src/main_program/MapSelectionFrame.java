package main_program;

import javax.swing.*;

public class MapSelectionFrame extends JFrame {
	
	public static void main(String[] args) {
		new MapSelectionFrame();
	}
	
	public MapSelectionFrame() {
		// Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Public Transportaion Simulation");
		
		// Set panel
		JPanel selectionPanel = new MapSelectionPanel(this);
		add(selectionPanel);
		
		// Show frame
		setVisible(true);
	}
	
}