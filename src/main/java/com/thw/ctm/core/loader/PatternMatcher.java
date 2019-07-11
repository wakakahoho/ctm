package com.thw.ctm.core.loader;

import com.thw.ctm.core.event.TrackEvent;

/**
 * @author duanxiang 2019/7/4 16:18
 *
 **/
public interface PatternMatcher {

    /**
     * from string to TrackEvent
     * @param line
     * @return
     */
    TrackEvent build(String line);

}
