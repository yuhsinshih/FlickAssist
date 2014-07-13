package com.example.flickassist.models;

public class Person {
	protected String name;

	public Person() {
		this.name = "unknown";
	}

	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
