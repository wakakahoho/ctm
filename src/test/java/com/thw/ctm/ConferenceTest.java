package com.thw.ctm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thw.ctm.core.Conference;
import com.thw.ctm.core.TrackScheduler;
import com.thw.ctm.core.enums.DurationUnit;
import com.thw.ctm.core.loader.TrackEventEventLoader;
import com.thw.ctm.talk.TalkEvent;
import com.thw.ctm.talk.TalkTrackScheduler;
import com.thw.ctm.talk.util.ConferenceUtil;

/**
 * @author duanxiang 2019/7/2 15:08
 **/
public class ConferenceTest {

    @Test
    public void testDateFormat() {
        String format = ConferenceUtil.formatDoubleToTime(20.12);
        Assert.assertEquals("08:12 PM", format);
    }

    @Test
    public void test1() {
        List<TalkEvent> nodes = new ArrayList<>();
        nodes.add(new TalkEvent("1", 60, DurationUnit.MINUTES));
        nodes.add(new TalkEvent("2",  45,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("3", 30,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("4", 45,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("5", 45,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("6",  1,DurationUnit.LIGHTENING));
        nodes.add(new TalkEvent("7", 60,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("8", 45,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("9", 30,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("10",  30,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("11",  45,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("12",  60,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("13",60,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("14", 45,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("15", 30,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("16", 30,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("17",  60,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("18",  30,DurationUnit.MINUTES));
        nodes.add(new TalkEvent("19", 30,DurationUnit.MINUTES));

        TalkTrackScheduler talkTrackScheduler = new TalkTrackScheduler();
        Conference conference = talkTrackScheduler.schedule(nodes);
        conference.print();
    }

    @Test
    public void test2(){
        TrackScheduler<TalkEvent> talkTrackScheduler = new TalkTrackScheduler();
        talkTrackScheduler.setEventLoader(new TrackEventEventLoader("schedule1.txt"));
        Conference conference = talkTrackScheduler.run();
        conference.print();
    }
}
