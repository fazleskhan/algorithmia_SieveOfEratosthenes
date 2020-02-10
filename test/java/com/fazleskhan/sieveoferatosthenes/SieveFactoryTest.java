package com.fazleskhan.sieveoferatosthenes;

import org.junit.Assert;
import org.junit.Test;

public class SieveFactoryTest {
    @Test
    public void initPrimesZeroLength() {
        SieveOfEratosthenes target = new SieveOfEratosthenes();

        boolean[] result = target.initPrimes(0);
        Assert.assertArrayEquals(new boolean[0], result);
    }

    @Test
    public void initPrimesOneLength() {
        SieveOfEratosthenes target = new SieveOfEratosthenes();

        boolean[] result = target.initPrimes(1);
        Assert.assertArrayEquals(new boolean[]{false}, result);
    }

    @Test
    public void initPrimesTwoLength() {
        SieveOfEratosthenes target = new SieveOfEratosthenes();

        boolean[] result = target.initPrimes(2);
        Assert.assertArrayEquals(new boolean[]{false, false}, result);
    }

    @Test
    public void initPrimesThreeLength() {
        SieveOfEratosthenes target = new SieveOfEratosthenes();

        boolean[] result = target.initPrimes(3);
        Assert.assertArrayEquals(new boolean[]{false, false, true}, result);
    }

    @Test
    public void initPrimesOneHundredLength() {
        SieveOfEratosthenes target = new SieveOfEratosthenes();

        boolean[] result = target.initPrimes(10);
        Assert.assertArrayEquals(new boolean[]{false, false, true, true, true, true, true, true, true, true}, result);
    }

}