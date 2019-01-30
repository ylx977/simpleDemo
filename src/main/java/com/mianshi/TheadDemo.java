package com.mianshi;

public class TheadDemo {

	public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	System.out.println("t方法1");
                	System.out.println("t方法2");
                	System.out.println("t方法3");
                    Thread.sleep(2000);
                    System.out.println("t方法4");
                    System.out.println("t方法5");
                    System.out.println("t方法6");
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                System.out.print("2");
            }
        });
        System.out.println("main方法啦1");
        t.start();
        System.out.println("main方法啦2");
        t.join();//有了join就相当于我t一定要执行完，你main才能执行
        System.out.println("main方法啦3");
        System.out.println("main方法啦4");
        System.out.println("main方法啦5");
        System.out.println("main方法啦6");
    }


}
