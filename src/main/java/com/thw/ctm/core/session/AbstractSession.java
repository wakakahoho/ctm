package com.thw.ctm.core.session;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    //default group 0
    private static final Logger logger = LogManager.getLogger(AbstractSession.class);
    private LocalTime currentTime;
    private int remainTime;
    private List<SessionStrategy> sessionStrategies;
    private List<TrackEvent> events;


    public AbstractSession(int startTime, int endTime) {
        validate(startTime, endTime);
        remainTime = (endTime - startTime) * ConferenceConfig.MIN_OF_HOUR;
        currentTime = LocalTime.of(startTime, 0);
        events = new ArrayList<>();
        sessionStrategies = new ArrayList<>();
    }

    public void registerStrategy(SessionStrategy<T> sessionStrategy) {
        Objects.requireNonNull(sessionStrategy, "registerStrategy cant'be null");
        this.sessionStrategies.add(sessionStrategy);
    }


    protected List<TrackEvent> preHandle() {
        return null;
    }


    protected List<TrackEvent> postHandle() {
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

    /**
     * all handled events
     */
    public List<TrackEvent> getAllEvents() {
        return Collections.unmodifiableList(events);
    }


    protected List<TrackEvent> getLocalEvents() {
        return this.events;
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
            if (Objects.nonNull(preHandle())) {
                this.events.addAll(preHandle());
            }
            List<T> eventList = new ArrayList<>(events);
            Collections.reverse(sessionStrategies);
            for (SessionStrategy sessionStrategy : sessionStrategies) {
                sessionStrategy.process(eventList);
            }
            if (Objects.nonNull(postHandle())) {
                this.events.addAll(postHandle());

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

    protected LocalTime getCurrentTime() {
        return currentTime;
    }

    /**
     * move current time cursor
     */
    public void setCurrentTime(int duration) {
        currentTime = currentTime.plusMinutes(duration);

    }


    /**
     * a session may use different way to deal with events
     */
    public interface SessionStrategy<E extends TrackEvent> {

        void process(List<E> events);

    }


}
