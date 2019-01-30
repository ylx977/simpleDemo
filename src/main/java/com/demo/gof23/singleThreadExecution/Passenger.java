package com.demo.gof23.singleThreadExecution;

public class Passenger extends Thread {
	
	private final FlightSecurity flightSecurity;
	private final String idCard;
	private final String boardingCard;
	
	public Passenger(FlightSecurity flightSecurity,String idCard,String boardingCard) {
		this.flightSecurity = flightSecurity;
		this.boardingCard = boardingCard;
		this.idCard = idCard;
	}
	
	@Override
	public void run() {
		
		while(true){
			this.flightSecurity.pass(this.boardingCard,this.idCard);
		}
		
	}
	
	public static void main(String[] args) {
		final FlightSecurity flightSecurity = new FlightSecurity();
		new Passenger(flightSecurity, "A", "A").start();
		new Passenger(flightSecurity, "B", "B").start();
		new Passenger(flightSecurity, "C", "C").start();
	}

}
