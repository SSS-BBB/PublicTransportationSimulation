package main_program;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import maps.*;
import program_colors.ProgramColor;

public class MapSelectionPanel extends JPanel implements ActionListener {
	
	private MapSelectionFrame selectFrame;
	
	private JButton selectMapBtn;
	private JComboBox<String> mapSelectComboBox;
	
	public MapSelectionPanel(MapSelectionFrame selectFrame) {
		// Attributes setting
		this.selectFrame = selectFrame;
		
		// Panel setting
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(ProgramColor.DARK_BLUE);
		
		// Glue
		add(Box.createVerticalGlue());
		
		// Margin and Spacing
		int margin = 20;
		
		// Components
		JLabel selectMapLb = new JLabel("Select Map");
		selectMapLb.setAlignmentX(CENTER_ALIGNMENT);
		selectMapLb.setFont(new Font("Tahoma", Font.BOLD, 50));
		selectMapLb.setForeground(Color.white);
		add(selectMapLb);
		add(Box.createRigidArea(new Dimension(0, margin)));
		
		mapSelectComboBox = new JComboBox<>(MapList.getMapNameList());
		mapSelectComboBox.setPreferredSize(new Dimension(500, 40));
		mapSelectComboBox.setMaximumSize(mapSelectComboBox.getPreferredSize());
		mapSelectComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mapSelectComboBox.setBackground(null);
		// TODO: design drop down
		add(mapSelectComboBox);
		add(Box.createRigidArea(new Dimension(0, margin)));
		
		selectMapBtn = new JButton("Select this map");
		selectMapBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectMapBtn.addActionListener(this);
		selectMapBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// TODO: design button
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
	
}