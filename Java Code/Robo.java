package opencvtest2;
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

import javax.swing.JFrame;

import java.awt.Robot; 

public class Robo extends JFrame
{ 
	private Robot robot;
	public Robo() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Movemouse() throws InterruptedException {
		System.out.println("helllooo");
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
		System.out.println("helllooo");
	}
	public void Scroll() throws InterruptedException {
		Thread.sleep(500); 
		for(int i = 0; i < 20; i++){ //scroll
			robot.mouseWheel(1);
			try{ Thread.sleep(50); }catch(InterruptedException e){}
		}
	}
	public void paint(Graphics g) {
		g.drawImage(Zoom(), 0, 0, 300, 300, (ImageObserver)this);
	}
	public Image Zoom() { try {
		Point p = MouseInfo.getPointerInfo().getLocation();
		return robot.createScreenCapture(new Rectangle((int)p.getX(), (int) p.getY()));
	} catch (Exception e) { 
		System.err.println(e.getMessage()); 
	}
	return null; 

	}
}