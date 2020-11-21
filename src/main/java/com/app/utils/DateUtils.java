package com.app.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static long getDifferenceBetweenTwoDates(Date from, Date to, TimeUnit timeUnit) {
        long interval = to.getTime() - from.getTime();
        return timeUnit.convert(interval, TimeUnit.MILLISECONDS);
    }
}
