package com.example.flickassist.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MovieInfo extends Movie {

	private String[] genre = null;
	private String director;
	private ArrayList<MovieClip> clips;
	
	public MovieInfo(JSONObject json) {
		super(json);
		
		try {
			if(json.has("genre")) {
				JSONArray jsonArr = json.getJSONArray("genre");
				genre = new String[jsonArr.length()];
				for(int i = 0; i < jsonArr.length(); i++) {
					genre[i] = jsonArr.getString(i);
				}
			}
			
			// http://api.rottentomatoes.com/api/public/v1.0/movies/770672122/clips.json?apikey=qg8r8sxqbq268r7mntbt6uy2
			String clip_link = "http://api.rottentomatoes.com/api/public/v1.0/movies/" + this.getId() +"/clips.json?apikey=qg8r8sxqbq268r7mntbt6uy2";
			Log.d("debug", "clip_link: "+clip_link);
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(clip_link, new JsonHttpResponseHandler() {
				public void onSuccess(JSONObject json) {
					try {
						JSONObject obj = null;
						String tempThumb;
						JSONArray jsonArray = json.getJSONArray("clips");
						if(jsonArray != null) {
							clips = new ArrayList<MovieClip>();
							for (int i = 0; i < jsonArray.length(); i++) {
								obj = jsonArray.getJSONObject(i);
								tempThumb = obj.getString("thumbnail");
								if(tempThumb != null && "".equals(tempThumb)) {
									clips.add(new MovieClip(tempThumb));
									Log.d("debug", tempThumb);
								}
							}
						}
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
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MovieClip> getMovieClip () {
		if(clips != null && clips.size()>0) {
			return clips;
		}
		return null;
	}
	public String getGenre() {
		if (genre == null)
			return "N/A";
		else {
			StringBuffer sb = new StringBuffer(genre[0]);
			for (int i = 1; i < genre.length; i++) {
				sb.append(", "+genre[i]);
			}
			return sb.toString();
		}
	}
}
