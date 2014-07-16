package com.example.flickassist.models;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.activeandroid.util.Log;

public class Cast extends Person {

	private String[] character;
	private String id;

	public Cast (JSONObject json) {
		
		JSONArray chars;
		character = null;

		try {
			name = json.getString("name");
			id = json.getString("id");
			chars = json.getJSONArray("characters");
			
			if (chars != null) {
				this.character = new String[chars.length()];
				for (int i=0; i < chars.length(); i++) {
					try {
						this.character[i] = chars.getString(i);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public String getId () {
		return id;
	}

	public String[] getCharacter() {
		return this.character;
	}

	@Override
	public String toString() {
		if (this.character != null && this.character.length > 0) {
			StringBuffer sb = new StringBuffer(this.character[0]);

			for (int i=1; i < this.character.length; i++) {
				sb.append(", " + this.character[i]);
			}
			return sb.toString();
		} else {
			return "N/A";
		}
	}
}
