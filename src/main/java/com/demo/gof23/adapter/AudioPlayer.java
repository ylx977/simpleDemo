package com.demo.gof23.adapter;

import java.util.Arrays;
import java.util.List;

public class AudioPlayer implements MediaPlayer{
	
	private List<String> types = Arrays.asList("mp3");

	private MediaPlayer adapter;
	
	public AudioPlayer(MediaPlayer adapter){
		this.adapter = adapter;
	}
	
	@Override
	public void play(String filename) {
		String[] split = filename.split("\\.");
		String type = split[split.length-1];
		if(this.match(type)){
			System.out.println("Audio播放器播放:"+filename+",文件类型为:"+type);
		}else if(adapter.match(type)){
			adapter.play(filename);
		}else{
			System.out.println("没有支持的格式");
		}
	}

	@Override
	public boolean match(String type) {
		return types.contains(type);
	}
	
	

}
