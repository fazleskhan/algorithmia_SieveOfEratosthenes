package com.fazleskhan.virmedica.shared;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SieveResultTest {

    private PrimesResult target;

    @Before
    public void setUp() throws Exception {
        List<Integer> primes = Arrays.asList(1,2,3);
        List<String> messages = Arrays.asList("a","b","c");
        target = new PrimesResult(primes, messages);
    }

    @Test
    public void getPrimes() throws Exception {
        List<Integer> result = target.getPrimes();
        assertThat(result,is(Arrays.asList(1,2,3)));
    }

    @Test
    public void getMessages() throws Exception {
        List<String> result = target.getMessages();
        assertThat(result,is(Arrays.asList("a","b","c")));
    }

}