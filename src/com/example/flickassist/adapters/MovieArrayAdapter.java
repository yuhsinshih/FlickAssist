package com.example.flickassist.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flickassist.R;
import com.example.flickassist.activities.DetailActivity;
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
		final Movie movie = getItem(position);

		// Find or inflate the template
		View v;	// for performance reason
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.movie_item, parent, false);
		} else {
			v = convertView;
		}

		// Find the views within template
		TextView tvMovieTitle = (TextView) v.findViewById(R.id.tvMovieTitle);
		tvMovieTitle.setText(movie.getTitle());
		
		TextView tvYear = (TextView) v.findViewById(R.id.tvYear);
		tvYear.setText("(" + movie.getYear() + ")");
		
		TextView tvDuration = (TextView) v.findViewById(R.id.tvDuration);
		tvDuration.setText(movie.getRuntime());
		
		TextView tvRating = (TextView) v.findViewById(R.id.tvRating);
		tvRating.setText(String.valueOf(movie.getCritics_score()));
		
		TextView tvCast = (TextView) v.findViewById(R.id.tvCast);
		tvCast.setText(movie.getCasts());
		
		ImageView ivMovieThumb = (ImageView) v.findViewById(R.id.ivMovieThumb);
		ivMovieThumb.setImageResource(android.R.color.transparent);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(movie.getPoster_profile(), ivMovieThumb);

		v.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), DetailActivity.class);
				i.putExtra("movie_id", movie.getId());
				i.putExtra("title", movie.getTitle());
				getContext().startActivity(i);
			}
		});
		// Populate views with movie data

		return v;
	}

}
