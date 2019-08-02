package MyPackage;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.*;

public class VideoCap implements ImageObserver
{
	static
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private VideoCapture capture;
	private Mat2Image mat2img = new Mat2Image();

	public VideoCap()
	{
		capture = new VideoCapture();
		capture.open(0);
	}

	BufferedImage getOneFrame()
	{
		capture.read(mat2img.getMat());
		BufferedImage buff = mat2img.getImage(mat2img.getMat());
//CHECKSLEEP()
		int imgwidth = buff.getWidth(this);
		int imgheight = buff.getHeight(this);
		int tolerance = 25;
		Color mycolor = new Color(buff.getRGB(imgheight / 2, imgwidth / 2));
		int redCheck = mycolor.getRed();
		int blueCheck = mycolor.getBlue();
		int greenCheck = mycolor.getGreen();
		int count = 0;
		for(int y = 0; y < imgheight; y++)
		{
			for(int x = 0; x < imgwidth; x++)
			{
				mycolor = new Color(buff.getRGB(x, y));
				int red = mycolor.getRed();
				int blue = mycolor.getBlue();
				int green = mycolor.getGreen();
				if(!((red < redCheck + tolerance && red > redCheck - tolerance) &&
						(blue < blueCheck + tolerance && blue > blueCheck - tolerance) &&
						(green < greenCheck + tolerance && green > greenCheck - tolerance)))
				{
					count++;
				}
			}
		}
		if (count < 250) {
			System.exit(0);
			//robo.sleep();
		}
		return buff;
	}

	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
