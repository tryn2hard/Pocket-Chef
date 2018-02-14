package com.example.robot.pocket_chef.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.TestData;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepInstructionFragment extends Fragment {

    private static final String TAG = StepInstructionFragment.class.getSimpleName();

    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_DESCRIPTION_POS = "descriptionPos";

    private int mRecipeId;
    private int mStepDescriptionPos;
    private Context mContext;

    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;

    public StepInstructionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            mStepDescriptionPos = getArguments().getInt(ARG_DESCRIPTION_POS);
        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_step_instruction, container, false);
        TextView textView = (TextView) view.findViewById(R.id.step_instruction_text_view);
        textView.setText(TestData.ITEMS.get(mRecipeId).steps.get(mStepDescriptionPos).description);

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        LoadControl loadControl = new DefaultLoadControl();

        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        simpleExoPlayerView = new SimpleExoPlayerView(getContext());
        simpleExoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.player_view);

        simpleExoPlayerView.setUseController(true);
        simpleExoPlayerView.requestFocus();

        simpleExoPlayerView.setPlayer(player);

        if(TestData.ITEMS.get(mRecipeId).steps.get(mStepDescriptionPos).videoUrl != null){
            String recipeDescriptionVideo = TestData.ITEMS.get(mRecipeId).steps.get(mStepDescriptionPos).videoUrl;
            Uri mp4VideoUri = Uri.parse(recipeDescriptionVideo);

            DefaultBandwidthMeter bandwidthMeter1 = new DefaultBandwidthMeter();

            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, "PocketChef"), bandwidthMeter1);

            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            final MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri, dataSourceFactory, extractorsFactory, null, null);

            final LoopingMediaSource loopingSource = new LoopingMediaSource(videoSource);

            player.prepare(videoSource);

            player.addListener(new ExoPlayer.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest) {
                    Log.v(TAG, "Listener-onTimelineChanged...");
                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                    Log.v(TAG, "Listener-onTracksChanged...");
                }

                @Override
                public void onLoadingChanged(boolean isLoading) {
                    Log.v(TAG, "Listener-onLoadingChanged...isLoading:"+isLoading);
                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    Log.v(TAG, "Listener-onPlayerStateChanged..." + playbackState);
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                    Log.v(TAG, "Listener-onRepeatModeChanged...");
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    Log.v(TAG, "Listener-onPlayerError...");
                    player.stop();
                    player.prepare(loopingSource);
                    player.setPlayWhenReady(true);
                }

                @Override
                public void onPositionDiscontinuity() {
                    Log.v(TAG, "Listener-onPositionDiscontinuity...");
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    Log.v(TAG, "Listener-onPlaybackParametersChanged...");
                }
            });

            player.setPlayWhenReady(true); //run file/link when ready to play.
            //player.setVideoDebugListener(); //for listening to resolution change and  outputing the resolution

        }else{
            simpleExoPlayerView.setVisibility(View.GONE);
        }
        return view;
    }

    public static StepInstructionFragment newInstance(int recipeId, int stepDescriptionPos){
        StepInstructionFragment newFragment = new StepInstructionFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_RECIPE_ID, recipeId);
        b.putInt(ARG_DESCRIPTION_POS, stepDescriptionPos);
        newFragment.setArguments(b);

        return newFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.release();
    }
}
