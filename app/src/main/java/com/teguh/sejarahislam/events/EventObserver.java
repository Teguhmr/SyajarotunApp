package com.teguh.sejarahislam.events;


import com.teguh.sejarahislam.events.engine.GameWonEvent;
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

public interface EventObserver {

	void onEvent(DifficultySelectedEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

	void onEvent(Cerita_GameEvent event);

	void onEvent(CeritaEvent event);

	void onEvent(GamesEvent event);

	void onEvent(LevelGameEvent event);

	void onEvent(ResetBackgroundGameEvent event);
}
