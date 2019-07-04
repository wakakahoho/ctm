package com.thw.ctm.core.loader;

import java.util.List;

import com.thw.ctm.core.event.TrackEvent;

/**
 * @author duanxiang 2019/7/4 15:41 load event from datasource
 **/
public interface EventLoader<T extends TrackEvent> {

    /**
     * @return TrackEvent
     */
    List<T> load();
}
