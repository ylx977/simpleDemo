package com.demo.httprequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.mock.web.MockMultipartFile;

import com.alibaba.fastjson.JSON;

public class HttpRequestDemo {
	
	/*
	 * 模拟文件和字段上传，以表单形式上传
	 */
	public static void main(String[] args) throws Exception {
		Map<String, String> map=new LinkedHashMap<>();
		map.put("businessName", "新业务名");
		map.put("userId", "1005");
		
		Map<String, MockMultipartFile> fileMap = new LinkedHashMap<>();
		MockMultipartFile file1 = new MockMultipartFile("业务审核文件", new FileInputStream("C:\\Users\\fuzamei\\Desktop\\业务审核文件.docx"));
		fileMap.put("businessApproval", file1);
		MockMultipartFile file2 = new MockMultipartFile("尽职调查文件", new FileInputStream("C:\\Users\\fuzamei\\Desktop\\尽职调查文件.docx"));
		fileMap.put("responsibleInvestigation", file2);
		MockMultipartFile file3 = new MockMultipartFile("产品审核文件", new FileInputStream("C:\\Users\\fuzamei\\Desktop\\产品审核文件.docx"));
		fileMap.put("productApproval", file3);
		MockMultipartFile file4 = new MockMultipartFile("基本资料文件", new FileInputStream("C:\\Users\\fuzamei\\Desktop\\基本资料文件.docx"));
		fileMap.put("basicData", file4);
		
		final String BOUNDARY = java.util.UUID.randomUUID().toString();
		final String twoHyphens = "--";
		final String NEWLINE = "\r\n"; // 换行，或者说是回车
		URL url = new URL("http://localhost:8090/ccb_fund_trusteeship/business_declare/uploadBusinessMaterialsByBranchBank");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setConnectTimeout(20000);
		conn.setReadTimeout(20000);
		conn.setDefaultUseCaches(false);
		
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		for (Map.Entry<String, String> entry : map.entrySet()) {
			dos.writeBytes(twoHyphens + BOUNDARY + NEWLINE);
			dos.writeBytes("Content-Disposition: form-data; " + " name=\"" + entry.getKey() + "\""+ NEWLINE);
			dos.writeBytes(NEWLINE); // 空行，一定不能少，键和值之间有一个固定的空行
			dos.write(entry.getValue().getBytes("UTF-8")); // 将值写入
			dos.writeBytes(NEWLINE); //换行
		}
		
		for (Map.Entry<String, MockMultipartFile> entry : fileMap.entrySet()) {
			dos.writeBytes(twoHyphens + BOUNDARY + NEWLINE);
			dos.write(("Content-Disposition: form-data; "+" name=\"" + entry.getKey() + "\"; filename=\""+entry.getValue().getName()+".docx\""+ NEWLINE).getBytes("utf-8"));
			dos.writeBytes(NEWLINE); // 空行，一定不能少，键和值之间有一个固定的空行
			dos.write(entry.getValue().getBytes()); // 将值写入
			dos.writeBytes(NEWLINE); //换行
		}
		
		dos.writeBytes(twoHyphens + BOUNDARY + twoHyphens + NEWLINE); // 最后的分割线，与前面的有点不一样是前缀+分界线+前缀+换行，最后多了一个前缀
		dos.flush();
		dos.close();
		
		InputStream input = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		String line;
		StringBuilder sb=new StringBuilder();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		
		System.out.println(JSON.toJSONString(sb.toString(), true));
		
	}
}
