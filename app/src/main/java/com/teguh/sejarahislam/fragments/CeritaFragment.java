package com.teguh.sejarahislam.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.fragment.app.Fragment;

import com.teguh.sejarahislam.Buku2Activity;
import com.teguh.sejarahislam.BukuActivity;
import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Shared;


public class CeritaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.fragment_cerita, container, false);
        View abuAbdillah = view.findViewById(R.id.theme_cerita_container);
        View abdulQodir = view.findViewById(R.id.theme_game_container);

        abuAbdillah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BukuActivity.class));
            }
        });

        abdulQodir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Buku2Activity.class));

            }
        });
        /**
         * Imporove performance first!!!
         */
        animateShow(abuAbdillah);
        animateShow(abdulQodir);
//        animateShow(emoji);

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