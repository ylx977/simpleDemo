package com.demo.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveAction：无返回值
 * RecursiveTask：有返回值
 * @author fuzamei
 *
 */
public class CountTask extends RecursiveTask<Long>{

	private static final Long THRESHOLD = 10000L;
	private long start;
	private long end;
	
	public CountTask(long start,long end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute() {
		long sum = 0;
		boolean canCompute = (end-start) <= THRESHOLD;
		if(canCompute){
			for (long i = start; i <= end; i++) {
				sum += i;
			}
		}else{
			long step = (end-start)/100;//分成100个小任务
			ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
			long pos = this.start;
			for (int i = 0; i < 100; i++) {
				long lastOne = pos + step;
				if(lastOne>end){
					lastOne=end;//确保最后一个任务的末尾值不会超出
				}
				CountTask subTask = new CountTask(pos, lastOne);
				pos += step + 1;
				subTasks.add(subTask);
				subTask.fork();//推向线程池
			}
			for (CountTask countTask : subTasks) {
				sum += countTask.join();
			}
		}
		return sum;
	}
	
	
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(70);//fork join用的线程池
		CountTask countTask = new CountTask(100, 100000000);
		long start = System.currentTimeMillis();
		ForkJoinTask<Long> result = forkJoinPool.submit(countTask);
		try {
			Long sum = result.get();
			System.out.println(sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		long sum = 0;
//		for (int i = 100; i <= 100000000; i++) {
//			sum+=i;
//		}
//		
//		System.out.println(sum);
		System.out.println("5000000049995050");
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

}
