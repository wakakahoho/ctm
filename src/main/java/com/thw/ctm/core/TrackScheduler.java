package com.thw.ctm.core;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.core.loader.EventLoader;
import com.thw.ctm.core.session.task.SessionTask;

/**
 * @author duanxiang 2019/7/1 15:17
 **/
public abstract class TrackScheduler<T extends TrackEvent> {

    private static final Logger logger = LogManager.getLogger(TrackScheduler.class);


    private EventLoader eventLoader;

    public TrackScheduler() {

    }

    public void setEventLoader(EventLoader eventLoader) {
        this.eventLoader = eventLoader;
    }

    public Conference run() {
        if (Objects.isNull(eventLoader)) {
            throw new IllegalStateException("eventLoader is null,set eventLoader first!");
        }
        return schedule(eventLoader.load());
    }


    public Conference schedule(List<T> events) {
        logger.debug("start schedule,got {} event", events.size());
        List<Track> tracks = buildTracks(events);
        tracks.forEach(track -> {
            logger.debug("process track :{}", track);
            SessionTask<T> session;
            while ((session = track.nextSession()) != null) {
                session.planWith(events);
            }
        });
        return new Conference(tracks);
    }


    public abstract List<Track> buildTracks(List<? extends TrackEvent> events);
}
