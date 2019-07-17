
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import java.awt.Graphics;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class opencvtesting implements ActionListener{
	JFrame frame = new JFrame();
	JButton button = new JButton("Setup hand here");
	private JPanel contentPane;
	VideoCapture videoCap = new VideoCapture();

	public opencvtesting() {
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		button.setSize(250, 50);
		button.setVisible(true);
		frame.getContentPane().add(button);
		button.addActionListener(this);

		contentPane.setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new opencvtesting();
	}
	
	public void actionPerformed(ActionEvent event) {
		button.setVisible(false);

	}
}


