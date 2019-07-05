package com.thw.ctm.talk;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.core.session.AbstractSession;


/**
 * @author duanxiang 2019/7/2 9:45
 **/
public class TalkSession extends AbstractSession<TalkEvent> {

    private static final Logger logger = LogManager.getLogger(TalkSession.class);

    public TalkSession(int startTime, int endTime) {
        super(startTime, endTime);
        registerStrategy(new SimpleSessionStrategy());
    }


    @Override
    public List<TrackEvent> postHandle() {
        //when all talk event done,add network event
        // current time between 16 and 17
        if (this.getCurrentTime().getHour() >= ConferenceConfig.NETWORK_EVENT_TIME_FIRST
            && this.getCurrentTime().getHour() <= ConferenceConfig.NETWORK_EVENT_TIME_LAST) {
            TrackEvent event = new TrackEvent("Network event");
            event.setTime(this.getCurrentTime());
            logger.info("add extra network event [{}]", event);
            return Collections.singletonList(event);
        }

        return null;
    }

    /**
     * (remainTime - event duration)
     */
    public class SimpleSessionStrategy implements SessionStrategy<TalkEvent> {

        private final Logger logger = LogManager.getLogger(SimpleSessionStrategy.class);


        protected boolean add(TalkEvent event) {
            Objects.requireNonNull(event, "event cant't be null");
            if (event.duration() <= getRemainTime()) {
                event.setTime(getCurrentTime());
                setRemainTime(getRemainTime() - event.duration());
                setCurrentTime(event.duration());
                if (notFull()) {
                    getLocalEvents().add(event);
                    return true;
                }
            }
            return false;
        }


        @Override
        public void process(List<TalkEvent> events) {
            events.forEach(talkEvent -> {
                if (!talkEvent.isHandled() && add(talkEvent)) {
                    talkEvent.setHandled(true);
                } else {
                    return;
                }
            });
        }

    }


}
