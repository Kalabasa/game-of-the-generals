package gg;

// Sound class adapted from Metagun source code
// Ⓒ Markus Persson

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.*;

public class Sound {

	private static boolean muted = false;
	
	public static void toggleMute(){
		muted = !muted;
	}
	
	public static class Clips {
		public Clip[] clips;
		private int p;
		private int count;

		public Clips(byte[] buffer, int count) throws LineUnavailableException,
				IOException, UnsupportedAudioFileException {
			if (buffer == null)
				return;

			clips = new Clip[count];
			this.count = count;
			for (int i = 0; i < count; i++) {
				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem
						.getAudioInputStream(new ByteArrayInputStream(buffer)));
			}
		}
		
		public void play(){
			play(1, 0);
		}

		public void play(float volume, float pan) {
			if (clips == null || muted)
				return;

			p++;
			if (p >= count)
				p = 0;
			clips[p].stop();

			try{
				FloatControl gainControl = (FloatControl) clips[p].getControl(FloatControl.Type.MASTER_GAIN);
				float max = gainControl.getMaximum();
				float min = gainControl.getMinimum();
				float g = min + (max - min) * (float) Math.pow(volume, 0.5);
				gainControl.setValue(Math.max(min, Math.min(g, max)));
			}catch(IllegalArgumentException e){
			}
			
			try{
				FloatControl panControl = (FloatControl) clips[p].getControl(FloatControl.Type.PAN);
				float max = panControl.getMaximum();
				float min = panControl.getMinimum();
				panControl.setValue(Math.max(min, Math.min(pan, max)));
			}catch(IllegalArgumentException e){
				try{
					FloatControl balanceControl = (FloatControl) clips[p].getControl(FloatControl.Type.BALANCE);
					float max = balanceControl.getMaximum();
					float min = balanceControl.getMinimum();
					balanceControl.setValue(Math.max(min, Math.min(pan, max)));
				}catch(IllegalArgumentException e2){
					System.out.println("Can't pan sound!");
				}
			}

			clips[p].setFramePosition(0);
			clips[p].start();
		}

		public void stop() {
			clips[p].stop();
		}
		
		public void stopAll(){
			for(Clip c : clips){
				c.stop();
			}
		}
	}
	
	public static void stopAll(){
		for(Clips c : allClipses){
			c.stopAll();
		}
	}
	
	private static List<Clips> allClipses = new LinkedList<>();

	public static Clips chime = load("chime.wav", 2);
	public static Clips chime2 = load("chime2.wav", 2);
	public static Clips click = load("click.wav", 2);
	public static Clips fanfare = load("fanfare.wav", 2);
	public static Clips pop = load("pop.wav", 2);

	private static Clips load(String name, int count) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataInputStream dis = new DataInputStream(
					Sound.class.getResourceAsStream("/" + name));
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = dis.read(buffer)) >= 0) {
				baos.write(buffer, 0, read);
			}
			dis.close();

			byte[] data = baos.toByteArray();
			Clips clips = new Clips(data, count);
			allClipses.add(clips);
			return clips;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return new Clips(null, 0);
			} catch (Exception ee) {
				ee.printStackTrace();
				return null;
			}
		}
	}

	public static void touch() {
	}
}
