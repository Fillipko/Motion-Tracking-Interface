package MyPackage;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
import java.awt.image.*;

public class VideoCap
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
        return mat2img.getImage(mat2img.getMat());
	}
}
