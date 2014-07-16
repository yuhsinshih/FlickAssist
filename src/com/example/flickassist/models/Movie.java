package com.example.flickassist.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Movie {
	private String title;
	private int id;	// int or long?
	private int imdbid;
	private int year;
	private int runtime;
	private int critics_score;
	private int audience_score;
	private String synopsis;
	private String genre;
	private ArrayList<Cast> casts;
	private String poster_thumb;
	private String poster_profile;
	private String poster_detailed;
	private String poster_original;

	public Movie (JSONObject json) {
		JSONObject posters, ratings;
		JSONArray castArray;
		try {
			id = json.getInt("id");
			title = json.getString("title");
			year = json.getInt("year");
			
			posters = json.getJSONObject("posters");
			poster_thumb = posters.getString("thumbnail");
			poster_profile = posters.getString("profile");
			poster_detailed = posters.getString("detailed");
			poster_original = posters.getString("original");
			
			ratings = json.getJSONObject("ratings");
			critics_score = ratings.getInt("critics_score");
			audience_score = ratings.getInt("audience_score");
			
//			imdbid = json.getInt("imdbid");
			runtime = json.getInt("runtime");
			synopsis = json.getString("synopsis");
		
			
			castArray = json.getJSONArray("abridged_cast");
//			Log.d("debug", castArray.toString());
			casts = new ArrayList<Cast>();
			for (int i = 0; i < castArray.length(); i++) {
				casts.add(new Cast(castArray.getJSONObject(i)));
				Log.d("debug", casts.get(i).getName());
//				Log.d("debug", casts.toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) {
		if (jsonArray == null)
			return null;
		
		ArrayList<Movie> movies = new ArrayList<Movie>(jsonArray.length());
		for (int i=0; i < jsonArray.length(); i++) {
			JSONObject movieJson = null;
			try {
				movieJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			Movie movie = new Movie(movieJson);
			if(movie != null) {
				movies.add(movie);
			}
		}
		return movies;
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

	public String getPoster_thumb() {
		return poster_thumb;
	}


	public String getPoster_profile() {
		return poster_profile;
	}


	public String getPoster_detailed() {
		return poster_detailed;
	}


	public String getPoster_original() {
		return poster_original;
	}
	
	public String getCasts() {
		StringBuffer sb = new StringBuffer();
//		Log.d("debug", "case size: "+casts.size());
		for (int i=0; i < casts.size(); i++ ) {
			sb.append(casts.get(i).getName() + ",");
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return title;
	}

}
