package com.teguh.sejarahislam.themes;


import android.graphics.Bitmap;

import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.model.BoardConfiguration;
import com.teguh.sejarahislam.utils.Utils;

public class Themes {

	public static String URI_DRAWABLE = "drawable://";

	public static Theme createCeritaTheme() {
		Theme theme = new Theme();
		theme.id = 3;
		theme.setName("Cerita");
		theme.backgroundImageUrl = URI_DRAWABLE + "cerita";

		return theme;
	}
	public static Theme createGameTheme() {
		Theme theme = new Theme();
		theme.id = 4;
		theme.setName("Games");
		theme.backgroundImageUrl = URI_DRAWABLE + "game";

		return theme;
	}

	public static Theme createBattleAbdulQodirTheme() {
		Theme theme = new Theme();
		BoardConfiguration boardConfiguration = new BoardConfiguration(1);
		boardConfiguration.difficulty = 1;
		theme.id = 1;
		theme.setName("Abdul Qadir Jailani");
		theme.backgroundImageUrl = URI_DRAWABLE + "game";

		return theme;

	}

	public static Theme createBattleAbuAbdillahTheme() {
		Theme theme = new Theme();
		theme.id = 2;
		theme.setName("Abu Abdillah");
		theme.backgroundImageUrl = URI_DRAWABLE + "game";

		return theme;

	}

	public static Bitmap getBackgroundImage(Theme theme) {
		String drawableResourceName = theme.backgroundImageUrl.substring(Themes.URI_DRAWABLE.length());
		int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
		Bitmap bitmap = Utils.scaleDown(drawableResourceId, Utils.screenWidth(), Utils.screenHeight());
		return bitmap;
	}

}
