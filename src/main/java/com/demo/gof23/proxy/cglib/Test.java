package com.demo.gof23.proxy.cglib;

public class Test {
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		UserDao proxyUserDao = (UserDao)new UserDaoInterceptor(userDao).getProxyInstance();
		proxyUserDao.save();
		
	}
}
