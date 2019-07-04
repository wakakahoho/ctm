package com.thw.ctm.core.session.task;

import java.util.List;

import com.thw.ctm.core.event.TrackEvent;

/**
 * @Author duanxiang
 * @Date 2019/7/4 10:05
 *  a Session Manages a list of track event.
 *  SessionTask  combine track event with Session execute in order.
 *
 **/
@FunctionalInterface
public interface SessionTask<T extends TrackEvent> {

    void planWith(List<T> events);

}
