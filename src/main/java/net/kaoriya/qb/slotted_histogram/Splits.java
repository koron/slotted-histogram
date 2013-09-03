package net.kaoriya.qb.slotted_histogram;

import java.util.TreeSet;

public class Splits
{
    private final long[] splits;

    protected Splits(long[] splits) {
        this.splits = splits;
    }

    public int getLength() {
        return this.splits.length;
    }

    public long getValue(int index) {
        return this.splits[index];
    }

    public long getOverValue() {
        return this.splits[this.splits.length - 1] + 1;
    }

    public int getIndex(long value) {
        return findIndex(this.splits, value);
    }

    public static Splits newInstance(long... splits) {
        TreeSet<Long> set = new TreeSet<>();
        for (long value : splits) {
            set.add(value);
        }
        long[] data = new long[set.size()];
        int index = 0;
        for (Long value : set) {
            data[index++] = value;
        }
        return new Splits(data);
    }

    public static int findIndex(long[] splits, long value) {
        int start = 0;
        int end = splits.length - 1;
        if (value < splits[start]) {
            return -1;
        } else if (value >= splits[end]) {
            return splits.length;
        }
        while (true) {
            int mid = (start + end) / 2;
            if (mid == start) {
                return mid;
            } else if (value >= splits[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
    }
}
