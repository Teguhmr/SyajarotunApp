package com.teguh.sejarahislam.events.ui;

import com.teguh.sejarahislam.events.AbstractEvent;
import com.teguh.sejarahislam.events.EventObserver;
import com.teguh.sejarahislam.themes.Theme;

public class GamesEvent extends AbstractEvent {


    public static final String TYPE = GamesEvent.class.getName();
    public final Theme theme;

    public GamesEvent(Theme theme) {
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
