package com.teguh.sejarahislam.events.ui;


import com.teguh.sejarahislam.events.AbstractEvent;
import com.teguh.sejarahislam.events.EventObserver;
import com.teguh.sejarahislam.themes.Theme;

public class LevelGameEvent extends AbstractEvent {

	public static final String TYPE = LevelGameEvent.class.getName();
	public final Theme theme;

	public LevelGameEvent(Theme theme) {
		this.theme = theme;
	}

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
