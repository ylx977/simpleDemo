package com.demo.gof23.filter_criteria.cirteria;

import java.util.ArrayList;
import java.util.List;

import com.demo.gof23.filter_criteria.Person;

public class AndCriteria implements Criteria {
	
	private Criteria one;
	private Criteria two;
	
	private AndCriteria(Criteria one,Criteria two){
		this.one = one;
		this.two = two;
	}
	
	public static final AndCriteria getInstance(Criteria one,Criteria two){
		return new AndCriteria(one,two);
	}

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		final List<Person> oneCriteria = one.meetCriteria(list);
		final List<Person> twoCriteria = two.meetCriteria(list);
		final List<Person> andLists = new ArrayList<Person>();
		oneCriteria.stream().forEach(x->{
			if(twoCriteria.contains(x)){
				andLists.add(x);
			}
		});
		return andLists;
	}

}
