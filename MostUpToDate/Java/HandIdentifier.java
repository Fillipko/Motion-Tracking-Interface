package MyPackage;
import java.awt.AWTException;
import java.awt.Image;
import java.io.IOException;

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
	private String[] xml;
	private GetHandImage getHand;
	public int[] in = new int[] {1, 2};

	static
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public HandIdentifier() {
		try {
			getHand = new GetHandImage(in);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Mat findHand(Mat mat)
	{
		xml = new String[]{"closed", "open"};
		int[] tolerance = new int[]{4, 4};
		int counter = 0;
		for(int i = 0; i < xml.length; i++)
		{
			try {
				CascadeClassifier cc = new CascadeClassifier("xml/" + xml[i] + ".xml");
				MatOfRect handDetection = new MatOfRect();
				cc.detectMultiScale(mat, handDetection, 1.1, tolerance[i], 0, new Size(40.0, 40.0));
				numOfFoundHands = Integer.parseInt(String.format("%d", handDetection.toArray().length));
				for (Rect rect : handDetection.toArray()) {
					Imgproc.rectangle(mat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 250), rectThickness);
					counter++;
					if(counter > 0 && i == 0) {
//						System.out.println("closed");
						getHand.update(0);
					}
					if(counter > 0 && i == 1) {
//-						System.out.println("open");
						getHand.update(1);
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return mat;
	}
}