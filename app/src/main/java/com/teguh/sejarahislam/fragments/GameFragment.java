package com.teguh.sejarahislam.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Memory;
import com.teguh.sejarahislam.common.Music;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.events.engine.GameWonEvent;
import com.teguh.sejarahislam.model.Game;
import com.teguh.sejarahislam.model.GameState;
import com.teguh.sejarahislam.services.BackgroundMusicGamesService;
import com.teguh.sejarahislam.services.BackgroundMusicService;
import com.teguh.sejarahislam.ui.BoardView;
import com.teguh.sejarahislam.ui.PopupManager;
import com.teguh.sejarahislam.utils.Clock;
import com.teguh.sejarahislam.utils.FontLoader;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class GameFragment extends BaseFragment {

	private BoardView mBoardView;
	private TextView mTime;
	private ImageView mTimeImage;
//	private LinearLayout ads;
	private final int presCounter = 0;
	private int maxPresCounter ;
	TextView textScreen, textQuestion, textTitle;
	Button buttonClear;
	Animation smallbigforth;
	Game mPlayingGame = Shared.engine.getActiveGame();
	LinearLayout linearLayout, linearLayout1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup view = (ViewGroup) inflater.inflate(R.layout.game_fragment, container, false);
		view.setClipChildren(false);
		((ViewGroup) view.findViewById(R.id.game_board)).setClipChildren(false);
		mTime = (TextView) view.findViewById(R.id.time_bar_text);
		mTimeImage = (ImageView) view.findViewById(R.id.time_bar_image);
		FontLoader.setTypeface(Shared.context, new TextView[]{mTime}, FontLoader.Font.GROBOLD);
		mBoardView = BoardView.fromXml(getActivity().getApplicationContext(), view);
		FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.game_container);
		frameLayout.addView(mBoardView);
		frameLayout.setClipChildren(false);

		// build board
		buildBoard();
//		Shared.eventBus.listen(FlipDownCardsEvent.TYPE, this);
//		Shared.eventBus.listen(HidePairCardsEvent.TYPE, this);
		Shared.eventBus.listen(GameWonEvent.TYPE, this);
		BackgroundMusicService.mediaPlayer.pause();

		return view;
	}


	@Override
	public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		smallbigforth = AnimationUtils.loadAnimation(getActivity(), R.anim.smallbigforth);
		//for (String key : keys) {            addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.editText)));        }

		for (int i = mPlayingGame.getKeys().length -1 ; i >= 0 ; i--)
		{
			String key = mPlayingGame.getKeys()[i];

			if(i>4)
				addView(((LinearLayout) mBoardView.findViewById(R.id.layoutParent)), key, ((EditText) mBoardView.findViewById(R.id.editText)));
			else
				addView(((LinearLayout) mBoardView.findViewById(R.id.layoutParent1)), key, ((EditText) mBoardView.findViewById(R.id.editText)));
		}

		maxPresCounter = mPlayingGame.getMaxPresCounter();
		Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/FredokaOneRegular.ttf");

		buttonClear = mBoardView.findViewById(R.id.button_clear);
		buttonClear.setTypeface(typeface);
		buttonClear.setText("RESET");

		PushDownAnim.setPushDownAnimTo(buttonClear)
				.setScale( MODE_SCALE,
						1.2f)
				.setDurationPush( 100 )
				.setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
				.setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
				.setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						((EditText) mBoardView.findViewById(R.id.editText)).setText("");
						mPlayingGame.presCounter = 0;
						Game.keys = shuffleArray(mPlayingGame.getKeys());

						linearLayout.removeAllViews();
						linearLayout1.removeAllViews();
						//for (String key : keys) {                    }
						for (int i = mPlayingGame.getKeys().length -1 ; i >= 0 ; i--)
						{
							String key = mPlayingGame.getKeys()[i];

							if(i>4)
								addView(((LinearLayout) mBoardView.findViewById(R.id.layoutParent)), key, ((EditText) mBoardView.findViewById(R.id.editText)));
							else
								addView(((LinearLayout) mBoardView.findViewById(R.id.layoutParent1)), key, ((EditText) mBoardView.findViewById(R.id.editText)));
						}
					}
				});

	}


	private void addView(LinearLayout viewParent, final String text, final EditText editText) {
		LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT
		);

		linearLayoutParams.rightMargin = 10;
		linearLayoutParams.leftMargin = 10;

//		int _10 = R.dimen._10sdp;
//		linearLayoutParams.width = _90;
//		linearLayoutParams.height = _90;

		final TextView textView = new TextView(getActivity());

		textView.setLayoutParams(linearLayoutParams);
		textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
		textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
		textView.setGravity(Gravity.CENTER);
		textView.setText(text);
		textView.setWidth(90);
		textView.setHeight(90);
		textView.setTextSize(38);

		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
			textView.setWidth(120);
			textView.setHeight(120);
			textView.setTextSize(36);

		}else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			textView.setWidth(120);
			textView.setHeight(120);
			textView.setTextSize(38);

		}
//		textView.setPadding(5,0,5,8);
		textView.setClickable(true);
		textView.setFocusable(true);

		Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/FredokaOneRegular.ttf");

		textQuestion = (TextView) mBoardView.findViewById(R.id.textQuestion);
		textScreen = (TextView) mBoardView.findViewById(R.id.textScreen);
		textTitle = (TextView) mBoardView.findViewById(R.id.textTitle);
		textQuestion.setText(mPlayingGame.getTextQuestion());

		linearLayout = mBoardView.findViewById(R.id.layoutParent);
		linearLayout1 = mBoardView.findViewById(R.id.layoutParent1);


		textQuestion.setTypeface(typeface);
		textScreen.setTypeface(typeface);
		textTitle.setTypeface(typeface);
		editText.setTypeface(typeface);
		textView.setTypeface(typeface);

		textView.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("SetTextI18n")
			@Override
			public void onClick(View v) {
				Music.clickText();
				if(mPlayingGame.presCounter < mPlayingGame.getMaxPresCounter()) {
					if (mPlayingGame.presCounter == 0)
						editText.setText("");

					editText.setText(editText.getText().toString() + text);
					textView.startAnimation(smallbigforth);
					textView.setEnabled(false);
					textView.animate().alpha(0).setDuration(300);
					mPlayingGame.presCounter++;

					if (mPlayingGame.presCounter == mPlayingGame.getMaxPresCounter())
						doValidate();
				}
			}
		});


		viewParent.addView(textView);


	}

	@Override
	public void onDestroy() {
		Shared.eventBus.unlisten(GameWonEvent.TYPE, this);
		Clock.getInstance().pause();
		super.onDestroy();
	}


	@Override
	public void onResume() {
		super.onResume();
		Clock.getInstance().resume();
		playBackgroundMusic();
	}


	@Override
	public void onStop() {
		super.onStop();
		Clock.getInstance().pause();
		getActivity().stopService(new Intent(getActivity(), BackgroundMusicGamesService.class));
	}


	public void playBackgroundMusic() {
		Intent intent = new Intent(getActivity(), BackgroundMusicGamesService.class);
		getActivity().startService(intent);
	}

	private void doValidate() {
		mPlayingGame.presCounter = 0;

		EditText editText = mBoardView.findViewById(R.id.editText);
		linearLayout = mBoardView.findViewById(R.id.layoutParent);
		linearLayout1 = mBoardView.findViewById(R.id.layoutParent1);

		if(editText.getText().toString().equals(mPlayingGame.getTextAnswer())) {
//            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

//			Intent a = new Intent(MainActivity.this,BossAct.class);
//			startActivity(a);
			FancyToast.makeText(getActivity(),"KAMU BENAR !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
			Music.playCorrent();
			popUpWon();
			editText.setText("");
		} else {
			FancyToast.makeText(getActivity(),"SALAH",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
			editText.setText("");
			Game.keys = shuffleArray(mPlayingGame.getKeys());
			Music.wrongAnswer();
		}

//        linearLayout.removeAllViews();
//        for (String key : keys) {
//            addView(linearLayout, key, editText);
//        }
		linearLayout.removeAllViews();
		linearLayout1.removeAllViews();
		//for (String key : keys) {                    }
		for (int i = mPlayingGame.getKeys().length -1 ; i >= 0 ; i--)
		{
			String key = mPlayingGame.getKeys()[i];
			if(i>4)
				addView(linearLayout, key, editText);
			else
				addView(linearLayout1, key, editText);
		}

	}

	private String[] shuffleArray(String[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}


	private void buildBoard() {
		Game game = Shared.engine.getActiveGame();
		int time = game.boardConfiguration.time;
		setTime(time);

		startClock(time);
	}

	private void setTime(int time) {
		int min = time / 60;
		int sec = time - min*60;
		mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
	}

	private void startClock(int sec) {
		Clock clock = Clock.getInstance();
		clock.startTimer(sec*1000, 1000, new Clock.OnTimerCount() {
			
			@Override
			public void onTick(long millisUntilFinished) {
				setTime((int) (millisUntilFinished/1000));
			}
			
			@Override
			public void onFinish() {
				setTime(0);
				popUpWon();
				Music.gameOver();
			}
		});
	}

	@Override
	public void onEvent(GameWonEvent event) {
		mTime.setVisibility(View.GONE);
		mTimeImage.setVisibility(View.GONE);
		mTimeImage.setVisibility(View.GONE);
		textScreen.setVisibility(View.GONE);
		textQuestion.setVisibility(View.GONE);
		textTitle.setVisibility(View.GONE);
		mBoardView.setVisibility(View.GONE);
		PopupManager.showPopupWon(event.gameState);
	}

	private void popUpWon(){
		Game game = Shared.engine.getActiveGame();

		int passedSeconds = (int) (Clock.getInstance().getPassedTime() / 1000);
		Clock.getInstance().pause();
		int totalTime = game.boardConfiguration.time;
		GameState gameState = new GameState();
		game.gameState = gameState;
		// remained seconds
		gameState.remainedSeconds = totalTime - passedSeconds;
		gameState.passedSeconds = passedSeconds;

		// calc stars
		if (passedSeconds <= totalTime / 2) {
			gameState.achievedStars = 3;
		} else if (passedSeconds <= totalTime - totalTime / 5) {
			gameState.achievedStars = 2;
		} else if (passedSeconds < totalTime) {
			gameState.achievedStars = 1;
		} else {
			gameState.achievedStars = 0;
		}

		// calc score
		gameState.achievedScore = game.boardConfiguration.difficulty * gameState.remainedSeconds ;

		// save to memory
		Memory.save(game.theme.id, game.boardConfiguration.difficulty, gameState.achievedStars);
		Memory.saveTime(game.theme.id, game.boardConfiguration.difficulty ,gameState.passedSeconds);

		Shared.eventBus.notify(new GameWonEvent(gameState), 900);

	}


}
