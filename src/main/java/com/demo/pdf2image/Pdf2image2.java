package com.demo.pdf2image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

public class Pdf2image2 {

	private static final String pdfPath = "C:\\Users\\fuzamei\\Desktop\\阿里代码规范.pdf";
	
	private static final String imagesDir = "C:\\Users\\fuzamei\\Desktop\\PDFIMAGES";
	
	public static void main(String[] args) throws Exception {
		Document document = new Document();
		document.setFile(pdfPath);
		float scale = 2.5f;// 缩放比例
		float rotation = 0f;// 旋转角度
		for (int i = 0; i < document.getNumberOfPages(); i++) {
			BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
					org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
			try {
				File file = new File(imagesDir + "\\" + i + ".png");
				ImageIO.write(image, "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			image.flush();
		}
		document.dispose();
	}


}
