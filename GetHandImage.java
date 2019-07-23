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

public class GetHandImage extends JFrame{

	private BufferedImage image;
	private int width;
	private int height;
	private Color mycolor;

	public GetHandImage(Image image)
	{
		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		setVisible(true);
		//		setSize(800, 800);
		BufferedImage buff = (BufferedImage) image;
		width = image.getWidth(this);
		height = image.getHeight(this);
		Point p = new Point();
		for(int y = 0; y < height; y++) 
		{
			for(int x = 0; x < width; x++)
			{
				mycolor = new Color(buff.getRGB(x, y));
				int red = mycolor.getRed();
				int blue = mycolor.getBlue();
				int green = mycolor.getGreen();
				if(red==250 && blue == 0 && green==0)
				{
					p.setLocation(x,y);
				}
				break;
			}
			break;
		}
		BufferedImage redd = null;
		int countx=0;
		int county=0;
		for(int y = (int) p.getY() + 2; y < height; y++) 
		{
			for(int x = (int)p.getX() + 2; x < width; x++)
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
				}
			}
		}

	}

	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}
	public static void main(String[] args) throws IOException {
		new GetHandImage(ImageIO.read(new File("src/redII'.jpg")));
	}
}
