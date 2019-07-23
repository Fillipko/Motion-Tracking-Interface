package opencvtest2;

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
		getLoc();
		handwidth = getHandWidth();;
		handheight = getHandHeight();
		System.out.println(p.toString() + ", "
				+ "handW " + handwidth + ", "
				+ "handH " + handheight);
		System.out.println(new Color(buff.getRGB(712, 678)));
		redd = new BufferedImage(handwidth, handheight, BufferedImage.TYPE_INT_BGR);

		int countx=0;
		int county=0;
		for(int y = (int)p.getY(); y < handheight + (int) p.getY(); y++) 
		{
			for(int x = (int)p.getX(); x < handwidth + (int)p.getX(); x++)
			{
				mycolor = new Color(buff.getRGB(x, y));
				int red = mycolor.getRed();
				int blue = mycolor.getBlue();
				int green = mycolor.getGreen();
				if(!(red<=252 && red>=248)  && blue<=2 && green<=2) {
					redd.setRGB(countx, county, buff.getRGB(x, y));
					countx++;
				} else {
					county++;
					x = (int)p.getX();
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
				if((red<=252 && red>=248)  && blue<=2 && green<=2)
				{
					p.setLocation(x + 3, y + 3);
					return;
				}	
			}
		}
	}
	public int getHandWidth() {
		int count=0;
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
		int count=0;
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
		try {
			g.drawImage(ImageIO.read(new File("src/_DSC4053_out.jpg")), 0, 0, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		new GetHandImage(ImageIO.read(new File("src/_DSC4053_out.jpg")));
	}
}