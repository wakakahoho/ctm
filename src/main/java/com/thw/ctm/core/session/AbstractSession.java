package com.thw.ctm.core.session;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.core.session.task.SessionTask;
import com.thw.ctm.talk.ConferenceConfig;

/**
 * session represents the container of the TrackEvent extends this class to custom session.
 *
 * this class provide the basic fields and function to plan with events,and provide basic monitors or tools
 *
 * @author duanxiang 2019/7/1 16:43
 **/
public abstract class AbstractSession<T extends TrackEvent> implements SessionTask<T> {

    private LocalTime currentTime;
    private int remainTime;
    private List<TrackEvent> events;


    public AbstractSession(int startTime, int endTime) {
        validate(startTime, endTime);
        remainTime = (endTime - startTime) * ConferenceConfig.MIN_OF_HOUR;
        currentTime = LocalTime.of(startTime, 0);
        events = new ArrayList<>();
    }

    /**
     * implements must call super.add before implement this
     *
     * @param event the add event
     * @return add success
     */
    protected boolean add(T event) {
        //session is full
        if (notFull()) {
            this.events.add(event);
            return true;
        }
        return false;
    }

    /**
     * supply track event by other source not just the parameter of #planWith
     */
    protected List<TrackEvent> addBefore() {
        return null;
    }

    /**
     * same to addBefore
     */
    protected List<TrackEvent> addAfter() {
        return null;
    }


    /**
     * before session create to validate session
     */
    protected void validate(int startTime, int endTime) {
        if (endTime < startTime) {
            throw new IllegalArgumentException("endTime must greater than startTime");
        }
    }


    public List<TrackEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

    /**
     * subclass use this to decide current session is valid
     *
     * @return default is valid
     */
    public boolean support() {
        return true;
    }

    /**
     * iterates the events if match condition then delete.
     */
    @Override
    public void planWith(List<T> events) {
        Objects.requireNonNull(events);
        if (support()) {
            if (Objects.nonNull(addBefore())) {
                this.events.addAll(addBefore());
            }
            Iterator<T> iterator = events.iterator();
            while (iterator.hasNext()) {
                T next = iterator.next();
                if (add(next)) {
                    iterator.remove();
                } else {
                    break;
                }
            }
            if (Objects.nonNull(addAfter())) {
                this.events.addAll(addAfter());
            }
        }

    }

    /**
     * session has more time
     */
    public boolean notFull() {
        return remainTime >= 0;
    }

    protected int getRemainTime() {
        return remainTime;
    }

    protected void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * move current time cursor
     */
    protected void setCurrentTime(int duration) {
        currentTime = currentTime.plusMinutes(duration);

    }
}
