package com.grabIt.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Sample {

	@NotNull
	private String id;
	
	@NotEmpty(message="Name should not be empty.")
	private String name;
	
	@NotEmpty(message="Last Name should not be empty.")
	private String lastName;
	
	
	
}
