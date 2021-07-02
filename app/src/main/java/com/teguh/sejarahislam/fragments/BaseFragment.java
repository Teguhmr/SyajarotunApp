package com.teguh.sejarahislam.fragments;


import androidx.fragment.app.Fragment;

import com.teguh.sejarahislam.events.EventObserver;
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

public class BaseFragment extends Fragment implements EventObserver {

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(Cerita_GameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(CeritaEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GamesEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(LevelGameEvent event) {
		throw new UnsupportedOperationException();
	}

	public void onEvent(ResetBackgroundGameEvent event) {
		throw new UnsupportedOperationException();
	}


}
