package com.example.robot.pocket_chef.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.robot.pocket_chef.R;
import com.example.robot.pocket_chef.data.RecipeData;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepInstructionFragment extends Fragment {

    //String Constants for data storage and retrieval
    private static final String TAG = StepInstructionFragment.class.getSimpleName();
    private static final String STATE_RESUME_WINDOW = "resumeWindow";
    private static final String STATE_RESUME_POSITION = "resumePosition";
    private static final String STATE_PLAYER_FULLSCREEN = "playerFullscreen";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_DESCRIPTION_POS = "descriptionPos";
    private static final String STATE_IS_PLAYING = "playState";

    //Member variables related to the recipe
    private int mRecipeId;
    private int mStepDescriptionPos;
    private static boolean mHasLoadedOnce;

    //Member variable related to the fragment view
    private Context mContext;
    private View mView;
    private MediaSource mVideoSource;
    private ImageView mThumbnailImageView;

    //Member variables related to Exoplayer
    private SimpleExoPlayerView mExoPlayerView;
    private boolean mExoPlayerFullscreen = false;
    private FrameLayout mFullScreenButton;
    private ImageView mFullScreenIcon;
    private Dialog mFullScreenDialog;
    private int mResumeWindow;
    private long mResumePosition;
    private String mVideoUrlString;
    private boolean mPlayState;



    public StepInstructionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checks to see if the fragment was started from the viewpager and get any passed in data
        if (getArguments() != null) {
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            mStepDescriptionPos = getArguments().getInt(ARG_DESCRIPTION_POS);
        }

        // Retrieving data after the fragment has been destroyed
        if (savedInstanceState != null) {
            mResumeWindow = savedInstanceState.getInt(STATE_RESUME_WINDOW);
            mResumePosition = savedInstanceState.getLong(STATE_RESUME_POSITION);
            mExoPlayerFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN);
            mRecipeId = savedInstanceState.getInt(ARG_RECIPE_ID);
            mStepDescriptionPos = savedInstanceState.getInt(ARG_DESCRIPTION_POS);
            mPlayState = savedInstanceState.getBoolean(STATE_IS_PLAYING);
        }

        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity();

        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_step_instruction, container, false);

        // Add the stepInstruction to the fragment
        TextView stepInstructionTextView = (TextView) mView
                .findViewById(R.id.step_instruction_text_view);
        stepInstructionTextView.setText(RecipeData.RECIPES.get(mRecipeId)
                .steps.get(mStepDescriptionPos).description);

        mThumbnailImageView = mView.findViewById(R.id.recipe_thumbnail_image_view);

        mExoPlayerView = mView.findViewById(R.id.player_view);
        checkForThumbnail();
        initFullscreenDialog(mContext);
        initFullscreenButton();

        return mView;
    }

    private void checkForThumbnail() {
        String thumbnailUrl;

        if(RecipeData.RECIPES.get(mRecipeId).steps.get(mStepDescriptionPos).thumbnailURL.length() > 0){
            thumbnailUrl = RecipeData.RECIPES.get(mRecipeId).steps.get(mStepDescriptionPos).thumbnailURL;
            Picasso.with(mContext)
                    .load(thumbnailUrl).into(mThumbnailImageView);
            mThumbnailImageView.setVisibility(View.VISIBLE);
            mView.findViewById(R.id.main_media_frame).setVisibility(View.GONE);
            mExoPlayerView.setVisibility(View.GONE);
        }

    }

    // Save all the member variable states into a bundle to be retrieved when the fragment starts again
    @Override
    public void onSaveInstanceState(Bundle outState) {

        mPlayState = mExoPlayerView.getPlayer().getPlayWhenReady();

        outState.putInt(STATE_RESUME_WINDOW, mResumeWindow);
        outState.putLong(STATE_RESUME_POSITION, mResumePosition);
        outState.putBoolean(STATE_PLAYER_FULLSCREEN, mExoPlayerFullscreen);
        outState.putInt(ARG_RECIPE_ID, mRecipeId);
        outState.putInt(ARG_DESCRIPTION_POS, mStepDescriptionPos);
        outState.putBoolean(STATE_IS_PLAYING, mPlayState);

        super.onSaveInstanceState(outState);
    }

    // Initialization of the exoplayer
    private void initExoPlayer() {

        // Standard code to create a simple player to set into mExoPlayerView
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(mContext), trackSelector, loadControl);
        mExoPlayerView.setPlayer(player);


        // move the seek bar to a previous position
        boolean haveResumePosition = mResumeWindow != C.INDEX_UNSET;

        if (haveResumePosition) {
            mExoPlayerView.getPlayer().seekTo(mResumeWindow, mResumePosition);

        }

        if(mPlayState){
            mExoPlayerView.getPlayer().setPlayWhenReady(mPlayState);
        }

        if (mVideoSource != null) {
            mExoPlayerView.getPlayer().prepare(mVideoSource);
        }

    }

    // Initialization of the full screen dialog which we use to put over the fragment show a full
    // screen video.
    public void initFullscreenDialog(Context c) {

        mFullScreenDialog = new Dialog(c, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen)
                    closeFullscreenDialog();
                super.onBackPressed();
            }
        };
    }

    // Initialization of the buttons for full screen mode
    private void initFullscreenButton() {

        PlaybackControlView controlView = mExoPlayerView.findViewById(R.id.exo_controller);
        mFullScreenIcon = controlView.findViewById(R.id.exo_fullscreen_icon);
        mFullScreenButton = controlView.findViewById(R.id.exo_fullscreen_button);
        mFullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else
                    closeFullscreenDialog();
            }
        });
    }

    // Showing the full screen view
    public void openFullscreenDialog() {

        if (mVideoSource != null) {
            ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
            mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_fullscreen_skrink));
            mExoPlayerFullscreen = true;
            mFullScreenDialog.show();
        }
    }

    // Closing the full screen view
    public void closeFullscreenDialog() {

        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
        ((RelativeLayout) mView.findViewById(R.id.main_media_frame)).addView(mExoPlayerView);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_fullscreen_expand));
    }


    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume called on " + mStepDescriptionPos);

        // if mExoPlayerView is null do the following code
        if (mExoPlayerView != null) {

            generateMediaSource();
        }

        if (mExoPlayerFullscreen) {
            ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
            mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_fullscreen_skrink));
            mFullScreenDialog.show();
        }


    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause called on " + mStepDescriptionPos);
        if (mExoPlayerView != null && mExoPlayerView.getPlayer() != null) {
            mResumeWindow = mExoPlayerView.getPlayer().getCurrentWindowIndex();
            mResumePosition = Math.max(0, mExoPlayerView.getPlayer().getContentPosition());
            mExoPlayerView.getPlayer().release();
        }
        if (mFullScreenDialog != null)
            mFullScreenDialog.dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach called");
        if (mExoPlayerView != null && mExoPlayerView.getPlayer() != null) {
            mResumeWindow = mExoPlayerView.getPlayer().getCurrentWindowIndex();
            mResumePosition = Math.max(0, mExoPlayerView.getPlayer().getContentPosition());
            mExoPlayerView.getPlayer().release();
        }

        if (mFullScreenDialog != null)
            mFullScreenDialog.dismiss();
    }

    private void generateMediaSource(){

        mVideoUrlString = RecipeData.RECIPES.get(mRecipeId)
                .steps.get(mStepDescriptionPos).videoURL;

        Log.d(TAG,"mVideoUrlString is " + mVideoUrlString);

        if (!mVideoUrlString.equals("")) {
            Uri mp4VideoUri = Uri.parse(mVideoUrlString);
            DefaultBandwidthMeter bandwidthMeter1 = new DefaultBandwidthMeter();
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                    Util.getUserAgent(mContext, "PocketChef"), bandwidthMeter1);
            mVideoSource = new ExtractorMediaSource(mp4VideoUri, dataSourceFactory,
                    extractorsFactory, null, null);
            mExoPlayerView.setVisibility(View.VISIBLE);
            mView.findViewById(R.id.main_media_frame).setVisibility(View.VISIBLE);
            initExoPlayer();

        } else {
            Log.d(TAG, "Views set to gone");
            mExoPlayerView.setVisibility(View.GONE);
            mView.findViewById(R.id.main_media_frame).setVisibility(View.GONE);
            mFullScreenDialog.dismiss();
        }

    }

    public static StepInstructionFragment newInstance(int recipeId, int stepDescriptionPos) {

        StepInstructionFragment newFragment = new StepInstructionFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_RECIPE_ID, recipeId);
        b.putInt(ARG_DESCRIPTION_POS, stepDescriptionPos);
        newFragment.setArguments(b);
        return newFragment;
    }

}



