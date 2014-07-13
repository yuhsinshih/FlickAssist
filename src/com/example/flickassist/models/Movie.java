package com.example.flickassist.models;

import java.util.ArrayList;

import org.json.JSONObject;

public class Movie {
	private String title;
	private int id;	// int or long?
	private int imdbid;
	private int year;
	private int runtime;
	private int critics_score;
	private int audience_score;
	private String synopsis;
	private ArrayList<Cast> casts;

	public Movie(JSONObject json) {
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle() {
		return this.title;
	}
}
