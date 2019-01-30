package com.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;

public class Demo {
	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(
				"https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		request.setBizContent("{" +
		"\"trade_no\":\"20150320010101001\"," +
		"\"out_trade_no\":\"2014112611001004680073956707\"," +
		"\"out_request_no\":\"2014112611001004680073956707\"," +
		"\"org_pid\":\"2088101117952222\"" +
		"  }");
		AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
		
	}
}
