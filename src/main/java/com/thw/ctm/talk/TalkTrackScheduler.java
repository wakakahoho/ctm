package com.thw.ctm.talk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.Track;
import com.thw.ctm.core.TrackScheduler;
import com.thw.ctm.core.event.TrackEvent;

import static com.thw.ctm.talk.ConferenceConfig.LUNCH_TIME;
import static com.thw.ctm.talk.ConferenceConfig.LUNCH_TIME_END;
import static com.thw.ctm.talk.ConferenceConfig.NETWORK_EVENT_TIME_FIRST;
import static com.thw.ctm.talk.ConferenceConfig.NETWORK_EVENT_TIME_LAST;
import static com.thw.ctm.talk.ConferenceConfig.START_TIME;
import static com.thw.ctm.talk.ConferenceConfig.getMaxMinutesOfDay;
import static com.thw.ctm.talk.ConferenceConfig.getMinMinutesOfDay;

/**
 * @author duanxiang 2019/7/2 10:59
 **/
public class TalkTrackScheduler extends TrackScheduler<TalkEvent> {

    private static final Logger logger = LogManager.getLogger(TalkTrackScheduler.class);

    @Override
    public List<Track> buildTracks(List<? extends TrackEvent> events) {
        if (Objects.isNull(events) || events.isEmpty()) {
            logger.warn("parsed events is null");
            return Collections.emptyList();
        }
        List<Track> tracks = new ArrayList<>();

        //calculate numbers of task according to totalTime of task
        //and create session of the track
        int totalTime = 0;
        for (TrackEvent event : events) {
            if (event instanceof TalkEvent) {
                totalTime += ((TalkEvent) event).duration();
            }
        }
        while (totalTime > 0) {
            if (totalTime <= getMinMinutesOfDay()) {
                tracks.add(getTrack(false));
            } else {
                tracks.add(getTrack(true));
            }
            totalTime -= getMaxMinutesOfDay();

        }
        return tracks;
    }

    /**
     * group session
     * @param isMaxLength whether the afternoon time is 14 or 17
     * @return
     */
    private Track getTrack(boolean isMaxLength) {
        Track track = new Track();
        SmartTalkSession morningSession = new SmartTalkSession("morning",START_TIME, LUNCH_TIME);
        // lunchSession is simple
        LunchSession lunchSession = new LunchSession("lunch",LUNCH_TIME, LUNCH_TIME_END);
        SmartTalkSession afterNoonSession = new SmartTalkSession("afternoon",LUNCH_TIME_END, isMaxLength ? NETWORK_EVENT_TIME_LAST : NETWORK_EVENT_TIME_FIRST);
        track.addSession(morningSession);
        track.addSession(lunchSession);
        track.addSession(afterNoonSession);
        return track;
    }



}
