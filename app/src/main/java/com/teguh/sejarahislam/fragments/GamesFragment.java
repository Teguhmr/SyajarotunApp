package com.teguh.sejarahislam.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Memory;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.events.ui.LevelGameEvent;
import com.teguh.sejarahislam.themes.Theme;
import com.teguh.sejarahislam.themes.Themes;

import java.util.Locale;

public class GamesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.fragment_games, container, false);
        View abuAbdillah = view.findViewById(R.id.theme_abuabdillah_battle);
        View abdulQodir = view.findViewById(R.id.theme_abdulqodir_battle);

        final Theme themeAbdulQodir = Themes.createBattleAbdulQodirTheme();
        setStars((ImageView) abdulQodir.findViewById(R.id.theme_abdulqodir_battle), themeAbdulQodir, "abdulqodir");
        final Theme themeAbuAbdillah = Themes.createBattleAbuAbdillahTheme();
        setStars((ImageView) abuAbdillah.findViewById(R.id.theme_abuabdillah_battle), themeAbuAbdillah, "abuabdillah");

        abuAbdillah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new LevelGameEvent(themeAbuAbdillah));
            }
        });

        abdulQodir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new LevelGameEvent(themeAbdulQodir));
            }
        });
//
//        emoji.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Shared.eventBus.notify(new ThemeSelectedEvent(themeEmoji));
//            }
//        });

        /**
         * Imporove performance first!!!
         */
        animateShow(abuAbdillah);
        animateShow(abdulQodir);
//        animateShow(emoji);

        return view;
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        playBackgroundMusic();
//    }
//
//    public void playBackgroundMusic() {
//        Intent intent = new Intent(getActivity(), BackgroundMusicService.class);
//        getActivity().startService(intent);
//    }

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

    private void setStars(ImageView imageView, Theme theme, String type) {
        int sum = 0;
        for (int difficulty = 1; difficulty <= 4; difficulty++) {
            sum += Memory.getHighStars(theme.id, difficulty);
        }
        int num = sum / 4;
        if (num != 0) {
            String drawableResourceName = String.format(Locale.US, type + "_game_stars_%d", num);
            int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
            imageView.setImageResource(drawableResourceId);
        }
    }
}