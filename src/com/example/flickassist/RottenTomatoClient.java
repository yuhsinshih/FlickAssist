package com.example.flickassist;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RottenTomatoClient {

    public static final String REST_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
    public final String API_KEY = "qg8r8sxqbq268r7mntbt6uy2";
    public static final int PAGE_LIMIT = 16;

    private AsyncHttpClient client;
    
    public RottenTomatoClient(Context context) {
    	client = new AsyncHttpClient();
    }
    
    public void getInTheaterMovieList(AsyncHttpResponseHandler handler, int page) {
    	String apiUrl = getApiUrl("lists/movies/in_theaters.json");
    	RequestParams params = new RequestParams();
    	params.put("page", String.valueOf(page));
    	client.get(apiUrl, params, handler);	// use GET method
    }
    
    public String getApiUrl (String path) {
    	return REST_URL + path + "?apikey=" + API_KEY;
    }
}


