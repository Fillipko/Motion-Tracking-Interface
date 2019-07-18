import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DisplayVideo extends JFrame implements ActionListener 
{
	
    private JPanel contentPane;
    private VideoCap capture = new VideoCap();
    private JButton toggleCam;
    private boolean activeCamera;
    private static DisplayVideo frame;
    private Image img;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new DisplayVideo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DisplayVideo() throws IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        toggleCam = new JButton("Toggle Camera");
        toggleCam.addActionListener(this);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(Box.createHorizontalGlue());
        contentPane.add(toggleCam);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.LINE_AXIS));
        Thread t = new MyThread();
        t.start();
        /*
         * !!!!!!!!!!!!!!!!
         * change this file location
         * !!!!!!!!!!!!!!!!
         */
        img = ImageIO.read(new File("C:/Users/walkd/Documents/blackscreen.png"));
    }

    public void paint(Graphics g){
    	if(!activeCamera)
    	{
    		g.drawImage(img, 0, 0, this);
    	} 
    	else 
    	{
            g = contentPane.getGraphics();
            g.drawImage(capture.getOneFrame(), 0, 0, this);
    	}
    }

    class MyThread extends Thread{
        public void run() {
            for (;;){
                repaint();
                try 
                { 
                	Thread.sleep(33);
                } 
                catch (InterruptedException e) {
                	e.printStackTrace();
                }
            }
        }
    }
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(toggleCam))
		{
			activeCamera = !activeCamera;
		}
	}
}