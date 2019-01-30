package com.demo.fourRefs;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import com.demo.fourRefs.SoftRef.Bean;

/**
 * 弱引用所指向的对象只要进行 GC，就会自动进行回收，get() 返回 null。
 * 
 * @author jjs
 *
 */
public class WeakRef {
	
	/**
	 * 不需要设置vm参数，只要gc一回收对象就会被回收
	 * System.runFinalization()不调用也可以实现被回收
	 */
	public static void test1() {
		WeakReference<Bean> bean = new WeakReference<Bean>(new Bean("name", 10));
		System.gc();
		System.runFinalization();

		System.out.println(bean.get());// “null”
	}
	
	/**
	 * JVM参数：-Xmx5m - Xms5m 
	 * 
	 * 总结：WeakReference 与 SoftReference 具有相同的特性，也会视内存使用情况来判断是否自动回收。取第 100 个对象时，返回为 null。
	 */
	public static void test2() {
		Reference<Bean>[] referent = new WeakReference[100000];
		for (int i = 0; i < referent.length; i++) {
			referent[i] = new WeakReference<Bean>(new Bean("mybean:" + i, 100));
		}

		System.out.println(referent[100].get());// “null”
	}
	
	public static void main(String[] args) {
//		test1();
		test2();
	}

}
