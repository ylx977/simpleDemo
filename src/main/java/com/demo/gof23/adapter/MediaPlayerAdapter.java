package com.demo.gof23.adapter;

public class MediaPlayerAdapter implements MediaPlayer {

	private Mp4Player advancedMediaPlayer1;
	private VlcPlayer advancedMediaPlayer2;
	
	private MediaPlayerAdapter() {
		this.advancedMediaPlayer1 = Mp4Player.getInstance();
		this.advancedMediaPlayer2 = VlcPlayer.getInstance();
	}
	
	public static MediaPlayerAdapter getInstance(){
		return new MediaPlayerAdapter();
	}
	
	@Override
	public void play(String filename) {
		String[] split = filename.split("\\.");
		String type = split[(split.length)-1];
		if(advancedMediaPlayer1.getTypes().contains(type)){
			advancedMediaPlayer1.play(filename);
			return;
		}
		if(advancedMediaPlayer2.getTypes().contains(type)){
			advancedMediaPlayer2.play(filename);
			return;
		}
	}

	@Override
	public boolean match(String type) {
		if(advancedMediaPlayer1.getTypes().contains(type)){
			return true;
		}
		if(advancedMediaPlayer2.getTypes().contains(type)){
			return true;
		}
		return false;
	}

}
