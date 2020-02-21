package algorithmia.algorithmia_SieveOfEratosthenes;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class OutputParamsTests {

    @Test
    public void nullValuesTest(){
        OutputParams target = new OutputParams(null,null,null);
        assertThat(target.toString(),equalTo("OutputParams{primes=null, messages=null, inputParams=null}"));
    }

    @Test
    public void nonNullValuesTest(){
        OutputParams target = new OutputParams(new InputParams(),new ArrayList<>(), new ArrayList<>());
        assertThat(target.toString(),equalTo("OutputParams{primes=[], messages=[], inputParams=InputParams{maxPrimeCount=null, rangeStart=null, rangeEnd=null}}"));
    }
}
