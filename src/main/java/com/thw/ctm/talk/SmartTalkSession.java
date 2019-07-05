package com.thw.ctm.talk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.event.TrackEvent;


/**
 * @author duanxiang 2019/7/2 9:45
 **/
public class SmartTalkSession extends TalkSession {

    private static final Logger logger = LogManager.getLogger(SmartTalkSession.class);


    public SmartTalkSession(int startTime, int endTime) {
        super(startTime, endTime);
        registerStrategy(new SmartSessionStrategy());
    }

    @Override
    public List<TrackEvent> postHandle() {
        return super.postHandle();
    }

    class SmartSessionStrategy extends TalkSession.SimpleSessionStrategy {

        private List<List<TalkEvent>> smartSessionResult;

        @Override
        public void process(List<TalkEvent> events) {
            smartSessionResult = new ArrayList<>();
            List<TalkEvent> talkEvents = events.stream().sorted(Comparator.comparing(TalkEvent::duration)).collect(Collectors.toList());
            if (talkEvents.isEmpty()) {
                return;
            }
            List<TalkEvent> temp = new ArrayList<>();
            group(talkEvents, 0, getRemainTime(), temp);
            logger.info("found {} matches", smartSessionResult.size());
            Random random = new Random();
            if(!smartSessionResult.isEmpty()){
                super.process(smartSessionResult.get(random.nextInt(smartSessionResult.size())));
            }
            super.process(events);
        }

        private void group(List<TalkEvent> talkEvents, int l, int target, List<TalkEvent> list) {
            if (target == 0) {
                smartSessionResult.add(new ArrayList<>(list));
                return;
            }
            for (int i = l; i < talkEvents.size(); i++) {
                if (i > l && talkEvents.get(i).duration() == talkEvents.get(i - 1).duration()) {
                    continue;
                }
                if (target - talkEvents.get(i).duration() >= 0) {
                    list.add(talkEvents.get(i));
                    group(talkEvents, i + 1, target - talkEvents.get(i).duration(), list);
                    if (!list.isEmpty()) {
                        list.remove(list.size() - 1);
                    }
                }

            }
        }


    }
}
