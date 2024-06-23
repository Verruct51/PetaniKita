package com.zoss.petanikita;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ScheduleMenuFragment extends Fragment {

    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule, container, false);

        // Setup Toolbar
        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle("Schedule");

        return rootView;
    }
}
