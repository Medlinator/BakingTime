package com.example.seanmedlin.bakingtime.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.models.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

    @BindView(R.id.fragment_details_step_description_text_view)
    @Nullable
    TextView mTextView;

    @BindView(R.id.fragment_step_details_next_button) Button mButton;

    private Step mStep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details_step, container, false);
        ButterKnife.bind(this, rootView);

        Intent intent = getActivity().getIntent();
        mStep = (Step) intent.getSerializableExtra("step");

        if (mTextView != null) {
            mTextView.setText(mStep.getDescription());

        }
        return rootView;
    }
}
