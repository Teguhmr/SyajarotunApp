package com.teguh.sejarahislam.engine;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ImageView;

import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.events.EventObserverAdapter;
import com.teguh.sejarahislam.events.ui.BackGameEvent;
import com.teguh.sejarahislam.events.ui.CeritaEvent;
import com.teguh.sejarahislam.events.ui.Cerita_GameEvent;
import com.teguh.sejarahislam.events.ui.DifficultySelectedEvent;
import com.teguh.sejarahislam.events.ui.GamesEvent;
import com.teguh.sejarahislam.events.ui.LevelGameEvent;
import com.teguh.sejarahislam.events.ui.NextGameEvent;
import com.teguh.sejarahislam.events.ui.ResetBackgroundEvent;
import com.teguh.sejarahislam.events.ui.ResetBackgroundGameEvent;
import com.teguh.sejarahislam.events.ui.StartEvent;
import com.teguh.sejarahislam.events.ui.ThemeSelectedEvent;
import com.teguh.sejarahislam.model.BoardConfiguration;
import com.teguh.sejarahislam.model.Game;
import com.teguh.sejarahislam.themes.Theme;
import com.teguh.sejarahislam.themes.Themes;
import com.teguh.sejarahislam.ui.PopupManager;
import com.teguh.sejarahislam.utils.Utils;

public class Engine extends EventObserverAdapter {

	private static Engine mInstance = null;
	public Game mPlayingGame = null;
	private ScreenController mScreenController;
	private Theme mSelectedTheme;
	private ImageView mBackgroundImage;
	private Handler mHandler;

	private Engine() {
		mScreenController = ScreenController.getInstance();
		mHandler = new Handler();
	}

	public static Engine getInstance() {
		if (mInstance == null) {
			mInstance = new Engine();
		}
		return mInstance;
	}

	public void start() {
		Shared.eventBus.listen(DifficultySelectedEvent.TYPE, this);
		Shared.eventBus.listen(StartEvent.TYPE, this);
		Shared.eventBus.listen(ThemeSelectedEvent.TYPE, this);
		Shared.eventBus.listen(BackGameEvent.TYPE, this);
		Shared.eventBus.listen(NextGameEvent.TYPE, this);
		Shared.eventBus.listen(ResetBackgroundEvent.TYPE, this);
		Shared.eventBus.listen(Cerita_GameEvent.TYPE, this);
		Shared.eventBus.listen(CeritaEvent.TYPE, this);
		Shared.eventBus.listen(GamesEvent.TYPE, this);
		Shared.eventBus.listen(LevelGameEvent.TYPE, this);
		Shared.eventBus.listen(ResetBackgroundGameEvent.TYPE, this);

	}

	public void stop() {
		mPlayingGame = null;
		mBackgroundImage.setImageDrawable(null);
		mBackgroundImage = null;
		mHandler.removeCallbacksAndMessages(null);
		mHandler = null;

		Shared.eventBus.unlisten(LevelGameEvent.TYPE, this);
		Shared.eventBus.unlisten(GamesEvent.TYPE, this);
		Shared.eventBus.unlisten(CeritaEvent.TYPE, this);
		Shared.eventBus.unlisten(Cerita_GameEvent.TYPE, this);
		Shared.eventBus.unlisten(DifficultySelectedEvent.TYPE, this);
		Shared.eventBus.unlisten(StartEvent.TYPE, this);
		Shared.eventBus.unlisten(ThemeSelectedEvent.TYPE, this);
		Shared.eventBus.unlisten(BackGameEvent.TYPE, this);
		Shared.eventBus.unlisten(NextGameEvent.TYPE, this);
		Shared.eventBus.unlisten(ResetBackgroundEvent.TYPE, this);
		Shared.eventBus.unlisten(ResetBackgroundGameEvent.TYPE, this);

		mInstance = null;
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		Drawable drawable = mBackgroundImage.getDrawable();
		if (drawable != null) {
			((TransitionDrawable) drawable).reverseTransition(2000);
		} else {
			new AsyncTask<Void, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(Void... params) {
					Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
					return bitmap;
				}

				protected void onPostExecute(Bitmap bitmap) {
					mBackgroundImage.setImageBitmap(bitmap);
				};

			}.execute();
		}
	}
	@Override
	public void onEvent(ResetBackgroundGameEvent event) {
		Drawable drawable = mBackgroundImage.getDrawable();
		if (drawable != null) {
			((TransitionDrawable) drawable).reverseTransition(2000);
		} else {
			new AsyncTask<Void, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(Void... params) {
					Bitmap bitmap = Utils.scaleDown(R.drawable.game, Utils.screenWidth(), Utils.screenHeight());
					return bitmap;
				}

				protected void onPostExecute(Bitmap bitmap) {
					mBackgroundImage.setImageBitmap(bitmap);
				};

			}.execute();
		}
	}

	@Override
	public void onEvent(StartEvent event) {
		mScreenController.openScreen(ScreenController.Screen.STORY_GAME);
	}
	@Override
	public void onEvent(CeritaEvent event) {
		mSelectedTheme = event.theme;
		mScreenController.openScreen(ScreenController.Screen.CERITA);
		AsyncTask<Void, Void, TransitionDrawable> task = new AsyncTask<Void, Void, TransitionDrawable>() {

			@Override
			protected TransitionDrawable doInBackground(Void... params) {
				Bitmap bitmap = Utils.scaleDown(R.drawable.cerita, Utils.screenWidth(), Utils.screenHeight());
				Bitmap backgroundImage = Themes.getBackgroundImage(mSelectedTheme);
//				backgroundImage = Utils.crop(backgroundImage, Utils.screenHeight(), Utils.screenWidth());
				Drawable backgrounds[] = new Drawable[2];
				backgrounds[0] = new BitmapDrawable(Shared.context.getResources(), bitmap);
				backgrounds[1] = new BitmapDrawable(Shared.context.getResources(), backgroundImage);
				TransitionDrawable crossfader = new TransitionDrawable(backgrounds);
				return crossfader;
			}

			@Override
			protected void onPostExecute(TransitionDrawable result) {
				super.onPostExecute(result);
				mBackgroundImage.setImageDrawable(result);
				result.startTransition(2000);
			}
		};
		task.execute();	}


	@Override
	public void onEvent(GamesEvent event) {
		mSelectedTheme = event.theme;
		mScreenController.openScreen(ScreenController.Screen.GAMES);
		AsyncTask<Void, Void, TransitionDrawable> task = new AsyncTask<Void, Void, TransitionDrawable>() {

			@Override
			protected TransitionDrawable doInBackground(Void... params) {
				Bitmap bitmap = Utils.scaleDown(R.drawable.game, Utils.screenWidth(), Utils.screenHeight());
				Bitmap backgroundImage = Themes.getBackgroundImage(mSelectedTheme);
//				backgroundImage = Utils.crop(backgroundImage, Utils.screenHeight(), Utils.screenWidth());
				Drawable backgrounds[] = new Drawable[2];
				backgrounds[0] = new BitmapDrawable(Shared.context.getResources(), bitmap);
				backgrounds[1] = new BitmapDrawable(Shared.context.getResources(), backgroundImage);
				TransitionDrawable crossfader = new TransitionDrawable(backgrounds);
				return crossfader;
			}

			@Override
			protected void onPostExecute(TransitionDrawable result) {
				super.onPostExecute(result);
				mBackgroundImage.setImageDrawable(result);
				result.startTransition(2000);
			}
		};
		task.execute();	}

	@Override
	public void onEvent(NextGameEvent event) {
		PopupManager.closePopup();
		int difficulty = mPlayingGame.boardConfiguration.difficulty;
		if (mPlayingGame.gameState.achievedStars <= 3 && difficulty < 4) {
			difficulty++;
		}
		Shared.eventBus.notify(new DifficultySelectedEvent(difficulty));
	}

	@Override
	public void onEvent(BackGameEvent event) {
		PopupManager.closePopup();
		mScreenController.openScreen(ScreenController.Screen.LEVEL_GAME);
	}


	@Override
	public void onEvent(LevelGameEvent event) {
		mSelectedTheme = event.theme;
		mScreenController.openScreen(ScreenController.Screen.LEVEL_GAME);
		AsyncTask<Void, Void, TransitionDrawable> task = new AsyncTask<Void, Void, TransitionDrawable>() {

			@Override
			protected TransitionDrawable doInBackground(Void... params) {
				Bitmap bitmap = Utils.scaleDown(R.drawable.game, Utils.screenWidth(), Utils.screenHeight());
				Bitmap backgroundImage = Themes.getBackgroundImage(mSelectedTheme);
//				backgroundImage = Utils.crop(backgroundImage, Utils.screenHeight(), Utils.screenWidth());
				Drawable backgrounds[] = new Drawable[2];
				backgrounds[0] = new BitmapDrawable(Shared.context.getResources(), bitmap);
				backgrounds[1] = new BitmapDrawable(Shared.context.getResources(), backgroundImage);
				TransitionDrawable crossfader = new TransitionDrawable(backgrounds);
				return crossfader;
			}

			@Override
			protected void onPostExecute(TransitionDrawable result) {
				super.onPostExecute(result);
				mBackgroundImage.setImageDrawable(result);
				result.startTransition(2000);
			}
		};
		task.execute();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		mPlayingGame = new Game();
		mPlayingGame.boardConfiguration = new BoardConfiguration(event.difficulty);
		mPlayingGame.theme = mSelectedTheme;

		// arrange board
//		arrangeBoard();

		if (mPlayingGame.boardConfiguration.difficulty == 1 && mPlayingGame.theme.id == 1){
			mPlayingGame.setKeys(new String[]{"S", "U", "I", "F", "L", "A", "K", "O", "Q", "L"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(4);
			mPlayingGame.setTextAnswer("SUFI");
			mPlayingGame.setTextQuestion("Abdul Qadir Jaelani merupakan tokoh ?");
		} else if (mPlayingGame.boardConfiguration.difficulty == 2 && mPlayingGame.theme.id == 1){
			mPlayingGame.setKeys(new String[]{"J", "U", "J", "R", "N", "A", "K", "U", "E", "L"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(9);
			mPlayingGame.setTextAnswer("KEJUJURAN");
			mPlayingGame.setTextQuestion("Apa nasihat yang diberikan oleh Ibu dari Abdul Qadir yang ia tanamkan saat perjalanan ?");
		}else if (mPlayingGame.boardConfiguration.difficulty == 3 && mPlayingGame.theme.id == 1){
			mPlayingGame.setKeys(new String[]{"S", "A", "U", "D", "A", "A", "R", "G", "E", "L"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(8);
			mPlayingGame.setTextAnswer("SAUDAGAR");
			mPlayingGame.setTextQuestion("Siapa yang menjamu Abdul Qadir saat perjalanan ?");
		}else if (mPlayingGame.boardConfiguration.difficulty == 4 && mPlayingGame.theme.id == 1){
			mPlayingGame.setKeys(new String[]{"M", "M", "A", "A", "F", "A", "E", "K", "A", "N"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(9);
			mPlayingGame.setTextAnswer("MEMAAFKAN");
			mPlayingGame.setTextQuestion("Apa sikap yang ditunjukkan oleh Abdul Qadir saat perampok meminta maaf di kediaman Saudagar ?");
		}

		if (mPlayingGame.boardConfiguration.difficulty == 1 && mPlayingGame.theme.id == 2){
			mPlayingGame.setKeys(new String[]{"G", "A", "A", "H", "J", "A", "A", "Q", "F", "M"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(5);
			mPlayingGame.setTextAnswer("GAJAH");
			mPlayingGame.setTextQuestion("Daging hewan apa yang tidak akan dimakan pada Nadzar yang diucapkan Abu Abdillah ?");
		}else if (mPlayingGame.boardConfiguration.difficulty == 2 && mPlayingGame.theme.id == 2){
			mPlayingGame.setKeys(new String[]{"K", "L", "A", "E", "P", "A", "A", "N", "R", "M"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(9);
			mPlayingGame.setTextAnswer("KELAPARAN");
			mPlayingGame.setTextQuestion("Apa yang diderita penumpang kapal saat terdampar di pulau ?");
		}else if (mPlayingGame.boardConfiguration.difficulty == 3 && mPlayingGame.theme.id == 2){
			mPlayingGame.setKeys(new String[]{"P", "E", "R", "E", "K", "B", "U", "N", "A", "N"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(10);
			mPlayingGame.setTextAnswer("PERKEBUNAN");
			mPlayingGame.setTextQuestion("Dimana induk gajah menurunkan Abu Abdillah ?");
		}else if (mPlayingGame.boardConfiguration.difficulty == 4 && mPlayingGame.theme.id == 2){
			mPlayingGame.setKeys(new String[]{"S", "E", "M", "A", "N", "B", "U", "A", "M", "L"});
			mPlayingGame.getKeys();
			mPlayingGame.setMaxPresCounter(7);
			mPlayingGame.setTextAnswer("SEMALAM");
			mPlayingGame.setTextQuestion("Berapa lama waktu yang ditempuh oleh Abu Abdillah bersama induk gajah dari pantai ke desa ?");
		}

		// start the screen
		mScreenController.openScreen(ScreenController.Screen.GAME);
	}

	public Game getActiveGame() {
		return mPlayingGame;
	}

	public Theme getSelectedTheme() {
		return mSelectedTheme;
	}

	public void setBackgroundImageView(ImageView backgroundImage) {
		mBackgroundImage = backgroundImage;
	}
}
