package MyPackage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class HandIdentifier {
	private int rectThickness = 3;
	public int numOfFoundHands = 0;
	private int count = 0;
	private String[] xml;

	static 
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public Mat FindFace(Mat mat) 
	{
		xml = new String[]{ "cascadeFist", "cascadeOpen"};
		for(int i = 0; i < xml.length; i++)
		{
			System.out.println(xml[i]);
			CascadeClassifier cc = new CascadeClassifier("src/" + xml[i] + ".xml");
			MatOfRect handDetection = new MatOfRect();
			cc.detectMultiScale(mat, handDetection);
			numOfFoundHands = Integer.parseInt(String.format("%d", handDetection.toArray().length));
			for (Rect rect : handDetection.toArray()) {
				Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 250), rectThickness);
			}
		}
		return mat;
	}

	public int getThickness()
	{
		return rectThickness;
	}

}