import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.Robot; 

public class Robo 
{ 
	@SuppressWarnings("null")
	public static void main(String[] args) throws AWTException, InterruptedException
	{
		Robot robot = new Robot();
		robot.mouseMove(700,400); //move mouse
		int button = InputEvent.getMaskForButton(1);
		robot.mousePress(button);
		robot.mouseRelease(button);
		robot.keyPress(KeyEvent.VK_H); //type hello
		Thread.sleep(500); 
		robot.keyPress(KeyEvent.VK_E); 
		Thread.sleep(500); 
		robot.keyPress(KeyEvent.VK_L); 
		Thread.sleep(500); 
		robot.keyPress(KeyEvent.VK_L); 
		Thread.sleep(500); 
		robot.keyPress(KeyEvent.VK_O); 
		Thread.sleep(500); Robot r = new Robot();
		for(int i = 0; i < 20; i++){ //scroll
			r.mouseWheel(1);
			try{ Thread.sleep(50); }catch(InterruptedException e){}
		}
		Graphics g = null;
		ImageObserver ImageObserver = null;
		g.drawImage(Zoom(), 0, 0, 300, 300, ImageObserver); //zoom

	}
	public static Image Zoom() { try {
		Robot robot = new Robot();
		Point p = MouseInfo.getPointerInfo().getLocation();
		return robot.createScreenCapture(new Rectangle((int)p.getX(), (int) p.getY()));
	} catch (Exception e) { 
		System.err.println(e.getMessage()); 
	}
	return null; 

	}
}