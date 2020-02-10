package com.fazleskhan.sieveoferatosthenes;

import org.junit.Assert;
import org.junit.Test;

public class SieveFactoryTest {
    @Test
    public void initPrimesZeroLength() {
        boolean[] result = SieveOfEratosthenes.initPrimes(0);
        Assert.assertArrayEquals(new boolean[0], result);
    }

    @Test
    public void initPrimesOneLength() {
        boolean[] result = SieveOfEratosthenes.initPrimes(1);
        Assert.assertArrayEquals(new boolean[]{false}, result);
    }

    @Test
    public void initPrimesTwoLength() {
        boolean[] result = SieveOfEratosthenes.initPrimes(2);
        Assert.assertArrayEquals(new boolean[]{false, false}, result);
    }

    @Test
    public void initPrimesThreeLength() {
        boolean[] result = SieveOfEratosthenes.initPrimes(3);
        Assert.assertArrayEquals(new boolean[]{false, false, true}, result);
    }

    @Test
    public void initPrimesOneHundredLength() {
        boolean[] result = SieveOfEratosthenes.initPrimes(10);
        Assert.assertArrayEquals(new boolean[]{false, false, true, true, true, true, true, true, true, true}, result);
    }

}