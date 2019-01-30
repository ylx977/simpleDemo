package com.demo.gof23.filter_criteria;

import java.util.ArrayList;
import java.util.List;

import com.demo.gof23.filter_criteria.cirteria.AndCriteria;
import com.demo.gof23.filter_criteria.cirteria.FemaleCriteria;
import com.demo.gof23.filter_criteria.cirteria.MaleCriteria;
import com.demo.gof23.filter_criteria.cirteria.SingleCriteria;

public class Client {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		 
		persons.add(new Person("Robert","Male", "Single"));
		persons.add(new Person("John","Male", "Married"));
		persons.add(new Person("Laura","Female", "Married"));
		persons.add(new Person("Diana","Female", "Single"));
		persons.add(new Person("Mike","Male", "Single"));
		persons.add(new Person("Bobby","Male", "Single"));
		
		FemaleCriteria instance = FemaleCriteria.getInstance();
		MaleCriteria instance2 = MaleCriteria.getInstance();
		SingleCriteria instance3 = SingleCriteria.getInstance();
//		List<Person> meetCriteria = FemaleCriteria.getInstance().meetCriteria(persons);
//		List<Person> meetCriteria = MaleCriteria.getInstance().meetCriteria(persons);
//		List<Person> meetCriteria = SingleCriteria.getInstance().meetCriteria(persons);
		List<Person> meetCriteria = AndCriteria.getInstance(instance, instance3).meetCriteria(persons);
		printAll(meetCriteria);
	}
	
	
	
	public static void printAll(List<Person> lists){
		lists.stream().forEach(x->System.out.println(x));
	}

}
