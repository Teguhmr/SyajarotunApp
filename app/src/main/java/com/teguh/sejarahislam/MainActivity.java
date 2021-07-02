package com.teguh.sejarahislam;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.engine.Engine;
import com.teguh.sejarahislam.engine.ScreenController;
import com.teguh.sejarahislam.events.EventBus;
import com.teguh.sejarahislam.events.ui.BackGameEvent;
import com.teguh.sejarahislam.services.BackgroundMusicService;
import com.teguh.sejarahislam.ui.PopupManager;
import com.teguh.sejarahislam.utils.Utils;

public class MainActivity extends FragmentActivity {

	private ImageView mBackgroundImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Shared.context = getApplicationContext();
		Shared.engine = Engine.getInstance();
		Shared.eventBus = EventBus.getInstance();

		setContentView(R.layout.activity_main);
		mBackgroundImage = (ImageView) findViewById(R.id.background_image);

		Shared.activity = this;
		Shared.engine.start();
		Shared.engine.setBackgroundImageView(mBackgroundImage);

		// set background
		setBackgroundImage();

		// set menu
		ScreenController.getInstance().openScreen(ScreenController.Screen.MENU);
	}

	@Override
	protected void onDestroy() {
		Shared.engine.stop();
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		if (PopupManager.isShown()) {
			PopupManager.closePopup();
			if (ScreenController.getLastScreen() == ScreenController.Screen.GAME) {
				Shared.eventBus.notify(new BackGameEvent());

			}
		} else if (ScreenController.getInstance().onBack()) {
			super.onBackPressed();
		}
	}

	private void setBackgroundImage() {
		Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
//		bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
//		bitmap = Utils.downscaleBitmap(bitmap, 2);
		mBackgroundImage.setImageBitmap(bitmap);
	}

	@Override
	protected void onResume() {
		super.onResume();

		playBackgroundMusic();
	}

	@Override
	protected void onStop() {
		super.onStop();
		stopService(new Intent(MainActivity.this, BackgroundMusicService.class));
	}


	public void playBackgroundMusic() {
		Intent intent = new Intent(MainActivity.this, BackgroundMusicService.class);
		startService(intent);
	}


}
