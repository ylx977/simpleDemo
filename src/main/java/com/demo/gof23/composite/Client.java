package com.demo.gof23.composite;

public class Client {
	
	public static void printAll(Employee e,int count){
		
		String temp = "";
		for(int i = 0; i < count; i++){
			temp += "\t";
		}
		
		if(e.getSubordinates().size() == 0){
			System.out.println(temp + e);
		}else{
			System.out.println(temp + e);
			for(Employee sub : e.getSubordinates()){
				printAll(sub,count+1);
			}
		}
	}

	public static void main(String[] args) {
		Employee CEO = new Employee("John","CEO", 30000);
		 
	    Employee headSales = new Employee("Robert","Head Sales", 20000);
	 
	    Employee headMarketing = new Employee("Michel","Head Marketing", 20000);
	 
	    Employee clerk1 = new Employee("Laura","Marketing", 10000);
	    Employee clerk2 = new Employee("Bob","Marketing", 10000);
	 
	    Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
	    Employee salesExecutive2 = new Employee("Rob","Sales", 10000);
	 
	    CEO.add(headSales);
	    CEO.add(headMarketing);
	 
	    headSales.add(salesExecutive1);
	    headSales.add(salesExecutive2);
	 
	    headMarketing.add(clerk1);
	    headMarketing.add(clerk2);
	    printAll(CEO, 0);
	}

}
