package opencvtest2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import java.awt.Graphics;
import java.awt.GridLayout;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;



public class opencv implements ActionListener{
	JFrame frame = new JFrame();
	JButton button = new JButton("Start!");
	VideoCapture capture = new VideoCapture(0);
	JLabel up = new JLabel("Thumbs up if you want to scroll up!");
	JLabel down = new JLabel("Thumbs down if you want to scroll down!");
	JLabel left = new JLabel("Thumb to the left if you want to swipe left!");
	JLabel right = new JLabel("Thumb to the right if you want to swipe right!");
	Container north = new Container();
	Mat mat = new Mat();
	ImageConverter cvt = new ImageConverter();

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public opencv() {
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		button.setSize(250, 50);
		button.setVisible(true);
		frame.add(button);
		north.setLayout(new GridLayout(4,1));
		north.add(up);
		north.add(down);
		north.add(left);
		north.add(right);
		frame.add(north, BorderLayout.NORTH);
		button.addActionListener(this);
	}

	public static void main(String[] args) {
		new opencv();
	}

	public void actionPerformed(ActionEvent event) {
		button.setVisible(false);
		capture.isOpened();
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH, 500);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 500);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
		}
		capture.read(mat);
	}
	public BufferedImage getOneFrame(){
		mat = getNewImage(true);
		return cvt.mat2image(mat);        
	}
	public Mat getOneMatFrame(){
		mat = getNewImage(false);
		return mat;        
	}

	Mat tmp = new Mat();

	public Mat getNewImage(boolean bgr2rgb){
		capture.read(mat); 
		if (bgr2rgb)
			Imgproc.cvtColor(mat,mat,Imgproc.COLOR_RGB2BGR);
		return mat;
	}   
}