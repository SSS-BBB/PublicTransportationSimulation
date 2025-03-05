package main_program;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import data_mangement.MapData;
import stops.StopSign;
import vehicles.Vehicle;

public class MapPanel extends JPanel implements Runnable {
	
	private MapFrame mapFrame;
	
	private MapData map;
	private int mapScale; // sometimes the map is too big compared to object position, so we need mapScale to make it balance
	
	private MouseListener stopClickListener;
	
	private Thread loop;
	private boolean loopStop;
	
	private int[][] stopSignMapPositions;
	
	public MapPanel(MapData map, MapFrame mapFrame, int mapScale) {
		this.map = map;
		this.mapFrame = mapFrame;
		this.mapScale = mapScale;
		setStopSignMapPos();
		
		this.stopClickListener = new StopClickListener(this);
		addMouseListener(this.stopClickListener);
		}
	
	@Override
	public void run() {
		while (loop != null && !loopStop) {
			map.updateData();
			
			System.out.println("Map Loop!");
			
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
		loopStop = false;
		loop.start();
	}
	
	public void stopLoop() {
		loopStop = true;
	}
	
	public void setStopSignMapPos() {
		StopSign[] stopSignArray = map.getAllStopSigns();
		stopSignMapPositions = new int[stopSignArray.length][2];
		
		for (int i = 0; i < stopSignArray.length; i++) {
			int posX = (int) ( stopSignArray[i].getPosition()[0]*mapScale );
			int posY = (int) ( stopSignArray[i].getPosition()[1]*mapScale );
			
			stopSignMapPositions[i][0] = posX;
			stopSignMapPositions[i][1] = posY;
		}
	}
	
	public void setStopSignIcon(Graphics2D g2d) {
		
		for (int i = 0; i < stopSignMapPositions.length; i++) {
			
			g2d.setColor(Color.black);
			g2d.fillRect(stopSignMapPositions[i][0], stopSignMapPositions[i][1], 10, 10);
		}
	}
	
	public void setVehicleIcon(Graphics2D g2d) {
		Vehicle[] vehicleArray = map.getAllVehicles();
		
		for (Vehicle vehicle : vehicleArray) {
			int posX = (int) ( vehicle.getVehiclePostion()[0]*mapScale );
			int posY = (int) ( vehicle.getVehiclePostion()[1]*mapScale );
			
			g2d.setColor(Color.blue);
			g2d.fillRect(posX, posY, 8, 15);
			
			if (vehicle.isShowRoute()) {
				// show route of the vehicle
				StopSign[] vehicleStops = vehicle.getVehicleStops();
				for (int i = 0; i < vehicleStops.length - 1; i++) {
					// Stop and Next Stop
					StopSign stop = vehicleStops[i];
					StopSign nextStop = vehicleStops[i + 1];
					
					// Positions
					int x1 = (int) ( stop.getPosition()[0]*mapScale );
					int y1 = (int) ( stop.getPosition()[1]*mapScale );
					int x2 = (int) ( nextStop.getPosition()[0]*mapScale );
					int y2 = (int) ( nextStop.getPosition()[1]*mapScale );
					
					g2d.setColor(Color.black);
					g2d.drawLine(x1, y1, x2, y2);
				}
			}
		}
	}
	
	public int[][] getStopSignMapPos() {
		return stopSignMapPositions;
	}
	
	public MapFrame getMapFrame() {
		return mapFrame;
	}
	
	public Vehicle[] getVehicleList() {
		return map.getAllVehicles();
	}
	
	public StopSign[] getStopSignList() {
		return map.getAllStopSigns();
	}
	
	public MapData getMap() {
		return map;
	}
	
}
