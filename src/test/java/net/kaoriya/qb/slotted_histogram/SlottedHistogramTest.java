package net.kaoriya.qb.slotted_histogram;

import org.junit.Test;
import static org.junit.Assert.*;

public class SlottedHistogramTest
{
    @Test
    public void basic() {
        Splits splits = Splits.newInstance(0, 1, 2, 3, 4, 5);
        SlottedHistogram hist = new SlottedHistogram(splits);
        hist.offer(0);
        hist.offer(1);
        hist.offer(2);
        hist.offer(3);
        hist.offer(4);
        assertEquals(0, hist.quantile(0f));
        assertEquals(1, hist.quantile(0.2f));
        assertEquals(2, hist.quantile(0.4f));
        assertEquals(3, hist.quantile(0.6f));
        assertEquals(4, hist.quantile(0.8f));
        //assertEquals(5, hist.quantile(1.0f));
    }
}
