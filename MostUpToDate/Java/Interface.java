package MyPackage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface implements ActionListener{

	private JComboBox CommandList, CommandList1, CommandList2, CommandList3, CommandList4, CommandList5, CommandList6;
	private JFrame frame;
	private JLabel label, label1, label2, label3, label4, label5, label6;
	private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6;
	private JButton set, reset;
	private int zoomin, zoomout, scrollup, scrolldown, swiper, swipel, sleep;

	public static void main(String[] args) {
		new Interface();
	}
	public Interface() {
		frame = new JFrame();
		frame.setTitle("Hand Tracking");
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(7,2));
		set = new JButton ("Set");
		reset = new JButton ("Reset");
		set.setSize(10, 10);
		reset.setSize(10, 10);
		set.setVisible(true);
		reset.setVisible(true);
		set.addActionListener(this);
		reset.addActionListener(this);
		frame.add(set);
		frame.add(reset);
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
		label = new JLabel("Zoom in");
		label.setSize(100, 100);
		label.setVisible(true);
		label1 = new JLabel("Zoom out");
		label1.setSize(100, 100);
		label1.setVisible(true);
		label2 = new JLabel("Scroll up");
		label2.setSize(100, 100);
		label2.setVisible(true);
		label3 = new JLabel("Scroll down");
		label3.setSize(100, 100);
		label3.setVisible(true);
		label4 = new JLabel("Swipe right");
		label4.setSize(100, 100);
		label4.setVisible(true);
		label5 = new JLabel("Swipe left");
		label5.setSize(100, 100);
		label5.setVisible(true);
		label6 = new JLabel("Sleep");
		label6.setSize(100, 100);
		label6.setVisible(true);

		//		String[]actions = {"Select ", "zoom in", "zoom out", "scroll up", "scroll down", "alt shift tab" ,"alt tab", "sleep"};
		//		ActionList = new JComboBox(actions);
		//		ActionList.setVisible(true);
		//		ActionList.setSize(100,100);
		//		frame.add(ActionList);

		String[]commands = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList = new JComboBox(commands);
		CommandList.setVisible(true);
		CommandList.setSize(100,100);
		CommandList.addActionListener(this);
		String[]commands1 = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList1 = new JComboBox(commands1);
		CommandList1.setVisible(true);
		CommandList1.setSize(100,100);
		CommandList1.addActionListener(this);
		String[]commands2 = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList2 = new JComboBox(commands2);
		CommandList2.setVisible(true);
		CommandList2.setSize(100,100);
		CommandList2.addActionListener(this);
		String[]commands3 = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList3 = new JComboBox(commands3);
		CommandList3.setVisible(true);
		CommandList3.setSize(100,100);
		CommandList3.addActionListener(this);
		String[]commands4 = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList4 = new JComboBox(commands4);
		CommandList4.setVisible(true);
		CommandList4.setSize(100,100);
		CommandList4.addActionListener(this);
		String[]commands5 = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList5 = new JComboBox(commands5);
		CommandList5.setVisible(true);
		CommandList5.setSize(100,100);
		CommandList5.addActionListener(this);
		String[]commands6 = {"Assign action for each command", "fist", "open hand", "thumbs up", "thumbs down", "point right" ,"point left", "cover camera"};
		CommandList6 = new JComboBox(commands6);
		CommandList6.setVisible(true);
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
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		frame.add(panel5);
		frame.add(panel6);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source instanceof JComboBox) {
			JComboBox cb = (JComboBox)e.getSource();
			int selected = cb.getSelectedIndex();
			if (e.getSource().equals(CommandList)) {
				zoomin = selected;
			}
			if (e.getSource().equals(CommandList1)) {
				zoomout = selected;
			}
			if (e.getSource().equals(CommandList2)) {
				scrollup = selected;
			}
			if (e.getSource().equals(CommandList3)) {
				scrolldown = selected;
			}
			if (e.getSource().equals(CommandList4)) {
				swiper = selected;
			}
			if (e.getSource().equals(CommandList5)) {
				swipel = selected;
			}
			if (e.getSource().equals(CommandList6)) {
				sleep = selected;
			}
		}
		else if (source instanceof JButton) {
			if (e.getSource().equals(set)) {
				int [] array = {zoomin, zoomout, scrollup, scrolldown, swiper, swipel, sleep};
				int count = 0;
				for (int j = 0; j< array.length; j++) {
					for (int i = j+1; i<array.length; i++) {
						if (array[j] == array[i] || array[j]==0) {
							count++;
						}
					}
				}
				if (count>0) {
					set.setLabel("Error! Try again! Set");
				} else {
					System.out.println("good work");
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

}
