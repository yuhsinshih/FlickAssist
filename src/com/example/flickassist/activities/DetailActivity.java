package com.example.flickassist.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.flickassist.R;
import com.example.flickassist.models.Movie;
import com.example.flickassist.models.MovieInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

public class DetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		String title = getIntent().getStringExtra("title");
		String id = getIntent().getStringExtra("movie_id");

		TextView tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
		tvDetailTitle.setText(title);
		setDetailView(id);
	}
	
	void setDetailView(String id) {
		AsyncHttpClient client = new AsyncHttpClient();
		String request_url = "http://api.rottentomatoes.com/api/public/v1.0/movies/"+id+".json?apikey=qg8r8sxqbq268r7mntbt6uy2";
		Log.d("debug",request_url);
		client.get(request_url, 
			new JsonHttpResponseHandler() {
			public void onSuccess(JSONObject json) {
				
				MovieInfo movie = new MovieInfo(json);
				
				SmartImageView ivDetailImage = (SmartImageView) findViewById(R.id.ivDetailImage);
				ivDetailImage.setImageUrl(movie.getPoster_detailed());
//				Log.d("debug", "img: " + movie.getPoster_detailed());
				
//				TextView tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
//				tvDetailTitle.setText(movie.getTitle() + "(" + movie.getYear() + ")");
			}
		});
	}
}
