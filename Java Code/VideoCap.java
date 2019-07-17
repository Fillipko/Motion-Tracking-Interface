import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import java.awt.image.BufferedImage;

public class VideoCap 
{
	static
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	VideoCapture capture;
	Mat mat2image = new Mat();
	
	public VideoCap()
	{
		capture = new VideoCapture();
		capture.open(0);
	}
	
	BufferedImage getOneFrame()
	{
		capture.read(mat2image.mat);
		return mat2image.getImage(mat2image.mat);
	}
}
