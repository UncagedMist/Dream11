package kk.techbytecare.dream11.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kk.techbytecare.dream11.R;


public class MyContestFragment extends Fragment {

    public MyContestFragment() {
        // Required empty public constructor
    }

    public static MyContestFragment getInstance()    {
        return new MyContestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_contest, container, false);
    }
}
