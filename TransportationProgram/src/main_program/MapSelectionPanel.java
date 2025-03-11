package main_program;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import maps.*;
import program_designs.ComponentDesign;
import program_designs.ProgramColor;

public class MapSelectionPanel extends JPanel implements ActionListener {
	
	private MapSelectionFrame selectFrame;
	
	private JButton selectMapBtn;
	private JComboBox<String> mapSelectComboBox;
	
	public MapSelectionPanel(MapSelectionFrame selectFrame) {
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(ProgramColor.SELECTED_DROPDOWN));
		
		// Attributes setting
		this.selectFrame = selectFrame;
		
		// Panel setting
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// setBackground(ProgramColor.DARK_BLUE);
		
		// Glue
		add(Box.createVerticalGlue());
		
		// Margin and Spacing
		int margin = 20;
		int spacing = 30;
		
		// Components
		JLabel selectMapLb = new JLabel("Select Map");
		selectMapLb.setAlignmentX(CENTER_ALIGNMENT);
		selectMapLb.setFont(new Font("Tahoma", Font.BOLD, 50));
		selectMapLb.setForeground(Color.white);
		add(selectMapLb);
		add(Box.createRigidArea(new Dimension(0, margin)));
		
		mapSelectComboBox = new JComboBox<>(MapList.getMapNameList());
		mapSelectComboBox.setPreferredSize(new Dimension(280, 55));
		mapSelectComboBox.setMaximumSize(mapSelectComboBox.getPreferredSize());
		mapSelectComboBox.setBackground(ProgramColor.DROPDOWN_BACKGROUND);
		mapSelectComboBox.setForeground(Color.black);
		mapSelectComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mapSelectComboBox.setFocusable(false);
		add(mapSelectComboBox);
		add(Box.createRigidArea(new Dimension(0, spacing)));
		
		selectMapBtn = new JButton("Select this map");
		selectMapBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectMapBtn.addActionListener(this);
		ComponentDesign.buttonDesign(selectMapBtn, 150, 40, 16);
		add(selectMapBtn);
		
		// Glue
		add(Box.createVerticalGlue());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectMapBtn) {
			String selectedMapName = mapSelectComboBox.getSelectedItem().toString();
			new MapFrame(MapList.getMapFromName(selectedMapName));
			selectFrame.dispose();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Background
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		
		Color color1 = ProgramColor.DARK_BLUE;
		Color color2 = ProgramColor.LIGHT_GREEN;
		GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}
	
}