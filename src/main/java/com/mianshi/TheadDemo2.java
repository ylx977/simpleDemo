package com.mianshi;

public class TheadDemo2 {

	public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	System.out.println("t方法1");
                	System.out.println("t方法2");
                    Thread.sleep(5000);
                    System.out.println("t方法4");
                    System.out.println("t方法5");
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                System.out.print("2");
            }
        });
        Thread t2 = new Thread(new Runnable() {
        	@Override
        	public void run() {
        		try {
        			System.out.println("t2方法1");
        			System.out.println("t2方法2");
        			Thread.sleep(5000);
        			System.out.println("t2方法4");
        			System.out.println("t2方法5");
        		} catch (InterruptedException e) {
        			throw new RuntimeException();
        		}
        		System.out.print("2");
        	}
        });
        System.out.println("main方法啦1");
        t.start();
        System.out.println("main方法啦2");
        t.join();//有了join就相当于我t一定要执行完，你main才能执行下面一行代码，join不要在start之前调用，是没有效果的
        t2.start();
        t2.join();//有了join就相当于我t一定要执行完，你main才能执行下面一行代码
        System.out.println("main方法啦3");
        System.out.println("main方法啦4");
        System.out.println("main方法啦5");
        System.out.println("main方法啦6");
        
        /**join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
         * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
         */
        
    }


}
