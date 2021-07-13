package com.springboot.levi.leviweb1.utils;

/**
 * An abstraction for how time passes.
 */
public abstract class Clock {
    public abstract long getTick();

    public long getTime() {
        return System.currentTimeMillis();
    }

    public static Clock defaultClock() {
        return UserTimeClockHolder.DEFAULT;
    }

    public static class UserTimeClock extends Clock {
        @Override
        public long getTick() {
            return System.nanoTime();
        }
    }

    private static class UserTimeClockHolder {
        private static final Clock DEFAULT = new UserTimeClock();
    }
}
