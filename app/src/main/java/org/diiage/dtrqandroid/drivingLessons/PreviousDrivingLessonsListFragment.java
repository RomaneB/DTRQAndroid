package org.diiage.dtrqandroid.drivingLessons;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.diiage.dtrqandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousDrivingLessonsListFragment extends Fragment {

    public PreviousDrivingLessonsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_driving_lessons_list, container, false);
    }

}
