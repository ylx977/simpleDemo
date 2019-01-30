package com.demo.baseTest;

import java.util.Scanner;

/**
 * swicth case可以使用大括号的
 * @author fuzamei
 *
 */
public class SwitchCaseTest {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("请输入一个数字");
		int nextInt = scanner.nextInt();
		
		switch (nextInt) {
		case 1 : {
			System.out.println("你输入的是1");
			break;
		}
		case 2 : {
			System.out.println("你输入的是2");
			break;
		}	
		case 3 : {
			System.out.println("你输入的是3");
			break;
		}	
		default:
			System.out.println("你输入的啥也不是");
			break;
		}
		scanner.close();
	}
	
}
