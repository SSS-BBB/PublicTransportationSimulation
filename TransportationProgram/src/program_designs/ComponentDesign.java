package program_designs;

import java.awt.*;
import javax.swing.*;

public class ComponentDesign {
	
	public static void buttonDesign(JButton btn, int width, int height, int fontSize) {
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBackground(ProgramColor.LIGHT_BLUE);
		btn.setForeground(Color.black);
		// btn.setBorder(BorderFactory.createLineBorder(ProgramColor.LIGHT_GREEN, 2));
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(width, height));
		btn.setMaximumSize(btn.getPreferredSize());
		btn.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		
	}
	
	public static void buttonDesign(JButton btn, int width, int height) {
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBackground(ProgramColor.LIGHT_BLUE);
		btn.setForeground(Color.black);
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(width, height));
		btn.setMaximumSize(btn.getPreferredSize());
	}
	
}