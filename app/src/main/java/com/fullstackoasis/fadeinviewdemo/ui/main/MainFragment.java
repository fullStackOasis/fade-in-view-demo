package com.fullstackoasis.fadeinviewdemo.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullstackoasis.fadeinviewdemo.R;

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getCanonicalName();

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


// Create the scenes

        new Handler().postDelayed(new Runnable() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        doFadeIn();
                    }
                });

            }
        }, 6000);

    }

    private void doFadeIn() {
        Log.d(TAG, "doFadeIn");
        TextView tv = getActivity().findViewById(R.id.message);
        tv.setText("Enjoy your latte!");
// Create the scene root for the scenes in this app
        ViewGroup sceneRoot = (ViewGroup) getActivity().findViewById(R.id.scene_root);
        Scene aScene = Scene.getSceneForLayout(sceneRoot, R.layout.main_fragment_empty, getContext());
        Scene anotherScene =
                Scene.getSceneForLayout(sceneRoot, R.layout.main_fragment_scene2_latte, getContext());
        Transition fadeTransition =
                TransitionInflater.from(getContext()).
                        inflateTransition(R.transition.fade_transition);
        TransitionManager.go(anotherScene, fadeTransition);
    }
}