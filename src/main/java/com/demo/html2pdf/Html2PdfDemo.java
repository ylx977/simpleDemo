package com.demo.html2pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.pdf.PdfWriter;

public class Html2PdfDemo {
	
	public static final String HTML = "C:\\Users\\fuzamei\\Desktop\\haha.html";
	
	public static void main(String[] args) throws Exception {
		
//		Document document = new Document(PageSize.LETTER);
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\fuzamei\\Desktop\\testpdf.pdf"));
		document.open();
		document.addAuthor("test");
		document.addCreator("test");
		document.addSubject("test");
		document.addCreationDate();
		document.addTitle("XHTML to PDF");
		
		XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
		worker.parseXHtml(pdfWriter, document, new FileInputStream(HTML),  Charset.forName("UTF-8"),new AsianFontProvider());
		document.close();
		System.out.println("Done.");
	}
}
