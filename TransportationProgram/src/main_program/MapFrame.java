package main_program;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
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
					MapFrame frame = new MapFrame();
					MapPanel panel = new MapPanel(new TestMap());
					panel.setLayout(null);
					
					frame.add(panel);
					frame.setVisible(true);
					
					panel.startLoop();
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
