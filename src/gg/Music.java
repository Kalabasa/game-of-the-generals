package gg;

import java.io.DataInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Music {
	
	private static AdvancedPlayer player = null;
	private static String currentFilename = null;

	public static void play(String filename){
		if(!filename.equals(currentFilename)){
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
		}
	}

}
