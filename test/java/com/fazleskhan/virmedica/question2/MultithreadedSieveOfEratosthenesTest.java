package com.fazleskhan.virmedica.question2;

import com.fazleskhan.virmedica.shared.Helper;
import com.fazleskhan.virmedica.shared.PrimesResult;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MultithreadedSieveOfEratosthenesTest {

    private MultithreadedSieveOfEratosthenes target;

    private Helper mock;

    @Before
    public void setUp() {
        mock = createMock(Helper.class);
        target = new MultithreadedSieveOfEratosthenes(mock);
    }

    @Test
    public void calcPrimesZeroLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        PrimesResult result = target.calcPrimes(0, 1);
        assertFalse(result.getMessages().isEmpty());        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1, 1);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false, false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1, 1);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false, false, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(3, 1);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2)));
        verify(mock);
    }

    @Test
    public void calcPrimesTenLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false, false, true, true, true, true, true, true, true, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(10, 1);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2, 3, 5, 7)));
        verify(mock);
    }

    @Test
    public void calcPrimesZeroLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        PrimesResult result = target.calcPrimes(0, 2);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1, 2);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false, false});
        replay(mock);
        PrimesResult result = target.calcPrimes(1, 2);
        assertFalse(result.getMessages().isEmpty());
        assertTrue(result.getPrimes().isEmpty());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false, false, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(3, 2);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2)));
        verify(mock);
    }

    @Test
    public void calcPrimesTenLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false, false, true, true, true, true, true, true, true, true});
        replay(mock);
        PrimesResult result = target.calcPrimes(10, 2);
        assertFalse(result.getMessages().isEmpty());
        assertThat(result.getPrimes(),is(Arrays.asList(2, 3, 5, 7)));
        verify(mock);
    }


}