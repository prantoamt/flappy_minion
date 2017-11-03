import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPanel {
	
	FlappyBird bird = new FlappyBird();
	
	
	//Game Over
	public static void hitSound(){
		try{	
			File sound = new File("die.wav");
			AudioInputStream ais =AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}
		catch(Exception e){
			System.out.println("Sound file loading error!");
		}
		
		
	}
	
	//Point
	public static void pointSound(){
		try{	
			File sound = new File("Congrats.wav");
			AudioInputStream ais =AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}
		
		catch(Exception e){
			System.out.println("Sound file loading error!");
		}
		
		
	}

}
