package algorithmia.algorithmia_SieveOfEratosthenes;

import java.util.Objects;

public class InputParams {

    private Integer lastNumber;

    public InputParams(Integer lastNumber){
        this.lastNumber = lastNumber;
    }

    public Integer getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(Integer lastNumber) {
        this.lastNumber = lastNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParams that = (InputParams) o;
        return Objects.equals(lastNumber, that.lastNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastNumber);
    }

    @Override
    public String toString() {
        return "InputParams{" +
                "lastNumber=" + lastNumber +
                '}';
    }
}
