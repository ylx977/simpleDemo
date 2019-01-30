package com.demo.fourRefs;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SoftRef {
	
	@Data
	@AllArgsConstructor
	@Builder
	@NoArgsConstructor
	static class Bean {
		private String name;
		private int age;
	}
	
	static void test01(){
		SoftReference<Bean> softBean = new SoftReference<SoftRef.Bean>(new Bean("name", 10));
		Bean bean = softBean.get();
		System.out.println(bean.getName());
		
		System.gc();//告诉垃圾收集器打算进行垃圾收集，而垃圾收集器进不进行收集是不确定的 
		System.runFinalization();//强制调用已经失去引用的对象的finalize方法 
		
		// 软引用所指向的对象会根据内存使用情况来决定是否回收，这里内存还充足，所以不会被回收。
		System.out.println(bean.getName());// “name:10”
	}
	
	/**
	 * JVM参数：-Xmx5m - Xms5m 
	 * 总结：在新开辟 100000 个 Bean对象时，由于软引用会视内存使用情况来判断是否自动回收，所以当最大 Heap 阈值达到 2m 时，系统自动回收最前面开辟的对象，取第 100 个对象时，返回为 null。
	 */
	public static void test02() {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Reference<Bean>[] referent = new SoftReference[100000];
		for (int i = 0; i < referent.length; i++) {
			referent[i] = new SoftReference<Bean>(new Bean("mybean:" + i, 100));
		}
		
		System.out.println(referent[100].get());// “null”
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		test01();
		test02();
	}

}
