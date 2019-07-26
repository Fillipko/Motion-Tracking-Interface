package MyPackage;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound {
	
	public static void main (String[]args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		new Sound();
	}
	
	public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		File clap = new File("src/kine6 Theme.wav");
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(clap));
		clip.start();
	}
}
