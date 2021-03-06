package com.thw.ctm.talk;

import java.util.Collections;
import java.util.List;

import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.core.session.AbstractSession;

/**
 * @author duanxiang 2019/7/4 11:34 LunchSession
 * just add fixed event.
 **/
public class LunchSession extends AbstractSession<TrackEvent> {

    public LunchSession(String sessionName,int startTime, int endTime) {
        super(sessionName,startTime, endTime);
    }


    @Override
    protected List<TrackEvent> postHandle() {
        TrackEvent event = new TrackEvent("Lunch");
        event.setTime(this.getCurrentTime());
        return Collections.singletonList(event);
    }

    @Override
    public boolean support() {
        return ConferenceConfig.LUNCH_EVENT_ENABLED;
    }
}
