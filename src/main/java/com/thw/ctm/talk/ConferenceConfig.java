package com.thw.ctm.talk;

/**
 * @author duanxiang 2019/7/1 15:09
 **/
public class ConferenceConfig {

    /**
     * lunch event config
     */
    public static final boolean LUNCH_EVENT_ENABLED = true;
    public static final int LUNCH_TIME = 12;
    public static final int LUNCH_TIME_END = 13;



    public static final int START_TIME = 9;
    /**
     * network event config
     */
    public static final int NETWORK_EVENT_TIME_FIRST = 16;
    public static final int NETWORK_EVENT_TIME_LAST = 17;

    public static final int MIN_OF_HOUR = 60;
    /**
     * loader config
     */
    public static final int EVENT_NAME_INDEX = 1;
    public static final int EVENT_DURATION_INDEX = 2;
    public static final int EVENT_DURATION_UNIT_INDEX = 3;


    public static int getMaxMinutesOfDay() {
        int morningHour = LUNCH_TIME - START_TIME;
        int afterNoonHour = NETWORK_EVENT_TIME_LAST - LUNCH_TIME_END;
        return (morningHour + afterNoonHour) * MIN_OF_HOUR;
    }

    public static int getMinMinutesOfDay() {
        int morningHour = LUNCH_TIME - START_TIME;
        int afterNoonHour = NETWORK_EVENT_TIME_FIRST - LUNCH_TIME_END;
        return (morningHour + afterNoonHour) * MIN_OF_HOUR;
    }
}
