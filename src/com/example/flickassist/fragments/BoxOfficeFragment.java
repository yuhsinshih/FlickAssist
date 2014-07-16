package com.example.flickassist.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.flickassist.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class BoxOfficeFragment extends MovieListFragment {
	
	public static BoxOfficeFragment newInstance(int page, String title) {
		Log.d("debug", "BoxOffice init");
		BoxOfficeFragment fragmentMovieList = new BoxOfficeFragment();
		Bundle args = new Bundle();
		args.putInt("page", page);
		args.putString("title", title);
		fragmentMovieList.setArguments(args);
		return fragmentMovieList;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateMovieList(1, false);
	}
	
	@Override
	public void populateMovieList(int page, boolean clear) {
		final boolean clearResult = clear;

		// Get top 30 box office movie
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=qg8r8sxqbq268r7mntbt6uy2&limit=30", 
				new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject json) {
				if(clearResult == true){
					clearMovies();
				}
				try {
					addAll(Movie.fromJSONArray(json.getJSONArray("movies")));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}
		});
	}
}
