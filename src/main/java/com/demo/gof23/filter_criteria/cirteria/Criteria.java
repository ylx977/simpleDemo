package com.demo.gof23.filter_criteria.cirteria;

import java.util.List;

import com.demo.gof23.filter_criteria.Person;

public interface Criteria {
	
	List<Person> meetCriteria(List<Person> list);
}
