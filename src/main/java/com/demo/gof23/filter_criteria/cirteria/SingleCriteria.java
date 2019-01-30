package com.demo.gof23.filter_criteria.cirteria;

import java.util.ArrayList;
import java.util.List;

import com.demo.gof23.filter_criteria.Person;

public class SingleCriteria implements Criteria {

	private static final SingleCriteria SINGLE_CRITERIA = new SingleCriteria();
	
	private SingleCriteria() {}
	
	public static final SingleCriteria getInstance(){
		return SINGLE_CRITERIA;
	}

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		final List<Person> males = new ArrayList<Person>();
		list.stream().forEach(x->{
			if("Single".equals(x.getMaritalStatus())){
				males.add(x);
			}
		});
		return males;
	}

}
