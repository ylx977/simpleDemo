package com.demo.gof23.filter_criteria.cirteria;

import java.util.ArrayList;
import java.util.List;

import com.demo.gof23.filter_criteria.Person;

public class MaleCriteria implements Criteria {

	private static final MaleCriteria MALE_CRITERIA = new MaleCriteria();
	
	private MaleCriteria() {}
	
	public static final MaleCriteria getInstance(){
		return MALE_CRITERIA;
	}

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		final List<Person> males = new ArrayList<Person>();
		list.stream().forEach(x->{
			if("Male".equals(x.getGender())){
				males.add(x);
			}
		});
		return males;
	}

}
