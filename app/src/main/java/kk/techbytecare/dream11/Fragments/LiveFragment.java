package kk.techbytecare.dream11.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kk.techbytecare.dream11.R;

public class LiveFragment extends Fragment {

    private static LiveFragment INSTANCE = null;

    public static LiveFragment getInstance() {
        if (INSTANCE == null)   {
            INSTANCE = new LiveFragment();
        }
        return INSTANCE;
    }

    public LiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

}
