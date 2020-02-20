package algorithmia.algorithmia_SieveOfEratosthenes;

import com.fazleskhan.sieveoferatosthenes.PrimesResult;
import com.fazleskhan.sieveoferatosthenes.SieveOfEratosthenes;

public class algorithmia_SieveOfEratosthenes {

    private final SieveOfEratosthenes sieve;

    public algorithmia_SieveOfEratosthenes() {
        //default constructor
        sieve = new SieveOfEratosthenes();
    }

    public String apply(InputParams inputParams) {
        final int lastNumber = inputParams.getLastNumber();

        final PrimesResult result = sieve.calcPrimes(lastNumber);

        return String.format("Sieve of Eratosthenes (last number %s): messages - %s primes - %s",
                lastNumber, result.getMessages(), result.getPrimes());

    }
}
