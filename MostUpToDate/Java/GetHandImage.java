package MyPackage;

import java.awt.*;
import java.awt.Point;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import org.opencv.core.*;
import java.lang.Process;
import java.lang.reflect.Array;

public class GetHandImage implements ImageObserver
{
	private int imgwidth;
	private int imgheight;
	private Color mycolor;
	private Point p;
	private BufferedImage buff;
//	private int handwidth;
//	private int handheight;
	private Image redd;
	private Robo robo;
	private int[] array;
	private Interface inter;

	public GetHandImage(int[] in) throws AWTException, IOException, InterruptedException
	{
//		buff = (BufferedImage) image;
//		imgwidth = image.getWidth(this);
//		imgheight = image.getHeight(this);
//		p = new Point();
//		robo = new Robo();
//		getLoc();
//		handwidth = Math.abs(getHandWidth());
//		handheight = Math.abs(getHandHeight());
//		redd = buff.getSubimage((int)p.getX(),(int) p.getY(), handwidth, handheight);
//		ImageIO.write((RenderedImage)redd, "png", new File("saved.png"));
//		Runtime run = Runtime.getRuntime();
//		String[] nargs = {"cmd.exe", "\"C:\\Users\\walkd\\Documents\\GitHub\\NWAWP-Hand-Tracker\\test NN\\baked2.py\"", "-i",
//				"\"C:\\Users\\walkd\\Documents\\Eclipse Workspace\\Test2\\saved.png\"", "-c",
//				"\"C:\\Users\\walkd\\Documents\\GitHub\\NWAWP-Hand-Tracker\\test NN\\training_3\\cp.ckpt\"", };
		robo = new Robo();
		array = in;
	}

//	public void checkSleep() throws InterruptedException
//	{
//		int tolerance = 25;
//		mycolor = new Color(buff.getRGB(imgheight / 2, imgwidth / 2));
//		int redCheck = mycolor.getRed();
//		int blueCheck = mycolor.getBlue();
//		int greenCheck = mycolor.getGreen();
//		int count = 0;
//		for(int y = 0; y < imgheight; y++)
//		{
//			for(int x = 0; x < imgwidth; x++)
//			{
//				mycolor = new Color(buff.getRGB(x, y));
//				int red = mycolor.getRed();
//				int blue = mycolor.getBlue();
//				int green = mycolor.getGreen();
//				if(!((red < redCheck + tolerance && red > redCheck - tolerance) &&
//						(blue < blueCheck + tolerance && blue > blueCheck - tolerance) &&
//						(green < greenCheck + tolerance && green > greenCheck - tolerance)))
//				{
//					count++;
//				}
//			}
//		}
//		if (count < 250) {
//			System.exit(0);
//			//robo.sleep();
//		}
//	}

	public void update(int gest) throws InterruptedException, IOException
	{
//NEED TENSORFLOW TO RUN

//		runBatch();
//		int output = readBatch();
//		buff = (BufferedImage) img;
//		checkSleep();
//		array = inter.getArray();
		System.out.println(array[0] + " sdfds " + array[1]);
		for(int i = 0; i < array.length; i++)
		{
			if(gest == i)
			{
				if (array[i] == 1)
				{
					robo.zoomIn();
				}
				else if(array[i] == 2)
				{
					robo.zoomOut();
				}
				else if(array[i] == 3)
				{
					robo.scrollUp();
				}
				else if(array[i] == 4)
				{
					robo.scrollDown();
				}
				else if(array[i] == 5)
				{
					robo.altTab();
				}
				else if(array[i] == 6)
				{
					robo.shiftAltTab();
				}
			}
		}
	}

//TENSORFLOW NEEDED TO RUN

//	//returns the first line of the output file
//	public int readBatch() throws IOException
//	{
//		File output = new File("C:\\Users\\walkd\\Documents\\Eclipse Workspace\\Test2\\src\\output.txt");
//		BufferedReader br = new BufferedReader(new FileReader(output));
//		return Integer.parseInt(br.readLine());
//	}
//	//runs batch file
//	public void runBatch() throws IOException
//	{
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		processBuilder.command("cmd", "/c", "run.bat");
//		processBuilder.directory(new File("C:\\Users\\walkd\\Documents\\GitHub\\Motion-Tracking-Interface\\"));
//		Process process = processBuilder.start();
//	}

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

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

	public void setArray(int[] array)
	{
		this.array = array;
	}

	public void setImage(Image img)
	{
		this.buff = (BufferedImage) img;
	}
}