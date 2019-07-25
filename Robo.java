package MyPackage;
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
import javax.swing.JPanel;

import java.awt.Robot; 

public class Robo
{ 
	private Robot robot;

	public Robo() throws InterruptedException{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}	
	
	
	//alt tabs to the right
	public void altTab()
	{
		robot.keyPress(KeyEvent.VK_ALT); 
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT); 
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	//alt tabs to the left
	public void shiftAltTab()
	{
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_ALT); 
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	public void zoomIn() { 
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_PLUS);
		robot.keyPress(KeyEvent.VK_PLUS);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_PLUS);
		robot.keyRelease(KeyEvent.VK_PLUS);
	}
	
	public void zoomOut()
	{
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_MINUS);
	}
	
	public void scrollDown()
	{
		for(int i = 0; i < 3; i++)
		{
			robot.mouseWheel(2);
			try 
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void scrollUp()
	{
		for(int i = 0; i < 3; i++)
		{
			robot.mouseWheel(-2);
			try 
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Robo robo = new Robo();
		Thread.sleep(500);
		robo.scrollUp();
		Thread.sleep(500);
		robo.scrollDown();		
	}
}