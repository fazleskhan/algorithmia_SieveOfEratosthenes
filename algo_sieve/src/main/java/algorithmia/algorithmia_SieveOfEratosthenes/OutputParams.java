package algorithmia.algorithmia_SieveOfEratosthenes;

import java.util.List;
import java.util.Objects;

public class OutputParams {
    private final List<Integer> primes;
    private final List<String> messages;
    private final InputParams inputParams;

    public OutputParams(InputParams inputParams, List<Integer> primes, List<String> messages) {
        this.messages = messages;
        this.primes = primes;
        this.inputParams = inputParams;
    }

    public List<Integer> getPrimes() {
        return this.primes;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public InputParams getInputParams() { return this.inputParams; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputParams that = (OutputParams) o;
        return Objects.equals(primes, that.primes) &&
                Objects.equals(messages, that.messages) &&
                Objects.equals(inputParams, that.inputParams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primes, messages, inputParams);
    }

    @Override
    public String toString() {
        return "OutputParams{" +
                "primes=" + primes +
                ", messages=" + messages +
                ", inputParams=" + inputParams +
                '}';
    }
}
