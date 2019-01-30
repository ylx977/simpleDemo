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

public class ThumbnailDemo {
	
	/**
	 * 使用给定的图片生成指定大小的图片
	 */
	@Test
	public void generateFixedSizeImage(){
		try {
			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\pic.jpg")
			.size(140, 204)
			.toFile("C:\\Users\\fuzamei\\Desktop\\pic2.jpg");
//			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\pic.jpg").toFile("C:\\Users\\fuzamei\\Desktop\\pic3.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	/**
     * 对原图加水印,然后顺时针旋转90度,最后压缩为80%保存
	 */
	@Test
	public void generateRotationWatermark(){
		try {
			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\water.jpg")
			.size(1024, 768)
			.rotate(90)
			.watermark(Positions.CENTER, ImageIO.read(new File("C:\\Users\\fuzamei\\Desktop\\pic2.jpg")), 0.5f)
			.outputQuality(0.8)
			.toFile("C:\\Users\\fuzamei\\Desktop\\water2.jpg");;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取图片的长度和宽度
	 */
	@Test
	public void getSizeOfImage(){
		try {
			BufferedImage bImage = ImageIO.read(new File("C:\\Users\\fuzamei\\Desktop\\water.jpg"));
			int width = bImage.getWidth();
			int height = bImage.getHeight();
			System.out.println("图片高度为："+height);
			System.out.println("图片宽度为："+width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将图片写入到输出流中去
	 */
	@Test
	public void generateOutputstream(){
		try {
			OutputStream outputStream = new FileOutputStream("C:\\Users\\fuzamei\\Desktop\\water.bmp");
			//转换格式，同时写入输出流
			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\water.jpg")
			.size(1024, 768)
			.outputFormat("bmp")
			.toOutputStream(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按照比例对图片进行缩放
	 */
	@Test
	public void generateScale(){
		try {
			Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\water.jpg")
			.scale(0.5)//缩放，不能和size一起使用
			.outputQuality(1)//图片质量
			.toFile("C:\\Users\\fuzamei\\Desktop\\water3.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 生成缩略图到指定的目录
     */
	@Test
    public void generateThumbnail2Directory(){
        try {
            Thumbnails.of("C:\\Users\\fuzamei\\Desktop\\water.jpg").
                    //scalingMode(ScalingMode.BICUBIC).
                            scale(0.8). // 图片缩放80%, 不能和size()一起使用
                   toFiles(new File("C:\\Users\\fuzamei\\Desktop\\haha"), Rename.NO_CHANGE);//指定的目录一定要存在,否则报错
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }
	
	/**
     * 将指定目录下所有图片生成缩略图
     */
	@Test
    public void generateDirectoryThumbnail(){
        try {
            Thumbnails.of(new File("C:\\Users\\fuzamei\\Desktop").listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isFile()&&pathname.getName().endsWith("jpg");
				}
			})).
                    //scalingMode(ScalingMode.BICUBIC).
                            scale(0.8). // 图片缩放80%, 不能和size()一起使用
                    toFiles(new File("C:\\Users\\fuzamei\\Desktop\\haha"), Rename.SUFFIX_HYPHEN_THUMBNAIL);//指定的目录一定要存在,否则报错
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }
	
	@Test
	public void test01() throws IOException{
		URL url=new URL("http://img.zcool.cn/community/01690955496f930000019ae92f3a4e.jpg");
		URLConnection connection=url.openConnection();
//		connection.setRequestProperty("accept", "*/*");
//		connection.setRequestProperty("connection", "Keep-Alive");
//		connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//		connection.setDoOutput(true);
//		connection.setDoInput(true);
		InputStream inputStream = connection.getInputStream();
//		BufferedImage read = ImageIO.read(url);
//		int width = read.getWidth();
//		int height = read.getHeight();
		Thumbnails.of(url).scale(0.5,0.5).toFile(new File("C:\\Users\\fuzamei\\Desktop\\what1.jpg"));
	}
	
	@Test
	public void test02() throws IOException{
		URL url=new URL("http://127.0.0.1:8090/ccb_fund_trusteeship/AccountMaintenance/downloadAccountlFile?url=/pic.jpg&attachmentId=111&userId=123");
		System.out.println(1);
		URLConnection connection=url.openConnection();
		System.out.println(2);
		InputStream inputStream = connection.getInputStream();
		System.out.println(3);
		byte[] bs = new byte[1024];
		byte[] buf = new byte[0];
		int len = -1;
		while((len=inputStream.read(bs))!=-1){
			buf = Arrays.copyOf(buf, buf.length+len);
			System.arraycopy(bs, 0, buf, buf.length-len, len);
		}
		Encoder encoder = Base64.getEncoder();
		String originalstr = encoder.encodeToString(buf);
		System.out.println(originalstr.length());
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);
		System.out.println(4);
		Thumbnails.of(bais).scale(0.5,0.5).outputQuality(0.1).toFile(new File("C:\\Users\\fuzamei\\Desktop\\test4.jpg"));
		
		ByteArrayInputStream bais2 = new ByteArrayInputStream(buf);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		Thumbnails.of(bais2).scale(0.5,0.5).outputQuality(0.1).toOutputStream(baos);
		byte[] byteArray = baos.toByteArray();
		String laterstr = encoder.encodeToString(byteArray);
		System.out.println(laterstr.length());
		System.out.println(5);
//		inputStream.close();
	}
	
	@Test
	public void test04() throws IOException{
		URL url=new URL("http://127.0.0.1:8090/ccb_fund_trusteeship/AccountMaintenance/downloadAccountlFile?url=/pic.jpg&attachmentId=111&userId=123");
		System.out.println(1);
		URLConnection connection=url.openConnection();
		System.out.println(2);
		InputStream inputStream = connection.getInputStream();
		System.out.println(3);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Thumbnails.of(inputStream).scale(0.5,0.5).outputQuality(0.1).toOutputStream(baos);
		FileOutputStream fos = new FileOutputStream("C:\\Users\\fuzamei\\Desktop\\test4.jpg");
		System.out.println(baos.toByteArray().length);
		baos.writeTo(fos);
	}
	
	@Test
	public void test03(){
		byte[] b1=new byte[]{1,2,3,4};
		byte[] b2=new byte[]{5,6,7,8};
		b2=Arrays.copyOf(b2, b2.length+b1.length);
		System.arraycopy(b1, 0, b2, b2.length-b1.length, b1.length);
		System.out.println(Arrays.toString(b2));
	}
	
	
}
