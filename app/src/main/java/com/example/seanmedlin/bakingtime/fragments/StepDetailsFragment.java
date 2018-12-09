package com.example.seanmedlin.bakingtime.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.seanmedlin.bakingtime.R;
import com.example.seanmedlin.bakingtime.activities.RecipeDetailsActivity;
import com.example.seanmedlin.bakingtime.activities.StepDetailsActivity;
import com.example.seanmedlin.bakingtime.models.Recipe;
import com.example.seanmedlin.bakingtime.models.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

    private final static String TAG = StepDetailsFragment.class.getSimpleName();

    @BindView(R.id.fragment_details_step_no_video_text_view)
    @Nullable
    TextView mNoVideoTextView;
    @BindView(R.id.video_view)
    PlayerView playerView;
    @BindView(R.id.fragment_details_step_description_text_view)
    @Nullable
    TextView mTextView;
    @BindView(R.id.fragment_step_details_back_button)
    @Nullable
    Button mBackButton;
    @BindView(R.id.fragment_step_details_next_button)
    @Nullable
    Button mNextButton;


    private SimpleExoPlayer player;
    private Recipe mRecipe;
    private ArrayList<Step> mSteps;
    private Step mStep;
    private int mCurrentStepId;
    private long mPlaybackPosition;
    private int mCurrentWindow;
    private boolean mPlayWhenReady = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "OnCreateView");

        View rootView = inflater.inflate(
                R.layout.fragment_details_step,
                container,
                false);

        ButterKnife.bind(this, rootView);

        Intent intent = getActivity().getIntent();
        if (getResources().getBoolean(R.bool.twoPane)) {
            Bundle arguments = getArguments();
            mStep = (Step) arguments.getSerializable("step");
        } else {
            mSteps = (ArrayList<Step>) intent.getSerializableExtra("steps");
            mStep = (Step) intent.getSerializableExtra("step");
            mCurrentStepId = mStep.getId();
        }

        if (mTextView != null) {
            mTextView.setText(mStep.getDescription());
        }

        if (savedInstanceState != null) {
            Log.v(TAG, "pullSavedInstanceData");
            mPlaybackPosition = savedInstanceState.getLong("mPlaybackPosition");
            mCurrentWindow = savedInstanceState.getInt("mCurrentWindow");
            mPlayWhenReady = savedInstanceState.getBoolean("mPlayWhenReady");
        }

        // Set Back button visibility to GONE if id of step is 0
        if (mCurrentStepId < 1 && mBackButton != null) {
            mBackButton.setVisibility(View.GONE);
        }

        if (mBackButton != null) {
            mBackButton.setOnClickListener(v -> {
                Step step = mSteps.get((mCurrentStepId - 1));
                Intent setStepIntent = new Intent(getContext(), StepDetailsActivity.class);
                setStepIntent.putExtra("step", step);
                setStepIntent.putExtra("steps", mSteps);
                startActivity(setStepIntent);
            });
        }

        // Set Back button visibility to GONE if id of step is 0



        if (mNextButton != null) {
            if (mCurrentStepId == mSteps.size() - 1 && mNextButton != null) {
                mNextButton.setVisibility(View.GONE);
            }
            mNextButton.setOnClickListener(v -> {
                Step step = mSteps.get((mCurrentStepId + 1));
                Intent setStepIntent = new Intent(getContext(), StepDetailsActivity.class);
                setStepIntent.putExtra("step", step);
                setStepIntent.putExtra("steps", mSteps);
                startActivity(setStepIntent);
            });
        }

        return rootView;
    }

    @Override
    public void onStart() {
        Log.v(TAG, "OnStart");
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        Log.v(TAG, "OnResume");
        super.onResume();
        if (player == null) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        Log.v(TAG, "OnPause");
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initializePlayer() {
        Log.v(TAG, "initializePlayer");
        if (player == null) {
            Activity activity = getActivity();
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(activity),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(player);
            player.setPlayWhenReady(mPlayWhenReady);
            player.seekTo(mCurrentWindow, mPlaybackPosition);
        }

        if (mStep.getVideoUrl().isEmpty()) {
            playerView.setVisibility(View.GONE);
            mNoVideoTextView.setVisibility(View.VISIBLE);
        } else {
            MediaSource mediaSource = buildMediaSource(Uri.parse(mStep.getVideoUrl()));
            player.prepare(mediaSource, false, false);
        }
    }

    private void releasePlayer() {
        Log.v(TAG, "releasePlayer");
        if (player != null) {
            mPlaybackPosition = player.getCurrentPosition();
            mCurrentWindow = player.getCurrentWindowIndex();
            mPlayWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab"))
                .createMediaSource(uri);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putLong("mPlaybackPosition", mPlaybackPosition);
        outState.putInt("mCurrentWindow", mCurrentWindow);
        outState.putBoolean("mPlayWhenReady", mPlayWhenReady);
    }
}
