package com.guysapp.writely.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guysapp.writely.R;
import com.guysapp.writely.RewardActivity;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    String mString;
    LinearLayout l_rewards;



    public static BottomSheetFragment newInstance(String string) {
        BottomSheetFragment f = new BottomSheetFragment();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottomsheet, container, false);
        initUI(v);


        return v;
    }

    public void initUI(View v){
         l_rewards = v.findViewById(R.id.l_rewards);
         l_rewards.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l_rewards:
                Intent i = new Intent(getContext(), RewardActivity.class);
                startActivity(i);
                getActivity().finish();

        }
    }
}
