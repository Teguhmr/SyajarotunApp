package com.teguh.sejarahislam.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.fragment.app.Fragment;

import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.events.ui.CeritaEvent;
import com.teguh.sejarahislam.events.ui.GamesEvent;
import com.teguh.sejarahislam.themes.Theme;
import com.teguh.sejarahislam.themes.Themes;

public class Story_GameFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.fragment_story__game, container, false);
        View cerita = view.findViewById(R.id.theme_cerita_container);
        View game = view.findViewById(R.id.theme_game_container);

        final Theme themeCerita = Themes.createCeritaTheme();
        final Theme themeGame = Themes.createGameTheme();
        cerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new CeritaEvent(themeCerita));
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new GamesEvent(themeGame));
            }
        });
        /**
         * Imporove performance first!!!
         */
        animateShow(cerita);
        animateShow(game);
        return view;
    }

    private void animateShow(View view) {
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(animatorScaleX, animatorScaleY);
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }


}