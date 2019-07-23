package MyPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

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
	private BufferedImage redd;

	public GetHandImage(Image image)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 600);
		buff = (BufferedImage) image;
		imgwidth = image.getWidth(this);
		imgheight = image.getHeight(this);
		p = new Point();
		handwidth= 1;
		handheight=1;
		getLoc();
		getHandWidth();
		getHandHeight();
		System.out.println(p.toString() + ", "
				+ "handW " + handwidth + ", "
						+ "handH " + handheight + ", imgW "
								+ imgwidth + ", imgH " + imgheight);
		redd = new BufferedImage(handwidth, handheight, BufferedImage.TYPE_INT_BGR);

		int countx=0;
		int county=0;
		for(int y = (int) p.getY() + 2; y < handheight + (int) p.getY() + 3; y++) 
		{
			for(int x = (int)p.getX() + 2; x < handwidth + (int)p.getX() + 3; x++)
			{
				mycolor = new Color(buff.getRGB(x, y));
				int red = mycolor.getRed();
				int blue = mycolor.getBlue();
				int green = mycolor.getGreen();
				if(!(red==250 && blue == 0 && green==0)) {
					redd.setRGB(countx, county, buff.getRGB(x, y));
					countx++;
				} else {
					county++;
					x = (int)p.getX() + 2;
				}
			}
		}
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
				if(red==250 && blue == 0 && green==0)
				{
					p.setLocation(x,y);
					return;
				}	
			}
		}
	}
	public void getHandWidth() {
		int count=0;
		for(int x = (int)p.getX() + 3; x < imgwidth; x++)
		{
			mycolor = new Color(buff.getRGB(x, (int)p.getY() + 3));
			int red = mycolor.getRed();
			int blue = mycolor.getBlue();
			int green = mycolor.getGreen();
			if((red==250 && blue == 0 && green==0)) {
				handwidth= x-((int)p.getX() + 2);
				return;
			} else {
				count++;
			}
		}
		System.out.println(count);
	}
	public void getHandHeight() {
		int count=0;
		for(int y = (int) p.getY() + 4; y < imgheight; y++) 
		{
			mycolor = new Color(buff.getRGB((int)p.getX() + 4, y));
			int red = mycolor.getRed();
			int blue = mycolor.getBlue();
			int green = mycolor.getGreen();
			if((red == 250 && blue == 0 && green==0)) {
				System.out.println(y);
				handheight= y - ((int)p.getY() + 3);
				return;
			} else {
				count++;
				
			}
		}
		System.out.println(count);
	}
	public void paint(Graphics g)
	{
		g.drawImage(redd, 0, 0, this);
	}
	public static void main(String[] args) throws IOException {
		new GetHandImage(ImageIO.read(new File("src/_DSC4053_out.jpg")));
	}
}
