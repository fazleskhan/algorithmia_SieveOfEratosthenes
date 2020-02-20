package algorithmia.algorithmia_SieveOfEratosthenes;

import com.fazleskhan.sieveoferatosthenes.PrimesResult;
import com.fazleskhan.sieveoferatosthenes.SieveOfEratosthenes;

public class algorithmia_SieveOfEratosthenes {

    private final SieveOfEratosthenes sieve;

    public algorithmia_SieveOfEratosthenes() {
        //default constructor
        sieve = new SieveOfEratosthenes();
    }

    public OutputParams apply(InputParams inputParams) {
        final int lastNumber = inputParams.getLastNumber();

        final PrimesResult result = sieve.calcPrimes(lastNumber);

        return new OutputParams(result.getPrimes(),result.getMessages());
    }
}
