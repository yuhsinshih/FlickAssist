package com.example.flickassist.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.flickassist.R;
import com.example.flickassist.fragments.BoxOfficeFragment;
import com.example.flickassist.fragments.InTheaterFragment;
import com.example.flickassist.fragments.UpcomingFragment;
import com.example.flickassist.listeners.FragmentTabListener;
 
public class MainActivity extends FragmentActivity {

	FragmentPagerAdapter adapterViewPager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOnPageChangeListener(
        	new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    // When swiping between pages, select the
                    // corresponding tab.
                    getActionBar().setSelectedNavigationItem(position);
                }
            }
        );
        setupTabs(vpPager);
    }
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	MenuInflater inflater = getSupportMenuInflater();
//    	return super.onCreateOptionsMenu(menu);
//    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
	private static int NUM_ITEMS = 3;
		
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        
        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
 
        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
            case 0:
                return InTheaterFragment.newInstance(0, "In Theater");
            case 1:
                return UpcomingFragment.newInstance(1, "Upcoming");
            case 2:
            	return BoxOfficeFragment.newInstance(2, "Box Office");
            default:
            	return null;
            }
        }
        
        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
        	return "Page " + position;
        }
    }

	private void setupTabs(ViewPager vpPager) {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
	    
		Tab tab1 = actionBar
			.newTab()
			.setText("In Theater")
			.setTag("InTheaterFragment")
			.setTabListener(new FragmentTabListener<InTheaterFragment> (
					R.id.vpPager, this, "In Theater",
					InTheaterFragment.class, vpPager));
		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("Upcoming")
			.setTag("UpcomingFragment")
			.setTabListener(new FragmentTabListener<UpcomingFragment> (
					R.id.vpPager, this, "Upcoming",
					UpcomingFragment.class, vpPager));
		actionBar.addTab(tab2);
		
		Tab tab3 = actionBar
				.newTab()
				.setText("Box Office")
				.setTag("BoxOfficeFragment")
				.setTabListener(new FragmentTabListener<BoxOfficeFragment> (
						R.id.vpPager, this, "Box Office",
						BoxOfficeFragment.class, vpPager));

		actionBar.addTab(tab3);
	}
}