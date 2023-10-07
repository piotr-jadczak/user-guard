package com.pj.userguard.util.time;

import java.sql.Timestamp;

public class TimeUtils {

    private TimeUtils() {}

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
