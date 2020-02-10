package com.fazleskhan.sieveoferatosthenes;

import com.fazleskhan.sieveoferatosthenes.SieveOfEratosthenes;
import com.fazleskhan.sieveoferatosthenes.Helper;
import com.fazleskhan.sieveoferatosthenes.PrimesResult;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//import org.junit.Assert;

@RunWith(EasyMockRunner.class)
public class SieveOfEratosthenesTest {

    private SieveOfEratosthenes target;

    private Helper mock;

    @Before
    public void setUp() {
        mock = createMock(Helper.class);
        target = new SieveOfEratosthenes(mock);
    }

    @Test
    public void calcPrimesZeroLength() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        PrimesResult result = target.calcPrimes(0);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLength() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLength() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false, false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLength() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false, false, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(3);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2)));
        verify(mock);
    }

    @Test
    public void calcPrimesTenLength() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false, false, true, true, true, true, true, true, true, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(10);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2, 3, 5, 7)));
        verify(mock);
    }
}