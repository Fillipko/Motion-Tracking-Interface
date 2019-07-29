package MyPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interface extends JFrame implements ActionListener{

	private JComboBox CommandList, CommandList1, CommandList2, CommandList3, CommandList4, CommandList5, CommandList6;
	private JLabel label, label1, label2, label3, label4, label5, label6;
	private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6;
	private JButton set, reset;
	private int fist, openH, thumbU, thumbD, pointR, pointL, coverCam;
	private int [] array;
	private String[] commands;

	public static void main(String[] args) {
		new Interface();
	}
	public Interface() {
		setTitle("Hand Tracking");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(7,2));
		set = new JButton ("Set");
		reset = new JButton ("Reset");
		set.addActionListener(this);
		reset.addActionListener(this);
		add(set);
		add(reset);
		
		fist = 1;
		openH = 2;
		thumbU = 3;
		thumbD = 4;
		pointR = 5;
		pointL = 6;
		coverCam= 7;
		
		commands = new String[]{"Assign action for each gesture", "Zoom In", "Zoom Out", "Scroll Up", "Scroll Down", "Swipe Right", "Swipe Left", "Sleep"};
		
		panel = new JPanel();
		panel.setSize(500, 70);
		
		panel1 = new JPanel();
		panel1.setSize(500, 70);
		
		panel2 = new JPanel();
		panel2.setSize(500, 70);
		
		panel3 = new JPanel();
		panel3.setSize(500, 70);
		
		panel4 = new JPanel();
		panel4.setSize(500, 70);
		
		panel5 = new JPanel();
		panel5.setSize(500, 70);
		
		panel6 = new JPanel();
		panel6.setSize(500, 70);
		
		label = new JLabel("Closed Hand");
		label.setSize(100, 100);
		
		label1 = new JLabel("Open Hand");
		label1.setSize(100, 100);

		label2 = new JLabel("Thumbs Up");
		label2.setSize(100, 100);

		label3 = new JLabel("Thumbs Down");
		label3.setSize(100, 100);

		label4 = new JLabel("Point Right");
		label4.setSize(100, 100);

		label5 = new JLabel("Point Left");
		label5.setSize(100, 100);

		label6 = new JLabel("Cover Camera");
		label6.setSize(100, 100);		
				
		CommandList = new JComboBox(commands);
		CommandList.setSelectedIndex(1);
		CommandList.setSize(100,100);
		CommandList.addActionListener(this);
	
		CommandList1 = new JComboBox(commands);
		CommandList1.setSelectedIndex(2);
		CommandList1.setSize(100,100);
		CommandList1.addActionListener(this);
		
		CommandList2 = new JComboBox(commands);
		CommandList2.setSelectedIndex(3);
		CommandList2.setSize(100,100);
		CommandList2.addActionListener(this);
		
		CommandList3 = new JComboBox(commands);
		CommandList3.setSelectedIndex(4);
		CommandList3.setSize(100,100);
		CommandList3.addActionListener(this);
		
		CommandList4 = new JComboBox(commands);
		CommandList4.setSelectedIndex(5);
		CommandList4.setSize(100,100);
		CommandList4.addActionListener(this);
		
		CommandList5 = new JComboBox(commands);
		CommandList5.setSelectedIndex(6);
		CommandList5.setSize(100,100);
		CommandList5.addActionListener(this);
		
		CommandList6 = new JComboBox(commands);
		CommandList6.setSelectedIndex(7);
		CommandList6.setSize(100,100);
		CommandList6.addActionListener(this);
		
		panel.add(label);
		panel.add(CommandList);
		panel1.add(label1);
		panel1.add(CommandList1);
		panel2.add(label2);
		panel2.add(CommandList2);
		panel3.add(label3);
		panel3.add(CommandList3);
		panel4.add(label4);
		panel4.add(CommandList4);	
		panel5.add(label5);
		panel5.add(CommandList5);
		panel6.add(label6);
		panel6.add(CommandList6);	
		add(panel);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JComboBox) {
			JComboBox cb = (JComboBox)e.getSource();
			int selected = cb.getSelectedIndex();
			if (e.getSource().equals(CommandList)) {
				fist = selected;
			}
			if (e.getSource().equals(CommandList1)) {
				openH = selected;
			}
			if (e.getSource().equals(CommandList2)) {
				thumbU = selected;
			}
			if (e.getSource().equals(CommandList3)) {
				thumbD = selected;
			}
			if (e.getSource().equals(CommandList4)) {
				pointR = selected;
			}
			if (e.getSource().equals(CommandList5)) {
				pointL = selected;
			}
			if (e.getSource().equals(CommandList6)) {
				coverCam = selected;
			}
		}
		else if (source instanceof JButton) {
			if (e.getSource().equals(set)) {
				int [] array = {fist, openH, thumbU, thumbD, pointR, pointL, coverCam};
				int count = 0;
				for (int j = 0; j < array.length; j++) {
					for (int i = j + 1; i < array.length; i++) {
						if (array[j] == array[i] || array[j] == 0) {
							count++;
						}
					}
				}
				if (count > 0) {
					JOptionPane.showMessageDialog(this, "Please assign only one command for each gesture", "Error!", JOptionPane.ERROR_MESSAGE);
				} else {
					set.setLabel("Settings Updated!");
					this.array = array;
				}
			}
			if (e.getSource().equals(reset)) {
				CommandList.setSelectedIndex(0);
				CommandList1.setSelectedIndex(0);
				CommandList2.setSelectedIndex(0);
				CommandList3.setSelectedIndex(0);
				CommandList4.setSelectedIndex(0);
				CommandList5.setSelectedIndex(0);
				CommandList6.setSelectedIndex(0);
			}
		}
	}
	public int[] getArray() {
		return array;
	}
}
