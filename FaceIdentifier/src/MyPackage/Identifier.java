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

public class Identifier {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		String imgFileName = "Images/_DSC4053.jpg";
		Mat src = Imgcodecs.imread(imgFileName);

		String xmlFileName = "xml/lbpcascade_frontalface_improved.xml";
		CascadeClassifier cc = new CascadeClassifier(xmlFileName);

		MatOfRect faceDetection = new MatOfRect();
		cc.detectMultiScale(src, faceDetection);

		System.out.println(String.format("DetectedFaces: %d", faceDetection.toArray().length));
		for (Rect rect : faceDetection.toArray()) {
			Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 250), 3);
		}

		Imgcodecs.imwrite("Images/_DSC4053_out.jpg", src);
		System.out.println("Image Detection Finished");
	}
}
