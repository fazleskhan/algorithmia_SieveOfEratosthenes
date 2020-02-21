package algorithmia.algorithmia_SieveOfEratosthenes;

import com.fazleskhan.sieveoferatosthenes.PrimesResult;
import com.fazleskhan.sieveoferatosthenes.SieveOfEratosthenes;

import java.util.Objects;

public class algorithmia_SieveOfEratosthenes {

    public static final Integer DEFAULT_RANGE_END = 1000000;
    public static final Integer DEFAULT_RANGE_START = 0;
    public static final Integer DEFAULT_MAX_PRIME_COUNT = 1000;

    private final SieveOfEratosthenes sieve;

    public algorithmia_SieveOfEratosthenes() {
        //default constructor
        sieve = new SieveOfEratosthenes();
    }

    public OutputParams apply(InputParams inputParams) {

        final int lastNumber = Objects.isNull(inputParams.getRangeEnd())
                ? DEFAULT_RANGE_END : inputParams.getRangeEnd();

        final PrimesResult result = sieve.calcPrimes(lastNumber);

        return prepare(inputParams, result);
    }

    private OutputParams prepare (final InputParams inputParams, final PrimesResult primesResult ){
        //TODO filter the list of primes number based on input params
        return new OutputParams(inputParams,primesResult.getPrimes(), primesResult.getMessages());
    }
}
