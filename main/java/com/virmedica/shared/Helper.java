package com.virmedica.shared;

import java.util.Arrays;

public abstract class Helper {

    public static boolean[] initPrimes(final int end) {
        final boolean[] primes = new boolean[end];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        return primes;
    }

}
