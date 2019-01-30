package com.demo.QRcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.mock.web.MockMultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 参考此博文https://blog.csdn.net/sanfye/article/details/45749139
 * 
 * 需要添加以下的jar包依赖
 * <!-- https://mvnrepository.com/artifact/com.google.zxing/core -->
	<dependency>
    	<groupId>com.google.zxing</groupId>
    	<artifactId>core</artifactId>
    	<version>3.3.0</version>
	</dependency>
 * 
 * @author fuzamei
 *
 */
public class EncodeTest {
	
	 private static final int BLACK = 0xFF000000;//用于设置图案的颜色 ==Color.BLACK.getRGB()
	 private static final int WHITE = 0xFFFFFFFF; //用于背景色  ==Color.WHITE.getRGB()
	
	
	public static void main(String[] args) throws WriterException, IOException {
//		String contents = "1FAr69dcXyxrd4DYPWQX1djUBucBi4SV9U"; // 二维码内容 
//		String contents = "http://101.132.117.146/group1/M00/00/00/rBP3EVsjM1mAcKlKAAIN6x0PjcI297.jpg"; // 二维码内容 
		String contents = "http://tcm.33.cn/static/download/"; // 二维码内容 
        int width = 600; // 二维码图片宽度 300   
        int height = 600; // 二维码图片高度300  
        
        String format = "gif";// 二维码的图片格式 gif  
        
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%） (公司比特元地址用的是L等级的)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
     // 内容所使用字符集编码  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");     
//      hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值  
//      hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值  
        hints.put(EncodeHintType.MARGIN, 4);//设置二维码边的空度，非负数 (边框空白的宽度越大空白处越大)
        
        
        //要编码的内容  
        //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,  
        //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,  
        //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,BarcodeFormat.QR_CODE,width, //条形码的宽度  
                height, //条形码的高度  
                hints);//生成条形码时的一些配置,此项可选 
     // 生成二维码  
        File outputFile = new File("C:\\Users\\fuzamei\\Desktop\\ADDRESS2.gif");//指定输出路径  
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y,  (bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));//bitMatrix.get(x, y)为true表示这个点要绘制什么颜色，如果是false则绘制白色
//              image.setRGB(x, y,  (matrix.get(x, y) ? Color.YELLOW.getRGB() : Color.CYAN.getRGB()));  
            }  
        }
        
        //*************************************绘制logo代码*********************************************
        /*BufferedImage logo = ImageIO.read(new File("C:\\Users\\fuzamei\\Desktop\\logo.jpg"));//读取log标志，如果不要的话后面的代码可以省略
        Graphics2D g = image.createGraphics();
        g.drawImage(logo, logo.getWidth()/8*2, logo.getHeight()/8*2, logo.getWidth()/8, logo.getHeight()/8, null);
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g.setStroke(stroke);// 设置笔画对象  
      //指定弧度的圆角矩形  
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(logo.getWidth()/8*2, logo.getWidth()/8*2, logo.getWidth()/8, logo.getWidth()/8,20,20);  
        g.setColor(Color.white);  
        g.draw(round);// 绘制圆弧矩形  
        
      //设置logo 有一道灰色边框  
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g.setStroke(stroke2);// 设置笔画对象  
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(logo.getWidth()/8*2+2, logo.getWidth()/8*2+2, logo.getWidth()/8-4, logo.getWidth()/8-4,20,20);  
        g.setColor(new Color(128,128,128));  
        g.draw(round2);// 绘制圆弧矩形 
        g.dispose();
*/      //*************************************绘制logo代码*********************************************
        
        
      //*************************************绘制字符*********************************************
        /*Graphics2D g = image.createGraphics();
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g.setColor(Color.BLACK);
        g.setStroke(stroke);
        g.drawString(contents, 20, 10);
        g.dispose();*/
      //*************************************绘制字符*********************************************
        
        image.flush();
        
        ImageIO.write(image, format, outputFile);
        System.out.println("成功");
	}
}
