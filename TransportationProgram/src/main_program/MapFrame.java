package main_program;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import maps.*;
import stops.*;
import vehicles.*;

public class MapFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Set map
					MapData testMap = new TestMap();
					
					// Set frame
					MapFrame frame = new MapFrame(testMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	private MapData map;
	
	public MapFrame(MapData map) {
		// Attribute setting
		this.map = map;
		
		// Frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		
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
