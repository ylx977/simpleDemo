package com.demo.gof23.singleThreadExecution;

public class FlightSecurity {

	private int count = 0;
	
	private String boardingPass = "null";
	private String idCard = "null";
	
	public /*synchronized*/ void pass(String boardingPass, String idCard){
		this.boardingPass = boardingPass;
		this.idCard = idCard;
		this.count ++;
		check();
	}

	private void check() {
		if(this.boardingPass.charAt(0) != this.idCard.charAt(0)){
			throw new RuntimeException("========Exception========" + toString());
		}
	}
	
	@Override
	public String toString() {
		return "The" + count +" passenger,boardingPass [" + boardingPass + " ],idCard [" + idCard +" ]";
	}
}
