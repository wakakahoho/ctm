package com.thw.ctm.core;

import java.util.ArrayList;
import java.util.List;

import com.thw.ctm.core.session.AbstractSession;
import com.thw.ctm.core.session.task.SessionTask;

/**
 * @author duanxiang 2019/7/1 14:51
 **/
public class Track {

    private List<AbstractSession> sessions;
    private int currentIndex = 0;

    public Track() {
        sessions = new ArrayList<>();
    }

    public SessionTask nextSession() {
        if (currentIndex < sessions.size() && this.sessions.get(currentIndex).notFull()) {
            return this.sessions.get(currentIndex++);
        }
        return null;
    }

    public void addSession(AbstractSession session) {
        this.sessions.add(session);
    }

    public List<AbstractSession> getSessions() {
        return sessions;
    }
}
