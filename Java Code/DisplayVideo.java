import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class DisplayVideo implements ActionListener
{

	private JFrame frame;
	private JButton toggleCam;
	private JPanel panel;
	private VideoCap capture;
	private Thread t;
	private boolean activeCamera;

	public DisplayVideo()
	{				
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		activeCamera = false;
		panel = new JPanel();
		frame = new JFrame("Video");
		toggleCam = new JButton("Toggle Cam");
		toggleCam.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);
		panel.add(Box.createHorizontalGlue());
		panel.add(toggleCam);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		frame.add(panel, BorderLayout.NORTH);
		//capture = new VideoCapture();
		//		capture.open(0);
		t = new Thread();
		t.start();
	}

	public static void main(String[] args)
	{
		new DisplayVideo();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(toggleCam))
		{
			activeCamera = !activeCamera;
		}
	}

	public void paint(Graphics g)
	{
		g = panel.getGraphics();
		//		g.drawImage(capture.getOneFrame(), 0, 0, this);
	}

	public void run()
	{
		while(activeCamera) {
			//		repaint(g);
			try {
				Thread.sleep(33);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
