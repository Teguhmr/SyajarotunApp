package com.teguh.sejarahislam.fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.adapter.AdapterAboutView;
import com.teguh.sejarahislam.adapter.DepthPageTransformer;
import com.teguh.sejarahislam.common.About;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.events.ui.StartEvent;
import com.teguh.sejarahislam.ui.PopupManager;
import com.teguh.sejarahislam.utils.Utils;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class MenuFragment extends Fragment {

	private ImageView mTitle;
	private ImageView mStartGameButton;
	private ImageView mStartButtonLights;
	private ImageView mTooltip;
	private ImageView mSettingsGameButton;
	private ImageView mAboutButton;
	private BottomSheetDialog bottomSheetDialog;
	private ViewPager viewPager;
	private AdapterAboutView adapterAboutView;
	private List<About> mList;
	private int mCurrentPage;
	private Button btnNext, btnPrev;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_fragment, container, false);
		final ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i1) {

			}

			@Override
			public void onPageSelected(int i) {
				mCurrentPage = i;

				if (i == 0) {

					btnNext.setEnabled(true);
					btnPrev.setEnabled(false);
					btnPrev.setVisibility(View.INVISIBLE);

				} else if (i == mList.size() - 1) {

					btnNext.setEnabled(false);
					btnPrev.setEnabled(true);
					btnPrev.setVisibility(View.VISIBLE);
					btnNext.setVisibility(View.INVISIBLE);

				} else {

					btnNext.setEnabled(true);
					btnPrev.setEnabled(true);
					btnPrev.setVisibility(View.VISIBLE);
					btnNext.setVisibility(View.VISIBLE);


				}

			}
			@Override
			public void onPageScrollStateChanged(int i) {

			}
		};

		mTitle = (ImageView) view.findViewById(R.id.title);
		mStartGameButton = (ImageView) view.findViewById(R.id.start_game_button);
		mSettingsGameButton = (ImageView) view.findViewById(R.id.settings_game_button);
		mSettingsGameButton.setSoundEffectsEnabled(false);
		mSettingsGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupManager.showPopupSettings();
			}
		});
		mAboutButton = (ImageView) view.findViewById(R.id.google_play_button);
		mAboutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bottomSheetDialog = new BottomSheetDialog(v.getContext(),
						R.style.BottomSheetDialogTheme);
				View bottomSheetView = LayoutInflater.from(v.getContext()).inflate(
						R.layout.layout_bottom_sheet,
						(RelativeLayout) v.findViewById(R.id.bottomSheetContainer),
						false

				);

				btnNext = bottomSheetView.findViewById(R.id.btn_next);
				btnPrev = bottomSheetView.findViewById(R.id.btn_prev);
				btnPrev.setVisibility(View.INVISIBLE);

				PushDownAnim.setPushDownAnimTo(btnNext)
						.setScale( MODE_SCALE,
								1.2f)
						.setDurationPush( 100 )
						.setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
						.setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
						.setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								viewPager.setCurrentItem(mCurrentPage + 1);

							}
						});
				PushDownAnim.setPushDownAnimTo(btnPrev)
						.setScale( MODE_SCALE,
								1.2f)
						.setDurationPush( 100 )
						.setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
						.setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
						.setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								viewPager.setCurrentItem(mCurrentPage - 1);

							}
						});

				mList = new ArrayList<>();
				mList.add(new About(R.drawable.abdulqadir_profil_1, 1, "Teguh Muhammad Ridwan", "11190910000061", "teguh.mr19@mhs.uinjkt.ac.id", "MOBILE APPS DEVELOPER"));
				mList.add(new About(R.drawable.abdulqadir_profil_2, 2, "Fazriansyah", "11190910000062", "fazrian.syah19@mhs.uinjkt.ac.id", "REQUIREMENT GATHERING"));
				mList.add(new About(R.drawable.abdulqadir_profil_3, 3, "Ikhsan Adi Putra", "11190910000050", "ikhsan.adi19@mhs.uinjkt.ac.id", "DESIGN ANALYST"));
				mList.add(new About(R.drawable.abdulqadir_profil_4, 4, "Alifiar Hazazi Qisthan", "11190910000057", "alifiar.qisthan19@mhs.uinjkt.ac.id", "SYSTEM ANALYST"));
				mList.add(new About(R.drawable.abdulqadir_profil_5, 5, "Muhammad Alwi Renaldy", "11180910000123", "muhammadalwi.renaldy18@mhs.uinjkt.ac.id", "TESTING"));
				adapterAboutView = new AdapterAboutView(getActivity(), mList);
				viewPager = bottomSheetView.findViewById(R.id.viewPagerAbout);
				viewPager.setAdapter(adapterAboutView);
				viewPager.addOnPageChangeListener(viewListener);
				viewPager.setPageTransformer(true, new DepthPageTransformer());
				bottomSheetDialog.setContentView(bottomSheetView);
				bottomSheetDialog.show();
			}
		});


		mStartButtonLights = (ImageView) view.findViewById(R.id.start_game_button_lights);
		mTooltip = (ImageView) view.findViewById(R.id.tooltip);
		mStartGameButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// animate title from place and navigation buttons from place
				animateAllAssetsOff(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						Shared.eventBus.notify(new StartEvent());
					}
				});
			}
		});

		startLightsAnimation();
		startTootipAnimation();

		// play background music
//		Music.playBackgroundMusic();
		return view;
	}


	protected void animateAllAssetsOff(AnimatorListenerAdapter adapter) {
		// title
		// 120dp + 50dp + buffer(30dp)
		ObjectAnimator titleAnimator = ObjectAnimator.ofFloat(mTitle, "translationY", Utils.px(-200));
		titleAnimator.setInterpolator(new AccelerateInterpolator(2));
		titleAnimator.setDuration(300);

		// lights
		ObjectAnimator lightsAnimatorX = ObjectAnimator.ofFloat(mStartButtonLights, "scaleX", 0f);
		ObjectAnimator lightsAnimatorY = ObjectAnimator.ofFloat(mStartButtonLights, "scaleY", 0f);

		// tooltip
		ObjectAnimator tooltipAnimator = ObjectAnimator.ofFloat(mTooltip, "alpha", 0f);
		tooltipAnimator.setDuration(100);

		// settings button
		ObjectAnimator settingsAnimator = ObjectAnimator.ofFloat(mSettingsGameButton, "translationY", Utils.px(120));
		settingsAnimator.setInterpolator(new AccelerateInterpolator(2));
		settingsAnimator.setDuration(300);

		// about button
		ObjectAnimator googlePlayAnimator = ObjectAnimator.ofFloat(mAboutButton, "translationY", Utils.px(120));
		googlePlayAnimator.setInterpolator(new AccelerateInterpolator(2));
		googlePlayAnimator.setDuration(300);

		// start button
		ObjectAnimator startButtonAnimator = ObjectAnimator.ofFloat(mStartGameButton, "translationY", Utils.px(130));
		startButtonAnimator.setInterpolator(new AccelerateInterpolator(2));
		startButtonAnimator.setDuration(300);

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(titleAnimator, lightsAnimatorX, lightsAnimatorY, tooltipAnimator, settingsAnimator, googlePlayAnimator, startButtonAnimator);
		animatorSet.addListener(adapter);
		animatorSet.start();
	}

	private void startTootipAnimation() {
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTooltip, "scaleY", 0.8f);
		scaleY.setDuration(200);
		ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(mTooltip, "scaleY", 1f);
		scaleYBack.setDuration(500);
		scaleYBack.setInterpolator(new BounceInterpolator());
		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setStartDelay(1000);
		animatorSet.playSequentially(scaleY, scaleYBack);
		animatorSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				animatorSet.setStartDelay(2000);
				animatorSet.start();
			}
		});
		mTooltip.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

	private void startLightsAnimation() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(mStartButtonLights, "rotation", 0f, 360f);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.setDuration(6000);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		mStartButtonLights.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animator.start();
	}

}
