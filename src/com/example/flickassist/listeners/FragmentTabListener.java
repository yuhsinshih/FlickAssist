package com.example.flickassist.listeners;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class FragmentTabListener<T extends Fragment> implements TabListener {
    private Fragment mFragment;
	private final FragmentActivity mActivity;
	private final String mTag;
	private final Class<T> mClass;
	private final int mfragmentContainerId;
	private final ViewPager mViewPager;
        
        // This version defaults to replacing the entire activity content area
        // new FragmentTabListener<SomeFragment>(this, "first", SomeFragment.class))
	public FragmentTabListener(FragmentActivity activity, String tag, Class<T> clz, ViewPager vpPager) {
		mActivity = activity;
		mTag = tag;
		mClass = clz;
		mfragmentContainerId = android.R.id.content;
		mViewPager = vpPager;
	}
        
        // This version supports specifying the container to replace with fragment content
        // new FragmentTabListener<SomeFragment>(R.id.flContent, this, "first", SomeFragment.class))
	public FragmentTabListener(int fragmentContainerId, FragmentActivity activity, 
            String tag, Class<T> clz, ViewPager vpPager) {
		mActivity = activity;
		mTag = tag;
		mClass = clz;
		mfragmentContainerId = fragmentContainerId;
		mViewPager = vpPager;
	}

	/* The following are each of the ActionBar.TabListener callbacks */

	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentTransaction sft = mActivity.getSupportFragmentManager().beginTransaction();
		// Check if the fragment is already initialized
		if (mFragment == null) {
			// If not, instantiate and add it to the activity
			mFragment = Fragment.instantiate(mActivity, mClass.getName());
			sft.add(mfragmentContainerId, mFragment, mTag);
		} else {
			// If it exists, simply attach it in order to show it
			sft.attach(mFragment);
		}
		mViewPager.setCurrentItem(tab.getPosition());
		sft.commit();
	}

	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentTransaction sft = mActivity.getSupportFragmentManager().beginTransaction();
		if (mFragment != null) {
			// Detach the fragment, because another one is being attached
			sft.detach(mFragment);
		}
		sft.commit();
	}

	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}
}