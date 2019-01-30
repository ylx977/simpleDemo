package com.demo.gof23.adapter;

import java.util.Arrays;
import java.util.List;

public class VlcPlayer implements AdvancedMediaPlayer {
	
	private VlcPlayer(){}
	
	private static VlcPlayer vlcPlayer;
	
	public static final VlcPlayer getInstance(){
		if(vlcPlayer == null){
			synchronized (VlcPlayer.class) {
				if(vlcPlayer == null){
					vlcPlayer = new VlcPlayer();
					return vlcPlayer;
				}
			}
		}
		return vlcPlayer;
	}
	
	private String[] types = {"vlc","mp3"};
	
	public List<String> getTypes(){
		return Arrays.asList(types);
	}
	
	@Override
	public void play(String filename) {
		String[] split = filename.split("\\.");
		String type = split[split.length-1];
		System.out.println("使用VlcPlayer播放:"+filename+",文件类型为:"+type);
	}

}
