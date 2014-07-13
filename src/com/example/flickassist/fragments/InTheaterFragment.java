package com.example.flickassist.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

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
}
