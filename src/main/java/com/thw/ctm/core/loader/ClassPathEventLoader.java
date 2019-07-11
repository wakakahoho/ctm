package com.thw.ctm.core.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thw.ctm.core.TrackScheduler;
import com.thw.ctm.core.event.TrackEvent;

/**
 * @author duanxiang 2019/7/4 15:53
 * load file from class path
 * using patternMatcher to explain ...
 *
 **/
public class ClassPathEventLoader implements EventLoader<TrackEvent> {

    private static final Logger logger = LogManager.getLogger(TrackScheduler.class);
    private String fileName;
    private PatternMatcher patternMatcher;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<TrackEvent> load() {
        return getEventString().stream().map(patternMatcher::build).distinct().collect(Collectors.toList());
    }

    private List<String> getEventString() {
        List<String> lines = new ArrayList<>();
        String line ;
        try (BufferedReader br =
            new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ClassPathEventLoader.class.getClassLoader().getResourceAsStream(fileName),"file not exist")))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException("load file error", e);
        }
        return lines;
    }

    protected void setPatternMatcher(PatternMatcher patternMatcher) {
        this.patternMatcher = patternMatcher;
    }
}
