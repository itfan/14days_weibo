package com.lxt008.onlinemp3;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayMusic {
	
	private Context context = null;
	private MediaPlayer mediaPlayer = null;
	
	public PlayMusic( Context context){
		this.context = context;
		mediaPlayer = new MediaPlayer();
	}
	
	public void stopMusic(){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
	}
	
	public void playMusic(String url){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.reset();
		}
		try {
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
