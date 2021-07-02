package com.teguh.sejarahislam.events.ui;


import com.teguh.sejarahislam.events.AbstractEvent;
import com.teguh.sejarahislam.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class ResetBackgroundGameEvent extends AbstractEvent {

	public static final String TYPE = ResetBackgroundGameEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
