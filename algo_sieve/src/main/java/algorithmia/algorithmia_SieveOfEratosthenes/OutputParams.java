package algorithmia.algorithmia_SieveOfEratosthenes;

import java.util.List;
import java.util.Objects;

public class OutputParams {
    private final List<Integer> primes;
    private final List<String> messages;

    public OutputParams(List<Integer> primes, List<String> messages) {
        this.messages = messages;
        this.primes = primes;
    }

    public List<Integer> getPrimes() {
        return this.primes;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputParams that = (OutputParams) o;
        return Objects.equals(primes, that.primes) &&
                Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primes, messages);
    }

    @Override
    public String toString() {
        return "OutputParams{" +
                "primes=" + primes +
                ", messages=" + messages +
                '}';
    }
}
