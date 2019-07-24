package opencvtest2;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GetHandImage extends JFrame{

	private int imgwidth;
	private int imgheight;
	private Color mycolor;
	private Point p;
	private BufferedImage buff;
	private int handwidth;
	private int handheight;
	private Image redd;

	public GetHandImage(Image image) throws AWTException, IOException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 600);
		buff = (BufferedImage) image;
		imgwidth = image.getWidth(this);
		imgheight = image.getHeight(this);
		p = new Point();
		getLoc();
		handwidth = getHandWidth();;
		handheight = getHandHeight();
		redd = buff.getSubimage((int)p.getX(),(int) p.getY(), handwidth, handheight);
		repaint();
		Runtime run = Runtime.getRuntime();
		String[] nargs = {"echo", "hello world"};
		Process p = run.exec(nargs);
		BufferedReader is =
				new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		
		while ((line = is.readLine()) != null)
			
		System.out.println(line);
//		Robot robot = new Robot();
//		redd = robot.createScreenCapture.(new Rectangle((int)p.getX(),(int)p.getY(), handwidth, handheight));
//		redd = new BufferedImage(handwidth, handheight, BufferedImage.TYPE_3BYTE_BGR);
//
//		int countx=0;
//		int county=0;
//		for(int y = (int)p.getY(); y < handheight + (int) p.getY(); y++) 
//		{
//			for(int x = (int)p.getX(); x < handwidth + (int)p.getX(); x++)
//			{
//				mycolor = new Color(buff.getRGB(x, y));
//				int red = mycolor.getRed();
//				int blue = mycolor.getBlue();
//				int green = mycolor.getGreen();
//				if(!(red<=252 && red>=248) && blue<=2 && green<=2) {
//					redd.setRGB(countx, county, buff.getRGB(x, y));
//					countx++;
//				} else {
//					county++;
//					x = (int)p.getX();
//				}
//			}
//		}
	}
	public void getLoc() {
		for(int y = 0; y < imgheight; y++) 
		{
			for(int x = 0; x < imgwidth; x++)
			{
				mycolor = new Color(buff.getRGB(x, y));
				int red = mycolor.getRed();
				int blue = mycolor.getBlue();
				int green = mycolor.getGreen();
				if((red<=252 && red>=248)  && blue<=2 && green<=2)
				{
					p.setLocation(x + 3, y + 3);
					return;
				}	
			}
		}
	}
	public int getHandWidth() {
		int count=1;
		for(int x = (int)p.getX(); x < imgwidth; x++) {
			mycolor = new Color(buff.getRGB(x, (int)p.getY()));
			int red = mycolor.getRed();
			int blue = mycolor.getBlue();
			int green = mycolor.getGreen();
			if((red<=252 && red>=248)  && blue<=2 && green<=2) {
				return x-((int)p.getX());
			}
		}
		return count;
	}
	public int getHandHeight() {
		int count=1;
		for(int y = (int) p.getY(); y < imgheight; y++) {
			mycolor = new Color(buff.getRGB((int)p.getX(), y));
			int red = mycolor.getRed();
			int blue = mycolor.getBlue();
			int green = mycolor.getGreen();
			if((red<=252 && red>=248)  && blue<=2 && green<=2) {
				return y - ((int)p.getY());
			} 
		}
		return count;
	}
	public void paint(Graphics g)
	{
		g.drawImage(redd, 0, 0, this);
	}
	public static void main(String[] args) throws IOException, AWTException {
		new GetHandImage(ImageIO.read(new File("src/_DSC4053_out.jpg")));
	}
}