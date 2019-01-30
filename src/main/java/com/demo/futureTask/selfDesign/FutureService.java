package com.demo.futureTask.selfDesign;

public interface FutureService<IN,OUT> {
	
	//提交不需要返回值的任务，Future.get方法返回的将会是null
	Future<?> sumbit(Runnable runnable);
	
	//提交需要返回值的任务，其中task接口代替了Runnable接口
	Future<OUT> submit(Task<IN,OUT> task, IN input);
	
	//使用静态方法创建一个FutureService的实现
	static <T,R> FutureService<T, R> newService(){
		return new FutureServiceImpl<>();
	}
}
