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

import data_mangement.*;
import stops.*;
import vehicles.*;

public class MapFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Set frame
					MapFrame frame = new MapFrame();
					
					// Set map
					MapData testMap = new TestMap();
					
					// Set panel
					JPanel containerPanel = new JPanel();
					containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
					
					MapPanel mapPanel = new MapPanel(testMap, frame, 4);
					mapPanel.setLayout(null);
					containerPanel.add(mapPanel);
					
					frame.add(containerPanel);
					
					frame.setVisible(true);
					
					mapPanel.startLoop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MapFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
	}
	

}
