package graphic;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundsProvider {

	private static AudioInputStream audio;
	private static File paused;
	private static Clip pausedClip;
	private static File play;
	private static Clip playClip;
	private static File ball;
	public static Clip ballClip;
	private static File explosion;
	public static Clip explosionClip;
	private static File drop;
	public static Clip dropClip;

	
	static {
		
		paused = new File("sounds/pause.wav");
		play = new File("sounds/play.wav");
		drop = new File("sounds/drop.wav");
		ball = new File("sounds/ball.wav");
		explosion = new File("sounds/explosion.wav");

	}
	
	static {
		
		try {
			
			audio =  AudioSystem.getAudioInputStream(paused);
			pausedClip = AudioSystem.getClip();
			pausedClip.open(audio);
			
			audio =  AudioSystem.getAudioInputStream(play);
			playClip = AudioSystem.getClip();
			playClip.open(audio);
			
			audio =  AudioSystem.getAudioInputStream(ball);
			ballClip = AudioSystem.getClip();
			ballClip.open(audio);
			
			audio =  AudioSystem.getAudioInputStream(drop);
			dropClip = AudioSystem.getClip();
			dropClip.open(audio);
			
			audio =  AudioSystem.getAudioInputStream(explosion);
			explosionClip = AudioSystem.getClip();
			explosionClip.open(audio);

		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void playPaused() {
		pausedClip.setFramePosition(0);
		pausedClip.start();
	}

	public static void playMusic() {
		playClip.start();
		playClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public static void playBall() {
		if(ballClip.getFramePosition() == 0){
			ballClip.setFramePosition(0);
			ballClip.start();
		}
	}
	public static void playDrop() {
		dropClip.setFramePosition(0);
		dropClip.start();
	}
	public static void playExplosion() {
		explosionClip.setFramePosition(0);
		explosionClip.start();
	}
}