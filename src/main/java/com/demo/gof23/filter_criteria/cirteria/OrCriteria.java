package com.demo.gof23.filter_criteria.cirteria;

import java.util.List;

import com.demo.gof23.filter_criteria.Person;

public class OrCriteria implements Criteria{
	
	private Criteria one;
	private Criteria two;
	
	private OrCriteria(Criteria one,Criteria two){
		this.one = one;
		this.two = two;
	}
	
	public static final OrCriteria getInstance(Criteria one,Criteria two){
		return new OrCriteria(one,two);
	}

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		final List<Person> oneCriteria = one.meetCriteria(list);
		final List<Person> twoCriteria = two.meetCriteria(list);
		oneCriteria.stream().forEach(x->{
			if(!twoCriteria.contains(x)){
				twoCriteria.add(x);
			}
		});
		return twoCriteria;
	}

}
