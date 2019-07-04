package com.thw.ctm.core.loader;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.TrackScheduler;
import com.thw.ctm.core.enums.DurationUnit;
import com.thw.ctm.core.event.TrackEvent;
import com.thw.ctm.talk.TalkEvent;

import static com.thw.ctm.talk.ConferenceConfig.EVENT_DURATION_INDEX;
import static com.thw.ctm.talk.ConferenceConfig.EVENT_DURATION_UNIT_INDEX;
import static com.thw.ctm.talk.ConferenceConfig.EVENT_NAME_INDEX;

/**
 * @author duanxiang 2019/7/4 16:24
 **/
public class TrackEventEventLoader extends ClassPathEventLoader {

    private static final Logger logger = LogManager.getLogger(TrackScheduler.class);

    public TrackEventEventLoader(String fileName) {
        Objects.requireNonNull(fileName);
        setFileName(fileName);
        setPatternMatcher(new TrackEventPathMatcher());
    }

    static class TrackEventPathMatcher implements PatternMatcher {

        public static final Pattern PATTERN = Pattern.compile(
            "^(.+)\\s(\\d+)?\\s?((min)|(lightning))$");

        @Override
        public TrackEvent build(String line) {
            Matcher match = PATTERN.matcher(line);
            if (!match.find()) {
                logger.warn("error in parse line {}", line);
                return null;
            }
            //for title
            String title = match.group(EVENT_NAME_INDEX);
            // duration
            DurationUnit unit;
            if (match.group(EVENT_DURATION_UNIT_INDEX).equalsIgnoreCase("min")) {
                unit = DurationUnit.MINUTES;
            } else {
                unit = DurationUnit.LIGHTENING;
            }
            String durationInString = match.group(EVENT_DURATION_INDEX);
            if (durationInString == null) {
                durationInString = "1";
            }
             int duration = Integer.parseInt(durationInString);

            return new TalkEvent(title,duration,unit);
        }
    }
}
