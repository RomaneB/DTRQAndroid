package org.diiage.dtrqandroid.drivingLessons.viewPager;

import android.content.Context;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.drivingLessons.NombreLessonsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NombreLeconsFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public NombreLeconsFragmentPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

                return new NombreLessonsFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {

                return this.context.getString(R.string.nombre_lecons);

    }

    @Override
    public int getCount() {
        return 3;
    }
}
