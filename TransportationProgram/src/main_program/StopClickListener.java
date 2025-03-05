package main_program;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import stops.StopSign;
import useful.*;

public class StopClickListener implements MouseListener {
	
	private MapPanel panel; // Panel that this listener is currently in
	
	public StopClickListener(MapPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Get Stop Sign Positions
		int[][] allStopSignPos = panel.getStopSignMapPos();
		double[] mousePos = new double[] { e.getX(), e.getY() };
		
		for (int i = 0; i < allStopSignPos.length; i++) {
			// int array to double array
			double[] stopPosConvert = new double[] { allStopSignPos[i][0], allStopSignPos[i][1] };
			
			// Stop Sign Clicked
			if (UsefulFunc.distanceBetweenTwoPoints(mousePos, stopPosConvert) < 10) {
				panel.getMapFrame().dispose();
				panel.stopLoop();
				new BusStopDetailFrame(panel.getMap(), panel.getVehicleList(), panel.getStopSignList()[i]);
				break;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
