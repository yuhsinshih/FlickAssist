package com.example.flickassist.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flickassist.R;
import com.example.flickassist.models.Movie;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

	public MovieArrayAdapter(Context context, List<Movie> movies) {
		super(context, 0, movies);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for position

		// Find or inflate the template
		View v;	// for performance reason
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
//			v = inflator.inflate(R.layout. , parent, false);
			v = inflator.inflate(R.layout.movie_item, parent, false);
		} else {
			v = convertView;
		}
		// Find the views within template

		// Populate views with movie data

		return v;
	}

}
