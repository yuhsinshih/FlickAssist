package com.example.flickassist.models;

import java.util.ArrayList;

import org.json.JSONException;
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

	public static Movie fromJSON(JSONObject json) {
		Movie movie = new Movie();
		try {
			movie.id = json.getInt("id");
			movie.title = json.getString("title");
			movie.imdbid = json.getInt("imdbid");
			movie.runtime = json.getInt("runtime");
			movie.synopsis = json.getString("synopsis");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return movie;
	}
	
	public int getId() {
		return id;
	}
	
	public int getImdbId() {
		return imdbid;
	}
	
	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public int getRuntime() {
		return runtime;
	}

	public int getCritics_score() {
		return critics_score;
	}

	public int getAudience_score() {
		return audience_score;
	}

	public String getSynopsis() {
		return synopsis;
	}

	@Override
	public String toString() {
		return title + " (" + year +")";
	}

}
