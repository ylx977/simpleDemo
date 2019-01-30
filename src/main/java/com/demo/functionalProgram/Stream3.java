package com.demo.functionalProgram;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;

import lombok.Builder;
import lombok.Data;

public class Stream3 {
	
	static List<Person> persons = new ArrayList<Person>();
	
	static{
		for (int i = 0; i < 10; i++) {
			persons.add(Person.builder().age(i).Name("jack"+i).build());
		}
	}

	public static void main(String[] args) {
		Map<Integer, String> collect19 = persons.stream().collect(Collectors.toMap(Person::getAge, Person::getName));
		System.out.println(JSON.toJSONString(collect19));
	}

}
