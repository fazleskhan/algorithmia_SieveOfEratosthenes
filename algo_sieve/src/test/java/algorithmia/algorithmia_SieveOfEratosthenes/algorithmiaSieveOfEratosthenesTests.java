package algorithmia.algorithmia_SieveOfEratosthenes;

import org.junit.Test;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class algorithmiaSieveOfEratosthenesTests {

    @Test
    public void zeroLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();
        InputParams inputParams = new InputParams();
        inputParams.setRangeEnd(0);
        OutputParams result = target.apply(inputParams);
        assertThat(result.toString(), matchesPattern(
                "OutputParams\\{primes=\\[\\], messages=\\[Start calculating prime numbers between 1 and 0, Start Sieve, Number of primes found 0, Sieve took \\d* ms\\], inputParams=InputParams\\{maxPrimeCount=null, rangeStart=null, rangeEnd=0\\}\\}"
        ));
    }

    @Test
    public void oneHundredLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();
        InputParams inputParams = new InputParams();
        inputParams.setRangeEnd(100);
        OutputParams result = target.apply(inputParams);
        assertThat(result.toString(), matchesPattern(
                "OutputParams\\{primes=\\[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97\\], messages=\\[Start calculating prime numbers between 1 and 100, Start Sieve, Number of primes found 25, Sieve took \\d* ms\\], inputParams=InputParams\\{maxPrimeCount=null, rangeStart=null, rangeEnd=100\\}\\}"
        ));
    }

    @Test
    public void nullInputParam() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        try {
            target.apply(null);
            fail("NullPointer exception expected");
        } catch (NullPointerException npe) {
            //success
        }
    }
}
