package com.fazleskhan.algo_sieve;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AlgoSieveTests {

    @Test
    public void ZeroLastNumber(){
        AlgoSieve target = new AlgoSieve();

        String result = target.apply("0");
        assertThat(result,is("Sieve of Eratosthenes (last number 0): "));
    }
}
