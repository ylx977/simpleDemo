package com.demo.pdf2image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class Pdf2image {

	private static final String pdfPath = "C:\\Users\\fuzamei\\Desktop\\阿里代码规范.pdf";
	
	private static final String imagesDir = "C:\\Users\\fuzamei\\Desktop\\PDFIMAGES";
	
	public static void main(String[] args) {
		File file = new File(pdfPath);
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for(int i=0;i<pageCount;i++){
//				BufferedImage image = renderer.renderImageWithDPI(i, 296);
		     BufferedImage image = renderer.renderImage(i, 2.5f);
				ImageIO.write(image, "jpg", new File(imagesDir+"\\"+i+".jpg"));
				image.flush();
			}
		} catch (IOException e) {
				e.printStackTrace();
		}
	}


}
