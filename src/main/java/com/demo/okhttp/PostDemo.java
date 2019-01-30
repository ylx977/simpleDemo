package com.demo.okhttp;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import lombok.Builder;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostDemo {
	
	public static void main(String[] args) throws IOException {
		MediaType json = MediaType.parse("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		Bean bean = Bean.builder().accountName("18757198903").accountType(1).build();
		String jsonString = JSON.toJSONString(bean);
		
		RequestBody body = RequestBody.create(json, jsonString);
		
		Request request = new Request.Builder()
				.url("http://172.16.100.15:9003/contractchain/api/login/checkAccount")
				.post(body)
				.build();
		
		Response response = client.newCall(request).execute();
		if(response.isSuccessful()){
			System.out.println(response.body().string());
		}else{
//			System.out.println(response.body().string());
		}
	}
	
	
}


@Data
@Builder
class Bean{
	private String accountName;
	private Integer accountType;
}
