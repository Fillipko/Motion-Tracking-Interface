package MyPackage;

import java.awt.*;
import java.awt.Point;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import org.opencv.core.*;
import java.lang.Process;

public class GetHandImage implements ImageObserver
{
	private int imgwidth;
	private int imgheight;
	private Color mycolor;
	private Point p;
	private BufferedImage buff;
	private int handwidth;
	private int handheight;
	private Image redd;

	public GetHandImage(Image image) throws AWTException, IOException, InterruptedException
	{
		buff = (BufferedImage) image;
		imgwidth = image.getWidth(this);
		imgheight = image.getHeight(this);
		p = new Point();
		getLoc();
		handwidth = getHandWidth();
		handheight = getHandHeight();
		redd = buff.getSubimage((int)p.getX(),(int) p.getY(), handwidth, handheight);
		ImageIO.write((RenderedImage)redd, "png", new File("saved.png"));
		Runtime run = Runtime.getRuntime();
		String[] nargs = {"cmd.exe", "\"C:\\Users\\walkd\\Documents\\GitHub\\NWAWP-Hand-Tracker\\test NN\\baked2.py\"", "-i", 
				"\"C:\\Users\\walkd\\Documents\\Eclipse Workspace\\Test2\\saved.png\"", "-c", 
				"\"C:\\Users\\walkd\\Documents\\GitHub\\NWAWP-Hand-Tracker\\test NN\\training_3\\cp.ckpt\"", };
		runBatch();
		int output = readBatch();
		System.out.println(output);
	}

	//returns the first line of the output file
	public int readBatch() throws IOException 
	{
		File output = new File("C:\\Users\\walkd\\Documents\\Eclipse Workspace\\Test2\\src\\output.txt");
		BufferedReader br = new BufferedReader(new FileReader(output)); 
		return Integer.parseInt(br.readLine());
	}

	//runs batch file
	public void runBatch() throws IOException
	{
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("cmd", "/c", "run.bat");
		processBuilder.directory(new File("C:\\Users\\walkd\\Documents\\GitHub\\Motion-Tracking-Interface\\"));
		Process process = processBuilder.start();
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

	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new GetHandImage(ImageIO.read(new File("src/_DSC4053_out.jpg")));
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
}