package com.demo.gof23.adapter;

/**
 * 适配器模式
 * 参考http://www.runoob.com/design-pattern/adapter-pattern.html
 * 但是做了一些修改
 * AdvancedMediaPlayer的两个实例做成单例
 * 适配器可以getInstance多个实例
 * 而AudioPlayer也可以多个实例
 * 考虑到适配器今后有可能会因为有其它的AdvancedMediaPlayer出现，所以这里为了做灵活，没有做成单例
 * 而且AudioPlayer中的适配器也是用接口形式表达，这样可以在创建AudioPlayer实例的时候可以灵活选择不同款式的适配器
 * @author fuzamei
 *
 */
public class Client {
	
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer(MediaPlayerAdapter.getInstance());
		audioPlayer.play("haha.mp3");
		audioPlayer.play("haha.mp4");
		audioPlayer.play("haha.vlc");
		audioPlayer.play("haha.mp5");
		audioPlayer.play("haha.avi");
		
	}
	
}
