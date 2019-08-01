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

public class FaceIdentifier {
	public int numOfFoundFaces = 0;
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	public Mat FindFace(Mat mat) {
		String xmlFileName = "xml/aGest.xml";
		CascadeClassifier cc = new CascadeClassifier(xmlFileName);
		MatOfRect faceDetection = new MatOfRect();
		cc.detectMultiScale(mat, faceDetection);
		numOfFoundFaces = Integer.parseInt(String.format("%d", faceDetection.toArray().length));
		for (Rect rect : faceDetection.toArray()) {
			Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 250), 3);
		}
		return mat;
	}
}