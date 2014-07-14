package com.example.flickassist.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.example.flickassist.models.Movie;
import com.loopj.android.http.JsonHttpResponseHandler;

public class InTheaterFragment extends MovieListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateMovieList(1, false);
	}
	
	public void populateMovieList(int page, boolean clear) {
		getClient().getInTheaterMovieList(new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject response) {
				JSONArray movieJsonResults = null;
				Log.d("debug", response.toString());
			}
			
			public void onFailure(JSONObject response) {
				
			}
		}, page);
	}

	@Override
	public void populateTimeline(int page, boolean clear) {
		final boolean clearResult = clear;

		getClient().getInTheaterMovieList(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray json) {
				if(clearResult == true){
					clearMovies();
				}
				addAll(Movie.fromJSON(json));
//				addAll(Tweet.fromJSONArray(json));
			}
			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}
		}, page);
	}

}
