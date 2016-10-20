package com.fazleskhan.virmedica.shared;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeiveResultTest {

    private SeiveResult target;

    private Integer[] primes;

    private String[] messages;

    @Before
    public void setUp() throws Exception {
        primes = new Integer[]{1,2,3};
        messages = new String[]{"a","b","c"};
        target = new SeiveResult(primes,messages);
    }

    @Test
    public void getPrimes() throws Exception {
        Integer[] result = target.getPrimes();
        Assert.assertArrayEquals(new Integer[]{1,2,3},result);
    }

    @Test
    public void getMessages() throws Exception {
        String[] result = target.getMessages();
        Assert.assertArrayEquals(new String[]{"a","b","c"},result);
    }

}