package MyPackage;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.lang.Process;

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
		handwidth = getHandWidth();
		handheight = getHandHeight();
		redd = buff.getSubimage((int)p.getX(),(int) p.getY(), handwidth, handheight);
		repaint();
		ImageIO.write((RenderedImage)redd, "png", new File("saved.png"));
//		Imgcodecs.imwrite("DSC4053_out.jpg", bufferedImageToMat((BufferedImage)redd));
		Runtime run = Runtime.getRuntime();
		String[] nargs = {"cmd.exe", "\"C:\\Users\\walkd\\Documents\\GitHub\\NWAWP-Hand-Tracker\\test NN\\baked2.py\"", "-i", 
				"C:\\Users\\walkd\\Documents\\Eclipse Workspace\\Test2\\saved.png"};
		Process p = run.exec(nargs);
		BufferedReader is =
				new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		
		for(;;)
		{
			line = is.readLine();
			System.out.println("Outside if: " + line);
			if(line.equals("1"))
			{
				System.out.println("Inside if 1: " + line);
				System.out.println("open");
			}
			else if(line.equals("0"))
			{
				System.out.println("Inside if 2: " + line);
				System.out.println("closed");
			}
		}
		
		
		
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
				return x-((int)p.getX() + 2);
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
				return y - ((int)p.getY() + 2);
			} 
		}
		return count;
	}
	public void paint(Graphics g)
	{
		g.drawImage(redd, 0, 0, this);
	}
	
	public Mat bufferedImageToMat(BufferedImage bi) {
		  Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC4);
		  byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		  mat.put(0, 0, data);
		  return mat;
		}
	
	public static void main(String[] args) throws IOException, AWTException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new GetHandImage(ImageIO.read(new File("src/_DSC4053_out.jpg")));
	}
}