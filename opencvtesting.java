
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class opencvtesting implements ActionListener{
	JFrame frame = new JFrame();
	JButton button = new JButton("Setup hand here");

	public opencvtesting() {
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		button.setSize(250, 50);
		button.setVisible(true);
		frame.getContentPane().add(button);
		button.addActionListener(this);
	}

	public static void main(String[] args) {
		//		System.out.println("Welcome to OpenCV " + Core.VERSION);
		//		System.out.println(System.getProperty("java.library.path"));
		//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//		Mat m  = Mat.eye(3, 3, CvType.CV_8UC1);
		//		System.out.println("m = " + m.dump());
		new opencvtesting();
	}
	public void actionPerformed(ActionEvent event) {
		button.setVisible(false);
	}
}


