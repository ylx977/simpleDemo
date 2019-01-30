package com.concurrency.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FlightQueryTask extends Thread implements FlightQuery{

	@Override
	public List<String> get() {
		return this.flightList;
	}

	private final String origin;
	private final String destination;
	private final List<String> flightList = new ArrayList<>();
	public FlightQueryTask(String airline,String origin,String destination){
		super("[" + airline + "]");
		this.origin  = origin;
		this.destination  = destination;
	}
	
	@Override
	public void run() {
		System.out.printf("%s-query from %s to %s \n",getName(),origin,destination);
		int randomVal = ThreadLocalRandom.current().nextInt(10);
		try {
			TimeUnit.SECONDS.sleep(randomVal);
			this.flightList.add(getName() + "-" + randomVal);
			System.out.printf("The flight: %s list query successful\n",getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static List<String> flightCompany = Arrays.asList("CSA","CEA","HNA");
	public static void main(String[] args) {
		List<String> results = new ArrayList<>();
		List<FlightQueryTask> threads = flightCompany.stream().map(f->new FlightQueryTask(f, "SH", "BJ")).collect(Collectors.toList());
		
		//所有线程开启
		threads.forEach(Thread::start);
		
		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		threads.stream().map(FlightQueryTask::get).forEach(results::addAll);
		
		System.out.println("=======result======");
		
		results.forEach(System.out::println);
	}
}
