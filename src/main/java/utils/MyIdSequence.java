package utils;

import java.util.concurrent.atomic.AtomicLong;

public class MyIdSequence {
    private AtomicLong currentValue = new AtomicLong(0L);

    public long getNextValue() {
        return currentValue.getAndIncrement();
    }
}