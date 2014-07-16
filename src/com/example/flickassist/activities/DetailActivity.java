package com.example.flickassist.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.flickassist.R;

public class DetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		String title = getIntent().getStringExtra("title");
		String id = getIntent().getStringExtra("movie_id");

		TextView tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
		tvDetailTitle.setText(title + " : " + id);
	}
}
