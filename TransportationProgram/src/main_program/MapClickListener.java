package main_program;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import stops.StopSign;
import useful.*;

public class MapClickListener implements MouseListener {
	
	private MapPanel panel; // Panel that this listener is currently in
	
	public MapClickListener(MapPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Get Stop Sign Positions
		int[][] allStopSignPos = panel.getStopSignMapPos();
		double[] mousePos = new double[] { e.getX(), e.getY() };
		
		// check for stop sign clicked
		for (int i = 0; i < allStopSignPos.length; i++) {
			// int array to double array
			double[] stopPosConvert = new double[] { allStopSignPos[i][0], allStopSignPos[i][1] };
			
			// Stop Sign Clicked
			StopSign checkStopSign = panel.getStopSignList()[i];
			if (UsefulFunc.distanceBetweenTwoPoints(mousePos, stopPosConvert) < Math.max(checkStopSign.getIconWidth(), checkStopSign.getIconHeight())) {
				panel.getMapFrame().dispose();
				panel.stopLoop();
				new BusStopDetailFrame(panel.getMap(), panel.getVehicleList(), checkStopSign);
				return;
			}
		}
		
		// check for back button clicked
		int[] mousePosI = new int[] { e.getX(), e.getY() };
		int[] backPos = panel.getBackBtnPos();
		int[] backSize = panel.getBackBtnSize();
		if (isInRect(mousePosI, backPos, backSize)) {
			// Back button clicked
			panel.getMap().showAll();
			new MapSelectionFrame();
			
			panel.stopLoop();
			return;
		}
		
	}
	
	private boolean isInRect(int[] pos, int[] rectPos, int[] rectSize) {
		boolean checkX = (pos[0] >= rectPos[0]) && (pos[0] <= rectPos[0] + rectSize[0]);
		boolean checkY = (pos[1] >= rectPos[1]) && (pos[1] <= rectPos[1] + rectSize[1]);
		return checkX && checkY;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
