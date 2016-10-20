package com.fazleskhan.virmedica.question1;

import com.fazleskhan.virmedica.shared.Helper;
import com.fazleskhan.virmedica.shared.SieveResult;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import static org.easymock.EasyMock.*;

//import org.junit.Assert;

@RunWith(EasyMockRunner.class)
public class SingleThreadedSieveTest {

    private SingleThreadedSieve target;

    private Helper mock;

    @Before
    public void setUp(){
        mock = createMock(Helper.class);
        target = new SingleThreadedSieve(mock);
    }

    @Test
    public void calcPrimesZeroLength() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        SieveResult result = target.calcPrimes(0);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesOneLength() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        SieveResult result = target.calcPrimes(1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLength() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false,false});
        replay(mock);
        SieveResult result = target.calcPrimes(1);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[0],result.getPrimes());
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLength() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false,false,true});
        replay(mock);
        SieveResult result = target.calcPrimes(3);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[]{2},result.getPrimes());
        verify(mock);
    }
    @Test
    public void calcPrimesTenLength() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false,false,true,true,true,true,true,true,true,true});
        replay(mock);
        SieveResult result = target.calcPrimes(10);
        assertNotEquals(0,result.getMessages().length);
        assertArrayEquals(new Integer[]{2,3,5,7},result.getPrimes());
        verify(mock);
    }
}