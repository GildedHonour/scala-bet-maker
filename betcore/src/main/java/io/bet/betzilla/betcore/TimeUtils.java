package io.bet.betzilla.betcore;

import java.util.Date;

public class TimeUtils {

    public final static long ONE_SECOND = 1000;
    public final static long SECONDS = 60;
    public final static long ONE_MINUTE = ONE_SECOND * 60;
    public final static long MINUTES = 60;
    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long HOURS = 24;
    public final static long ONE_DAY = ONE_HOUR * 24;

    private TimeUtils() {
    }

    static public long minutesFromDate(Date date, int minutes) {
        return date.getTime() + (minutes * TimeUtils.MINUTES * TimeUtils.ONE_SECOND);
    }

    static public long minutesAgoFromDate(Date date, int minutes) {
        return date.getTime() - (minutes * TimeUtils.MINUTES * TimeUtils.ONE_SECOND);
    }
}