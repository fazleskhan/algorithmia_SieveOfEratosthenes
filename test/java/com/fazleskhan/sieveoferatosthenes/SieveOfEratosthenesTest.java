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
import java.util.Collections;
import java.util.function.Function;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//import org.junit.Assert;

@RunWith(EasyMockRunner.class)
public class SieveOfEratosthenesTest {

    private SieveOfEratosthenes target;

    private Function mock;

    @Before
    public void setUp() {
        mock = createMock(Function.class);
        target = new SieveOfEratosthenes(mock);
    }

    @Test
    public void calcPrimesZeroLength() {
        EasyMock.expect(mock.apply(0)).andReturn(new boolean[0]);
        replay(mock);
        PrimesResult result = target.calcPrimes(0);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLength() {
        EasyMock.expect(mock.apply(1)).andReturn(new boolean[]{false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLength() {
        EasyMock.expect(mock.apply(1)).andReturn(new boolean[]{false, false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLength() {
        EasyMock.expect(mock.apply(3)).andReturn(new boolean[]{false, false, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(3);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Collections.singletonList(2)));
        verify(mock);
    }

    @Test
    public void calcPrimesTenLength() {
        EasyMock.expect(mock.apply(10)).andReturn(new boolean[]{false, false, true, true, true, true, true, true, true, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(10);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2, 3, 5, 7)));
        verify(mock);
    }
}