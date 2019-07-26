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

public class Robo extends Robot
{ 
	public Robo() throws AWTException
	{
	}	

	//alt tabs to the right
	public void altTab()
	{
		keyPress(KeyEvent.VK_ALT); 
		keyPress(KeyEvent.VK_TAB);
		keyRelease(KeyEvent.VK_ALT); 
		keyRelease(KeyEvent.VK_TAB);
	}

	//alt tabs to the left
	public void shiftAltTab()
	{
		keyPress(KeyEvent.VK_SHIFT);
		keyPress(KeyEvent.VK_ALT);
		keyPress(KeyEvent.VK_TAB);
		keyRelease(KeyEvent.VK_SHIFT);
		keyRelease(KeyEvent.VK_ALT); 
		keyRelease(KeyEvent.VK_TAB);
	}

	//zooms using windows magnifier
	public void zoomIn() { 
		keyPress(KeyEvent.VK_WINDOWS);
		keyPress(KeyEvent.VK_PLUS);
		keyPress(KeyEvent.VK_PLUS);
		keyRelease(KeyEvent.VK_WINDOWS);
		keyRelease(KeyEvent.VK_PLUS);
		keyRelease(KeyEvent.VK_PLUS);
	}

	//zooms out using windows magnifier
	public void zoomOut()
	{
		keyPress(KeyEvent.VK_WINDOWS);
		keyPress(KeyEvent.VK_MINUS);
		keyRelease(KeyEvent.VK_WINDOWS);
		keyRelease(KeyEvent.VK_MINUS);
	}

	//scrolls 2 mouse wheel ticks 3 times
	public void scrollDown()
	{
		for(int i = 0; i < 3; i++)
		{
			mouseWheel(2);
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

	//scrolls 2 mouse wheel ticks 3 times
	public void scrollUp()
	{
		for(int i = 0; i < 3; i++)
		{
			mouseWheel(-2);
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

	//puts computer to sleep
	public void sleep() throws InterruptedException
	{
		keyPress(KeyEvent.VK_WINDOWS);
		keyPress(KeyEvent.VK_X);
		keyRelease(KeyEvent.VK_X);
		keyRelease(KeyEvent.VK_WINDOWS);
		Thread.sleep(0, 1);
		keyPress(KeyEvent.VK_U);
		Thread.sleep(0, 1);
		keyPress(KeyEvent.VK_S);
		keyRelease(KeyEvent.VK_U);
		keyRelease(KeyEvent.VK_S);
		keyRelease(KeyEvent.VK_WINDOWS);
	}

//	public static void main(String[] args) throws InterruptedException, AWTException
//	{
//		Robo robo = new Robo();	
//		robo.sleep();
//		
//	}
}