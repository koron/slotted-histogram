package net.kaoriya.qb.slotted_histogram;

public class SlottedHistogram
{
    private final int num;

    private final Splits splits;

    private final long[] slot;

    private long count;

    public SlottedHistogram(Splits splits) {
        this.num = splits.getLength();
        this.splits = splits;
        this.slot = new long[this.num];
    }

    public void offer(long value) {
        int index = this.splits.getIndex(value);
        if (index >= 0 && index < num) {
            ++this.slot[index];
        }
        ++this.count;
    }

    public long quantile(float q) {
        long threshold = (long)(q * this.count);
        long curr = 0;
        for (int i = 0, I = this.num; i < I; ++i) {
            curr += this.slot[i];
            if (curr > threshold) {
                return this.splits.getValue(i);
            }
        }
        return this.splits.getOverValue();
    }
}
