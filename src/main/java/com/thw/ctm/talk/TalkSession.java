package com.thw.ctm.talk;

import java.time.LocalTime;
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
    }




    @Override
    protected boolean add(TalkEvent event) {
        Objects.requireNonNull(event);
        logger.debug("add event:" + event);
        if (event.duration() <= this.getRemainTime()) {
            event.setTime(getCurrentTime());
            setRemainTime(getRemainTime() - event.duration());
            setCurrentTime(event.duration());
            return super.add(event);
        }
        return false;
    }

    @Override
    public List<TrackEvent> addAfter() {
        //when all talk event done,add network event
        if (this.getCurrentTime().isAfter(LocalTime.of(13, 0))) {
            TrackEvent event = new TrackEvent("Network event");
            event.setTime(this.getCurrentTime());
            return Collections.singletonList(event);
        }
        return null;
    }

}
