package com.fazleskhan.virmedica.question2a;

import com.fazleskhan.virmedica.shared.Helper;
import com.fazleskhan.virmedica.shared.SieveResult;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

public class FunctionalMultithreadedSieveTest {

    private FunctionalMultithreadedSieve target;

    private Helper mock;

    @Before
    public void setUp(){
        mock = createMock(Helper.class);
        target = new FunctionalMultithreadedSieve(mock);
    }

    @Test
    public void calcPrimesZeroLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        SieveResult result = target.calcPrimes(0,1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        SieveResult result = target.calcPrimes(1,1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false,false});
        replay(mock);
        SieveResult result = target.calcPrimes(1,1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false,false,true});
        replay(mock);
        SieveResult result = target.calcPrimes(3,1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[]{2},result.getPrimes());
        verify(mock);
    }
    @Test
    public void calcPrimesTenLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false,false,true,true,true,true,true,true,true,true});
        replay(mock);
        SieveResult result = target.calcPrimes(10,1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[]{2,3,5,7},result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesZeroLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        SieveResult result = target.calcPrimes(0,2);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        SieveResult result = target.calcPrimes(1,2);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false,false});
        replay(mock);
        SieveResult result = target.calcPrimes(1,2);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false,false,true});
        replay(mock);
        SieveResult result = target.calcPrimes(3,2);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[]{2},result.getPrimes());
        verify(mock);
    }
    @Test
    public void calcPrimesTenLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false,false,true,true,true,true,true,true,true,true});
        replay(mock);
        SieveResult result = target.calcPrimes(10,2);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[]{2,3,5,7},result.getPrimes());
        verify(mock);
    }

}