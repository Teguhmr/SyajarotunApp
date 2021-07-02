package com.teguh.sejarahislam.engine;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.events.ui.ResetBackgroundEvent;
import com.teguh.sejarahislam.events.ui.ResetBackgroundGameEvent;
import com.teguh.sejarahislam.fragments.CeritaFragment;
import com.teguh.sejarahislam.fragments.GameFragment;
import com.teguh.sejarahislam.fragments.GamesFragment;
import com.teguh.sejarahislam.fragments.LevelGameFragment;
import com.teguh.sejarahislam.fragments.MenuFragment;
import com.teguh.sejarahislam.fragments.Story_GameFragment;

import java.util.ArrayList;
import java.util.List;

public class ScreenController {

	private static ScreenController mInstance = null;
	private static List<Screen> openedScreens = new ArrayList<Screen>();
	private FragmentManager mFragmentManager;

	private ScreenController() {
	}

	public static ScreenController getInstance() {
		if (mInstance == null) {
			mInstance = new ScreenController();
		}
		return mInstance;
	}

	public static enum Screen {
		MENU,
		GAME,
		LEVEL_GAME,
		STORY_GAME,
		CERITA,
		GAMES
	}
	
	public static Screen getLastScreen() {
		return openedScreens.get(openedScreens.size() - 1);
	}

	public void openScreen(Screen screen) {
		mFragmentManager = Shared.activity.getSupportFragmentManager();

		if (screen == Screen.GAME && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
		} else if (screen == Screen.LEVEL_GAME && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
		}
		Fragment fragment = getFragment(screen);
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
		openedScreens.add(screen);
	}

	public boolean onBack() {
		if (openedScreens.size() > 0) {
			Screen screenToRemove = openedScreens.get(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
			if (openedScreens.size() == 0) {
				return true;
			}
			Screen screen = openedScreens.get(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
			openScreen(screen);
			if ((screen == Screen.MENU || screen == Screen.STORY_GAME) &&
					(screenToRemove == Screen.GAMES  && openedScreens.get(openedScreens.size() - 1) == Screen.GAMES)||
					(screenToRemove == Screen.CERITA && openedScreens.get(openedScreens.size() - 1) == Screen.CERITA)){
				Shared.eventBus.notify(new ResetBackgroundEvent());
			}else if ((screenToRemove == Screen.LEVEL_GAME && openedScreens.get(openedScreens.size() - 1) == Screen.LEVEL_GAME)
			|| screenToRemove == Screen.GAME && openedScreens.get(openedScreens.size() - 1) == Screen.GAME){
				Shared.eventBus.notify(new ResetBackgroundGameEvent());

			}
			return false;
		}
		return true;
	}

	private Fragment getFragment(Screen screen) {
		switch (screen) {
		case MENU:
			return new MenuFragment();
		case GAMES:
			return new GamesFragment();
		case GAME:
			return new GameFragment();
		case LEVEL_GAME:
			return new LevelGameFragment();
		case STORY_GAME:
			return new Story_GameFragment();
		case CERITA:
			return new CeritaFragment();
		default:
			break;
		}
		return null;
	}
}
