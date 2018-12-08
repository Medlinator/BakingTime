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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsFragment extends Fragment {

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

        View rootView = inflater.inflate(
                R.layout.fragment_details_step,
                container,
                false);

        ButterKnife.bind(this, rootView);

        Intent getStepIntent = getActivity().getIntent();
        mRecipe = (Recipe) getStepIntent.getSerializableExtra("recipe");
        mSteps = mRecipe.getSteps();
        mStep = (Step) getStepIntent.getSerializableExtra("step");
        mCurrentStepId = mStep.getId();


        if (mTextView != null) {
            mTextView.setText(mStep.getDescription());
        }

        if (savedInstanceState != null) {
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
        if (mCurrentStepId == mSteps.size() - 1 && mNextButton != null) {
            mNextButton.setVisibility(View.GONE);
        }

        if (mNextButton != null) {
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
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player == null) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        if (player == null) {
            Activity activity = getActivity();
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(activity),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(player);
            player.setPlayWhenReady(mPlayWhenReady);
            player.seekTo(mCurrentWindow, mPlaybackPosition);
        }
        if (!mStep.getVideoUrl().isEmpty()) {
            MediaSource mediaSource = buildMediaSource(Uri.parse(mStep.getVideoUrl()));
            player.prepare(mediaSource, false, false);
        } else {
            MediaSource mediaSource = buildMediaSource(Uri.parse("https://www.youtube.com/watch?v=05DqIGS_koU"));
            player.prepare(mediaSource, false, false);

        }

    }

    private void releasePlayer() {
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
        super.onSaveInstanceState(outState);
        outState.putLong("mPlaybackPosition", mPlaybackPosition);
        Log.e("TAG", String.valueOf(mPlaybackPosition));
        outState.putInt("mCurrentWindow", mCurrentWindow);
        Log.e("TAG", String.valueOf(mCurrentWindow));
        outState.putBoolean("mPlayWhenReady", mPlayWhenReady);
        Log.e("TAG", String.valueOf(mPlayWhenReady));
    }
}
