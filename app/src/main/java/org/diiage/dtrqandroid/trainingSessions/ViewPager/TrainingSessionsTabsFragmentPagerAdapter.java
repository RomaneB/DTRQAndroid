package org.diiage.dtrqandroid.trainingSessions.ViewPager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.trainingSessions.PastTrainingSessionsListFragment;
import org.diiage.dtrqandroid.trainingSessions.TrainingSessionsListFragment;

public class TrainingSessionsTabsFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public TrainingSessionsTabsFragmentPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            default:
            case 0:
                return new TrainingSessionsListFragment();
            case 1:
                return new PastTrainingSessionsListFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            default:
            case 0:
                return this.context.getString(R.string.training_sessions_tabs_title_liste_des_lecons);
            case 1:
                return this.context.getString(R.string.training_sessions_tabs_title_lecons_passees);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
