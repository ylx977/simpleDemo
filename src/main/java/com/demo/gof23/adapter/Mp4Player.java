package com.demo.gof23.adapter;

import java.util.Arrays;
import java.util.List;

public class Mp4Player implements AdvancedMediaPlayer {
	
	private Mp4Player(){}
	
	private static Mp4Player mp4Player;
	
	public static final Mp4Player getInstance(){
		if(mp4Player == null){
			synchronized (Mp4Player.class) {
				if(mp4Player == null){
					mp4Player = new Mp4Player();
					return mp4Player;
				}
			}
		}
		return mp4Player;
	}
	
	private String[] types = {"mp4","mp3"};
	
	public List<String> getTypes(){
		return Arrays.asList(types);
	}
	
	@Override
	public void play(String filename) {
		String[] split = filename.split("\\.");
		String type = split[split.length-1];
		System.out.println("使用Mp4Player播放:"+filename+",文件类型为:"+type);
	}

}
