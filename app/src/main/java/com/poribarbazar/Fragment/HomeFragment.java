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

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        /*if (container!=null)
        {

            container.removeAllViews();

        }*/





        return view;
    }




}