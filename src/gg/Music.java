package gg;

import java.io.DataInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Music {
	
	private static AdvancedPlayer player = null;

	public static void play(String name){
		DataInputStream dis = new DataInputStream(
				Sound.class.getResourceAsStream("/" + name));
		if(player != null){
			player.stop();
			player.close();
		}
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
