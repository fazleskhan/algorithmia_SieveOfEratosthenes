package algorithmia.algorithmia_SieveOfEratosthenes;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class InputParamsTests {

    @Test
    public void nullValuesTest(){
        InputParams target = new InputParams();
        assertThat(target.toString(),equalTo("InputParams{maxPrimeCount=null, rangeStart=null, rangeEnd=null}"));
    }

    @Test
    public void zeroValuesTest(){
        InputParams target = new InputParams();
        target.setRangeEnd(0);
        target.setRangeStart(0);
        target.setMaxPrimeCount(0);
        assertThat(target.toString(),equalTo("InputParams{maxPrimeCount=0, rangeStart=0, rangeEnd=0}"));
    }

}
