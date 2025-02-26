package main_program;

import java.awt.Color;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import data_mangement.MapData;
import stops.StopSign;
import vehicles.Vehicle;

public class MapPanel extends JPanel implements Runnable {
	
	private MapPanel contentPane;
	private MapData map;
	
	private ArrayList<JButton> iconBtnList;
	
	private Thread loop;
	
	public MapPanel(MapData map) {
		this.map = map;
		iconBtnList = new ArrayList<>();
	}
	
	@Override
	public void run() {
		while (loop != null) {
			map.updateData();
			
			repaint();
			
			try {
                Thread.sleep(500); // 1000 ms = 1 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		setStopSignIcon(g2d);
		setVehicleIcon(g2d);
		
		g2d.dispose();
	}
	
	public void startLoop() {
		loop = new Thread(this);
		loop.start();
}
	
	public void setStopSignIcon(Graphics2D g2d) {
		StopSign[] stopSignArray = map.getAllStopSigns();
		
		for (StopSign stop : stopSignArray) {
			int posX = (int) stop.getPosition()[0];
			int posY = (int) stop.getPosition()[1];
			
			g2d.setColor(Color.black);
			g2d.fillRect(posX*40, posY*40, 10, 10);
		}
	}
	
	public void setVehicleIcon(Graphics2D g2d) {
		Vehicle[] vehicleArray = map.getAllVehicles();
		
		for (Vehicle vehicle : vehicleArray) {
			int posX = (int) vehicle.getVehiclePostion()[0];
			int posY = (int) vehicle.getVehiclePostion()[1];
			
			g2d.setColor(Color.blue);
			g2d.fillRect(posX*40, posY*40, 10, 10);
		}
	}
	
	public void clearIcon() {
		// clear all icons from screen
		StopSign[] stopSignArray = map.getAllStopSigns();
		
		for (JButton iconBtn : iconBtnList) {
			remove(iconBtn);
		}
		
		iconBtnList.removeAll(iconBtnList);
	}
	
}
