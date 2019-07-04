package com.thw.ctm.core;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.enums.DurationUnit;
import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.talk.TalkEvent;
import com.thw.ctm.talk.util.ConferenceUtil;

/**
 * @author duanxiang 2019/7/1 14:50
 *
 *  As result of the secheuler ,this class hold the result of tracks
 *  Usage:
 *         TrackScheduler<TalkEvent> talkTrackScheduler = new TalkTrackScheduler();
 *         talkTrackScheduler.setEventLoader(new TrackEventEventLoader("schedule1.txt"));
 *         Conference conference = talkTrackScheduler.run();
 *         conference.print();
 **/
public class Conference {

    private static final Logger logger = LogManager.getLogger(Conference.class);

    private final List<Track> tracks;

    public Conference(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void print() {
        logger.info("starting output....");
        if (tracks.isEmpty()) {
            logger.warn("this conference is empty!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tracks.size(); i++) {
            sb.append("\n");
            sb.append(String.format("Track %d:\n", i + 1));
            tracks.get(i).getSessions().forEach(session -> {
                List<TrackEvent> events = session.getEvents();
                events.forEach(event -> {
                    sb.append(ConferenceUtil.formatterToApm.format(event.getTime()));
                    if (event instanceof TalkEvent) {
                        TalkEvent talkEvent = (TalkEvent) event;
                        if (talkEvent.getDurationUnit().equals(DurationUnit.LIGHTENING)) {
                            sb.append(String.format(" %s %s", talkEvent.getTitle(),talkEvent.getDurationUnit().getName()));
                        }else {
                            sb.append(String.format(" %s %dmin", event.getTitle(), talkEvent.duration()));

                        }
                    }else {
                        sb.append(String.format(" %s",event.getTitle()));
                    }
                    sb.append("\n");
                });
            });

        }
        logger.info(sb.toString());
    }


}
