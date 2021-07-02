package com.teguh.sejarahislam;

import android.app.Application;

import com.teguh.sejarahislam.utils.FontLoader;


public class GameApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FontLoader.loadFonts(this);

	}
}
