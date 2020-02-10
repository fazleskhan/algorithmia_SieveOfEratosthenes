package com.fazleskhan.sieveoferatosthenes;

import com.fazleskhan.sieveoferatosthenes.Helper;
import org.junit.Assert;
import org.junit.Test;

public class HelperTest {
    @Test
    public void initPrimesZeroLength() {
        Helper target = new Helper();

        boolean[] result = target.initPrimes(0);
        Assert.assertArrayEquals(new boolean[0], result);
    }

    @Test
    public void initPrimesOneLength() {
        Helper target = new Helper();

        boolean[] result = target.initPrimes(1);
        Assert.assertArrayEquals(new boolean[]{false}, result);
    }

    @Test
    public void initPrimesTwoLength() {
        Helper target = new Helper();

        boolean[] result = target.initPrimes(2);
        Assert.assertArrayEquals(new boolean[]{false, false}, result);
    }

    @Test
    public void initPrimesThreeLength() {
        Helper target = new Helper();

        boolean[] result = target.initPrimes(3);
        Assert.assertArrayEquals(new boolean[]{false, false, true}, result);
    }

    @Test
    public void initPrimesOneHundredLength() {
        Helper target = new Helper();

        boolean[] result = target.initPrimes(10);
        Assert.assertArrayEquals(new boolean[]{false, false, true, true, true, true, true, true, true, true}, result);
    }

}