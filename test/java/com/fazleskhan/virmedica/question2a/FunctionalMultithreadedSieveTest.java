package com.fazleskhan.virmedica.question2a;

import com.fazleskhan.virmedica.shared.Helper;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import static org.easymock.EasyMock.*;

/**
 * Created by Fazle Khan on 10/20/2016.
 */
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
        String[] result = target.calcPrimes(0,1);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesOneLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        String[] result = target.calcPrimes(1,1);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false,false});
        replay(mock);
        String[] result = target.calcPrimes(1,1);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false,false,true});
        replay(mock);
        String[] result = target.calcPrimes(3,1);
        Assert.notEmpty(result);
        verify(mock);
    }
    @Test
    public void calcPrimesTenLengthOneThread() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false,false,true,true,true,true,true,true,true,true});
        replay(mock);
        String[] result = target.calcPrimes(10,1);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesZeroLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(0)).andReturn(new boolean[0]);
        replay(mock);
        String[] result = target.calcPrimes(0,2);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesOneLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false});
        replay(mock);
        String[] result = target.calcPrimes(1,2);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesTwoLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(1)).andReturn(new boolean[]{false,false});
        replay(mock);
        String[] result = target.calcPrimes(1,2);
        Assert.notEmpty(result);
        verify(mock);
    }

    @Test
    public void calcPrimesThreeLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(3)).andReturn(new boolean[]{false,false,true});
        replay(mock);
        String[] result = target.calcPrimes(3,2);
        Assert.notEmpty(result);
        verify(mock);
    }
    @Test
    public void calcPrimesTenLengthTwoThread() throws Exception {
        EasyMock.expect(mock.initPrimes(10)).andReturn(new boolean[]{false,false,true,true,true,true,true,true,true,true});
        replay(mock);
        String[] result = target.calcPrimes(10,2);
        Assert.notEmpty(result);
        verify(mock);
    }

}