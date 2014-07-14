package com.example.flickassist.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.flickassist.R;
import com.example.flickassist.fragments.InTheaterFragment;
import com.example.flickassist.fragments.UpcomingFragment;
import com.example.flickassist.listeners.FragmentTabListener;
 
public class MainActivity extends FragmentActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTabs();
    }
    
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("In Theater")
			.setTag("InTheaterFragment")
			.setTabListener(new FragmentTabListener<InTheaterFragment> (
					R.id.flContainer, this, "inTheater",
					InTheaterFragment.class));
		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("Upcoming")
			.setTag("UpcomingTimelineFragment")
			.setTabListener(
			    new FragmentTabListener<UpcomingFragment>(R.id.flContainer, this, "upcoming",
			    		UpcomingFragment.class));

		actionBar.addTab(tab2);
	}
}