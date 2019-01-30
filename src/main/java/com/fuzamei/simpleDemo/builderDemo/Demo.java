package com.fuzamei.simpleDemo.builderDemo;

import com.fuzamei.simpleDemo.builderDemo.Bean.BeanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Demo {
	
	public static void main(String[] args) {
		BeanBuilder builder = Bean.builder();
		builder.name("name");
		builder.age(10);
		builder.ctime(1000L);
		builder.sex(true);
		Bean build = builder.build();
		System.out.println(build);
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Bean{
	private String name;
	private Integer age;
	private Boolean sex;
	private Long ctime;
}
