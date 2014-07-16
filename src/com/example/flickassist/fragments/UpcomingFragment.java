package com.example.flickassist.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import com.example.flickassist.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UpcomingFragment extends MovieListFragment {

	public static UpcomingFragment newInstance(int page, String title) {
		UpcomingFragment fragmentMovieList = new UpcomingFragment();
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

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=qg8r8sxqbq268r7mntbt6uy2&page_limit="+PAGE_LIMIT+"&page="+page, 
				new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject json) {
				if(clearResult == true){
					clearMovies();
				}
				try {
					total = json.getInt("total");
//					Log.d("debug", "Upcoming: " + total);
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
