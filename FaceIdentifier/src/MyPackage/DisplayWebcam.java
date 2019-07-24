package MyPackage;

import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DisplayWebcam extends JFrame implements ActionListener, ImageObserver 
{
	private JPanel picturePanel;
	private JPanel contentPane;
	private VideoCap capture;
	private JButton toggleCam;
	private JButton teachNew;
	private JButton about;
	private boolean activeCamera;
	private Thread t;
	private Image blackscreen;
	private int counter; 

	public DisplayWebcam(int width, int height) throws IOException
	{
		picturePanel = new JPanel()
		{
			public void paint(Graphics g) {
				if(!activeCamera) 
				{
					g.drawImage(blackscreen, 0, -20, this);
				}
				if (activeCamera)
				{
					g = picturePanel.getGraphics();
					g.drawImage(capture.getOneFrame(), 70, 100, this);
				}
			}
		};
		JPanel buttonPane = new JPanel();
		contentPane = new JPanel();
		capture = new VideoCap();
		toggleCam = new JButton("Toggle Camera");
		teachNew = new JButton("Teach New Gesture");
		about = new JButton("About");
		activeCamera = false;
		blackscreen = ImageIO.read(new File("Images/Motion Tracking Logo (1).jpg"));
		counter = 0;

		setTitle("Kine6 Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setContentPane(contentPane);
		setVisible(true);
		toggleCam.addActionListener(this);
//		toggleCam.setPreferredSize(new Dimension(100, 100));
		buttonPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 100;
		c.gridwidth = 100;
		c.anchor = GridBagConstraints.SOUTH;
		buttonPane.add(toggleCam, c);
		c.gridx = 100;
		buttonPane.add(teachNew, c);
		c.gridx = 200;
		buttonPane.add(about, c);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(Box.createHorizontalBox());
		contentPane.add(picturePanel, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.SOUTH);

		t = new MyThread();
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
		if(e.getSource().equals(teachNew))
		{
			
		}
		if(e.getSource().equals(about))
		{
			
		}
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
	{
		return false;
	}

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					new DisplayWebcam(800, 700);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
