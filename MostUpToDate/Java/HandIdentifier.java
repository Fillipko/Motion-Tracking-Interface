package MyPackage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
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
		xml = new String[]{"xml/closed.xml", "xml/open.xml"};
		for(int i = 0; i < xml.length; i++)
		{
<<<<<<< HEAD
			CascadeClassifier cc = new CascadeClassifier(xml[i]);
=======
			System.out.println(xml[i]);
			CascadeClassifier cc = new CascadeClassifier("src/" + xml[i] + ".xml");
>>>>>>> f164932645914c73d93804cd65da06bc3347928a
			MatOfRect handDetection = new MatOfRect();
			cc.detectMultiScale(mat, handDetection, 1.1, 4, 0, new Size(40.0, 40.0));
			//cc.detectMultiScale(mat, handDetection);
			numOfFoundHands = Integer.parseInt(String.format("%d", handDetection.toArray().length));
			for (Rect rect : handDetection.toArray()) {
				Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 250), rectThickness);
<<<<<<< HEAD
				count++;
			}

=======
			}
>>>>>>> f164932645914c73d93804cd65da06bc3347928a
		}
		return mat;
	}

	public int getThickness()
	{
		return rectThickness;
	}

}