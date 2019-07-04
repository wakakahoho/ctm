package com.thw.ctm.talk;

import com.thw.ctm.core.enums.DurationUnit;
import com.thw.ctm.core.event.TrackEvent;

/**
 * @author duanxiang 2019/7/1 14:57
 **/
public class TalkEvent extends TrackEvent {

    private int duration;

    private DurationUnit durationUnit;

    public TalkEvent() {
    }

    public TalkEvent(String title, int duration, DurationUnit unit) {
        super(title);
        this.durationUnit = unit;
        this.duration =  duration;
    }

    public int duration(){
        return durationUnit.inMinutes(duration);
    }


    public DurationUnit getDurationUnit() {
        return durationUnit;
    }

    @Override
    public String toString() {
        return "TalkEvent{" +
            "durationUnit=" + durationUnit +"title="+this.getTitle()+
            '}';
    }
}
