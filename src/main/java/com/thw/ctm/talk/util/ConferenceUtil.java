package com.thw.ctm.talk.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author duanxiang 2019/7/2 15:01
 **/
public final class ConferenceUtil {
    public static final DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern("HH.mm");
    public static final DateTimeFormatter formatterToApm = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
    private ConferenceUtil() {
    }

    public static String formatDoubleToTime(double time) {
        return formatterToApm.format(localTimeFormatter.parse(time+""));
    }

}
