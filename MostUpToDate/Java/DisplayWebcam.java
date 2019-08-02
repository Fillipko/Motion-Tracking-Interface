package MyPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;

public class DisplayWebcam extends JFrame implements ActionListener, ImageObserver
{
	private JPanel picturePanel;
	private JPanel contentPane;
	private VideoCap capture;
	private JButton toggleCam;
	private JButton gestureList;
	private JButton about;
	private boolean activeCamera;
	private Thread t;
	private Thread t1;
	private Image background;
	private int counter;
	private GetHandImage getHand;
	private Image currentImage;
	private JButton instructions;
	private Interface inter;
	public int[] in = new int[]{1, 2};

	public DisplayWebcam(int width, int height) throws IOException, AWTException, InterruptedException
	{
		picturePanel = new JPanel()
		{
			public void paint(Graphics g) {
				if(!activeCamera)
				{
					g.drawImage(background, 0, 0, this);
				}
				if (activeCamera)
				{
					currentImage = capture.getOneFrame();
					g = picturePanel.getGraphics();
					g.drawImage(currentImage, 70, 100, this);
				}
			}
		};
		capture = new VideoCap();
		currentImage = capture.getOneFrame();
		picturePanel.setSize(new Dimension(500, 500));
		JPanel buttonPane = new JPanel();
		contentPane = new JPanel();
		toggleCam = new JButton("Toggle Camera");
		gestureList = new JButton("Gesture Settings");
		about = new JButton("About");
		activeCamera = false;
		background = ImageIO.read(new File("Images/MenuLogo.jpg"));
		counter = 0;
		getHand = new GetHandImage(in);
		instructions = new JButton("Instructions");

		setResizable(false);
		this.setTitle("Kine6 Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setContentPane(contentPane);
		setVisible(true);
		setIconImage(ImageIO.read(new File("Images/IconLogo.jpg")));
		toggleCam.addActionListener(this);
		instructions.addActionListener(this);
		about.addActionListener(this);
		gestureList.addActionListener(this);
		buttonPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 100;
		c.gridwidth = 100;
		c.anchor = GridBagConstraints.SOUTH;
		buttonPane.add(instructions, c);
		c.gridx = 100;
		//buttonPane.add(gestureList, c);
		c.gridx = 200;
		buttonPane.add(toggleCam, c);
		c.gridx = 300;
		buttonPane.add(about, c);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(Box.createHorizontalBox());
		contentPane.add(picturePanel, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		t = new MyThread();
		t1 = new MyThread2();
		t.start();
	}

	public void paint(Graphics g)
	{
		if(counter == 0)
		{
			counter++;
			super.paint(g);
		}
		picturePanel.paint(picturePanel.getGraphics());
	}

	class MyThread extends Thread
	{
		public void run() {
			while(true)
			{
				repaint();
				try
				{
					Thread.sleep(33);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				System.gc();
			}
		}
	}
	class MyThread2 extends Thread {
		public void run() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(toggleCam))
		{
			if(activeCamera)
			{
				activeCamera = false;
			}
			else
			{
				activeCamera = true;
			}
		}
		if(e.getSource().equals(gestureList))
		{
			inter = new Interface(getHand, this);

//			while(inter.flag == false){
//			    t1.run();
//			}
			in = inter.getArray();
			System.out.println(in[0]);
			System.out.println(in[1]);
//			if (inter.flag) {
//				in = inter.getArray();
//			}
		}
		if(e.getSource().equals(about))
		{
			JOptionPane.showMessageDialog(this, "Made by:\n Fillip Cannard\n Sidharth Daga\n"
					+ " Rowan Sheets\n David Zager\n Made Using OpenCV", "About", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource().equals(instructions))
		{
			JOptionPane.showMessageDialog(this, "This applications lets you use your hand to control your computer! \n"
					+ " To begin, go to 'Gesture settings' and customize your experience.\n", "Then, click 'Toggle Camera' to begin!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
	{
		return false;
	}

	public static void main(String[] args)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new DisplayWebcam(800, 700);
					new Sound();

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}