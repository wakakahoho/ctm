package com.thw.ctm.talk;

import java.util.Collections;
import java.util.List;

import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.core.session.AbstractSession;

/**
 * @author duanxiang 2019/7/4 11:34
 **/
public class LunchSession extends AbstractSession<TalkEvent> {
    public LunchSession(int startTime, int endTime) {
        super(startTime, endTime);
    }

    @Override
    protected boolean add(TalkEvent event) {
        return false;
    }

    @Override
    protected List<TrackEvent> addAfter() {
        TrackEvent event = new TrackEvent("Lunch");
        event.setTime(this.getCurrentTime());
        return Collections.singletonList(event);
    }

    @Override
    public boolean support() {
        return ConferenceConfig.LUNCH_EVENT_ENABLED;
    }
}
