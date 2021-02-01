package com.poribarbazar.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;


import androidx.fragment.app.Fragment;


import com.poribarbazar.R;
import com.poribarbazar.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

EditText editTextsearch;

    public HomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container!=null)
        {

            container.removeAllViews();

        }
       View view = inflater.inflate(R.layout.fragment_home, container, false);





        return view;
    }




}