package MyPackage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class HandIdentifier {
	private int rectThickness = 3;
	public int numOfFoundHands = 0;

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public Mat FindFace(Mat mat) {
		String xmlFileName = "xml/lbpcascade_frontalface_improved.xml";
		CascadeClassifier cc = new CascadeClassifier(xmlFileName);
		MatOfRect handDetection = new MatOfRect();
		cc.detectMultiScale(mat, handDetection);
		Point p = new Point();
		numOfFoundHands = Integer.parseInt(String.format("%d", handDetection.toArray().length));
		for (Rect rect : handDetection.toArray()) {
			Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 250), rectThickness);
		}
		return mat;
	}

	public int getThickness()
	{
		return rectThickness;
	}

}