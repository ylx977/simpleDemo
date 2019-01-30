package com.demo.thumbnailTest;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

public class ThumbnailDemo2 {
	
	/**
	 * 使用给定的图片生成指定大小的图片
	 */
	@Test
	public void generateFixedSizeImage(){
		try {
			BufferedImage bImage = ImageIO.read(new File("C:\\Users\\fuzamei\\Desktop\\pic.jpg"));
			int width = bImage.getWidth();
			int height = bImage.getHeight();
			System.out.println("图片高度为："+height);
			System.out.println("图片宽度为："+width);
			
			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\pic.jpg")
			.size(width, height)
			.outputQuality(0.5d)
			.toFile("C:\\Users\\fuzamei\\Desktop\\pic2.jpg");
//			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\pic.jpg").toFile("C:\\Users\\fuzamei\\Desktop\\pic3.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	
}
