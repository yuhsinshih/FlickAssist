package com.example.flickassist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;
import com.example.flickassist.R;

public class MovieListFragment extends Fragment {

	private ListView lvMovies;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Non-view initialization
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout
		View v = inflater.inflate(R.layout.fragment_movie_list , container, false);
		// Assign our view references
		lvMovies = (ListView) v.findViewById(R.id.lvMovies);
		return v;
	}
}
