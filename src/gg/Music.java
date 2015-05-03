package gg;

import java.io.DataInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Music {
	
	private static AdvancedPlayer player = null;
	private static String currentFilename = null;
	private static boolean muted = false;
	
	public static void toggleMute(){
		if(!muted){
			muted = true;
			stop();
		}else{
			muted = false;
			if(currentFilename != null){
				String filename = currentFilename;
				currentFilename = null;
				play(filename);
			}
		}
	}

	public static void play(String filename){
		if(!muted && !filename.equals(currentFilename)){
			currentFilename = filename;
			DataInputStream dis = new DataInputStream(
					Sound.class.getResourceAsStream("/" + filename));
			stop();
			try {
				player = new AdvancedPlayer(dis);
			} catch (JavaLayerException e1) {
				e1.printStackTrace();
			}
			new Thread(){
				public void run() {
					try {
						player.play();
					} catch (JavaLayerException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
	}
	
	public static void stop(){
		if(player != null){
			player.close();
			player = null;
		}
	}

}
