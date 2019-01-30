package com.demo.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CounTask1 extends RecursiveTask<Long>{
	
	private static final Long THRESHOLD = 20L;
	private long start;
	private long end;
	public CounTask1(long start,long end){
		this.start=start;
		this.end=end;
	}
	
	private static final long getSum(long start,long end){
		if(start==end){
			return start;
		}else{
			return end*getSum(start,end-1);
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool(20);
		CounTask1 task = new CounTask1(5,40);
		ForkJoinTask<Long> result = pool.submit(task);
		System.out.println(result.get());
		System.out.println(getSum(5,40));
	}



	@Override
	protected Long compute() {
		long sum = 1;
		if((end-start)<=THRESHOLD){
			sum*=getSum(start,end);
		}else{
			long step =(end-start)/5;
			List<CounTask1> list = new ArrayList<CounTask1>();
			long pos = this.start;
			for (int i = 0; i < 5; i++) {
				long endPos = pos+step;
				if(endPos>end){
					endPos = end;
				}
				CounTask1 task = new CounTask1(pos, endPos);
				pos += step+1;
				list.add(task);
				task.fork();
			}
			for (CounTask1 counTask1 : list) {
				sum *= counTask1.join();
			}
		}
		return sum;
	}
}
