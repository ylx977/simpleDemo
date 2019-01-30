package com.demo.gof23.filter_criteria.cirteria;

import java.util.ArrayList;
import java.util.List;

import com.demo.gof23.filter_criteria.Person;

public class FemaleCriteria implements Criteria {
	
	private static final FemaleCriteria FEMALE_CRITERIA = new FemaleCriteria();
	
	private FemaleCriteria() {}
	
	public static final FemaleCriteria getInstance(){
		return FEMALE_CRITERIA;
	}

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		final List<Person> females = new ArrayList<Person>();
		list.stream().forEach(x->{
			if("Female".equals(x.getGender())){
				females.add(x);
			}
		});
		return females;
	}

}
