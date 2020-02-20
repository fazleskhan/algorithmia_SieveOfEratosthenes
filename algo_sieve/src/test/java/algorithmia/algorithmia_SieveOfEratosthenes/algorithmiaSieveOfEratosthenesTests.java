package algorithmia.algorithmia_SieveOfEratosthenes;

import org.junit.Test;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class algorithmiaSieveOfEratosthenesTests {

    @Test
    public void zeroLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        String result = target.apply(new InputParams(0));
        assertThat(result, matchesPattern(
                "Sieve of Eratosthenes \\(last number 0\\): messages - \\[Start calculating prime numbers between 1 and 0, Start Sieve, Number of primes found 0, Sieve took \\d* ms\\] primes - \\[\\]"
        ));
    }

    @Test
    public void oneHundredLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        String result = target.apply(new InputParams(100));
        assertThat(result, matchesPattern(
                "Sieve of Eratosthenes \\(last number 100\\): messages - \\[Start calculating prime numbers between 1 and 100, Start Sieve, Number of primes found 25, Sieve took \\d* ms\\] primes - \\[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97\\]"
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

    @Test
    public void nullLastNumber() {
        algorithmia_SieveOfEratosthenes target = new algorithmia_SieveOfEratosthenes();

        try {
            target.apply(new InputParams(null));
            fail("NullPointer exception expected");
        } catch (NullPointerException npe) {
            //success
        }
    }
}
