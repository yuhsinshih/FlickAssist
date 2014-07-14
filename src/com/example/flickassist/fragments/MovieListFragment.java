package com.example.flickassist.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.flickassist.R;
import com.example.flickassist.RottenTomatoClient;
import com.example.flickassist.adapters.MovieArrayAdapter;
import com.example.flickassist.listeners.EndlessScrollListener;
import com.example.flickassist.models.Movie;

public abstract class MovieListFragment extends Fragment {

	private ArrayList<Movie> movies;
	private MovieArrayAdapter aMovies;
	private ListView lvMovies;
//	private RottenTomatoClient client;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Non-view initialization
		movies = new ArrayList<Movie>();
		aMovies = new MovieArrayAdapter(getActivity(), movies);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout
		View v = inflater.inflate(R.layout.fragment_movie_list , container, false);
		// Assign our view references
		lvMovies = (ListView) v.findViewById(R.id.lvMovies);
		lvMovies.setAdapter(aMovies);
		
		lvMovies.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				// Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView

				customLoadMoreDataFromApi(page);
			}
		});
		return v;
	}
	
	public abstract void populateMovieList(int offset, boolean clear);
	
	// Append more data into the adapter
	public void customLoadMoreDataFromApi(int offset) {
		// This method probably sends out a network request and appends new data items to your adapter. 
		// Use the offset value and add it as a parameter to your API request to retrieve paginated data.
		// Deserialize API response and then construct new objects to append to the adapter
		populateMovieList(offset, false);
	}
	
	public void clearMovies() {
		if (movies != null) {
			movies.clear();
		}
	}
	
	public void addAll(ArrayList<Movie> movies) {
		aMovies.addAll(movies);
	}
}
