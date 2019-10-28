package org.diiage.dtrqandroid.drivingLessons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.drivingLessons.viewPager.DrivingLessonsTabsFragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class DrivingLessonsTabsFragment extends Fragment {

    private DrivingLessonsTabsFragmentPagerAdapter pagerAdapter;

    public DrivingLessonsTabsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driving_lessons_tabs, container, false);

        pagerAdapter = new DrivingLessonsTabsFragmentPagerAdapter(this.getChildFragmentManager(), this.getContext());
        ViewPager viewPager = view.findViewById(R.id.driving_lessons_tabs_view_pager);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.driving_lessons_tabs_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Inflate the layout for this fragment
        return view;
    }

}