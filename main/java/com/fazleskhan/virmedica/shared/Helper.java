package com.fazleskhan.virmedica.shared;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Helper {

    public Helper() {
        //default constructor
    }

    public boolean[] initPrimes(final int end) {

        final boolean[] primes = new boolean[end];
        Arrays.fill(primes, true);
        switch (end) {
            case 0:  //return zero element array
                break;
            case 1:  //return single element array
                primes[0] = false;
                break;
            default:
                primes[0] = false;
                primes[1] = false;
                break;
        }
        return primes;
    }
}
