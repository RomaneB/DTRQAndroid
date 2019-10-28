package org.diiage.dtrqandroid.drivingLessons.ViewPager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.drivingLessons.MyDrivingLessonsListFragment;
import org.diiage.dtrqandroid.drivingLessons.NextDrivingLessonsListFragment;
import org.diiage.dtrqandroid.drivingLessons.PreviousDrivingLessonsListFragment;

public class DrivingLessonsTabsFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public DrivingLessonsTabsFragmentPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            default:
            case 0:
                return new NextDrivingLessonsListFragment();
            case 1:
                return new MyDrivingLessonsListFragment();
            case 2:
                return new PreviousDrivingLessonsListFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            default:
            case 0:
                return this.context.getString(R.string.driving_lessons_tabs_title_lecons_precedentes);
            case 1:
                return this.context.getString(R.string.driving_lessons_tabs_title_mes_lecons);
            case 2:
                return this.context.getString(R.string.driving_lessons_tabs_title_lecons_prochaines);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
