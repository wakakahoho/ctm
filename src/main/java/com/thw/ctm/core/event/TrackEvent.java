package com.thw.ctm.core.event;

import java.time.LocalTime;


/**
 * @author duanxiang 2019/7/1 15:49
 **/
public class TrackEvent {

    private LocalTime time;
    private String title;
    private volatile boolean handled = false;

    public TrackEvent() {

    }

    public TrackEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }
}
