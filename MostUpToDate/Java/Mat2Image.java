package MyPackage;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class Mat2Image
{

    static
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private Mat mat = new Mat();
    private BufferedImage buffImg;
    private HandIdentifier handIdentifier = new HandIdentifier();

    public Mat2Image()
    {
    	handIdentifier.FindFace(mat);
    }

    public Mat2Image(Mat mat)
    {
        getSpace(handIdentifier.FindFace(mat));
    }

    public void getSpace(Mat mat)
    {
        int type = 0;
        if (mat.channels() == 1)
        {
            type = BufferedImage.TYPE_BYTE_GRAY;
        }
        else if (mat.channels() == 3)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        this.mat = mat;
        int w = mat.cols();
        int h = mat.rows();
        if (buffImg == null || buffImg.getWidth() != w || buffImg.getHeight() != h || buffImg.getType() != type)
        {
        	buffImg = new BufferedImage(w, h, type);
        }
    }

    BufferedImage getImage(Mat mat)
    {
        getSpace(handIdentifier.FindFace(mat));
        WritableRaster raster = buffImg.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        mat.get(0, 0, data);
        return buffImg;
    }

    public Mat getMat()
    {
    	return mat;
    }
}