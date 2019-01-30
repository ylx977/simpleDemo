package com.demo.classLoader;

public class Demo {

	static {
		System.out.println("(Demo.class) I will be loaded");
	}
	
	//main方法的启动会导致当前类的初始化---->1:main的加载导致类的初始化
	public static void main(String[] args) throws Exception {
		
		System.out.println("main start");
		
		System.out.println("开始使用A");

		Thread.sleep(5000);
		
//		new A();//--------------->2:通过new导致类的初始化
//		new SubA();
		
		//访问A的静态变量【会导致A的初始化】------------->3:访问类的静态变量
//		System.out.println("A的静态变量：" + A.x);
//		System.out.println("SubA父类的静态变量：" + SubA.x);//通过子类访问父类的的静态变量只会导致父类A的初始化
//		System.out.println("SubA的静态变量：" + SubA.subx);//子类的静态变量会导致父类和子类初始化
		
		//访问A复杂的静态常量【会导致A的初始化】------------->4:访问类的复杂静态常量
//		System.out.println("A的静态常量" + A.I);
//		System.out.println("SubA的静态常量" + SubA.I);//通过子类访问父类静态复杂常量会导致父类的初始化，不会导致子类的初始化
//		System.out.println("SubA的静态常量" + SubA.subI);//访问子类的复杂静态常量会导致父子类均初始化
		
		//访问A的静态方法【会导致A的初始化】------------->5:访问类的静态方法
//		A.test();
//		SubA.test();//通过子类访问父类的静态方法只会导致父类的初始化，子类不会初始化
//		SubA.subtest();//子类的静态方法的访问会导致子类和父类均被初始化
		
		//通过反射访问A【会导致A的初始化】-------------->6:反射
//		Class.forName("com.demo.classLoader.A");
//		Class.forName("com.demo.classLoader.SubA");//父类和子类均被初始化
		
		
		//================================以上都是类的主动使用==============================
		
		
		//================================以下是类的被动使用================================
		
//		A[] as = new A[10];//1---->数组的初始化不会导致类的初始化
		
		//访问A简单的静态常量【不会导致A的初始化】----->2：简单静态常量的访问不会导致类的初始化
//		System.out.println("A的静态常量" + A.F);
//		System.out.println("SubA的静态常量" + SubA.F);//通过子类访问父类的的静态常量不会导致父类A的初始化和SubA的初始化
//		System.out.println("SubA的静态常量" + SubA.subF);//简单的静态常量访问不会导致子类或者父类的初始化
	}
	
}
