package algorithmia.algorithmia_SieveOfEratosthenes;

import java.util.Objects;

public class InputParams {

    private Integer maxPrimeCount;
    private Integer rangeStart;
    private Integer rangeEnd;

    public InputParams(){
        //default constructor
    }

    public Integer getMaxPrimeCount() {
        return maxPrimeCount;
    }

    public void setMaxPrimeCount(Integer maxPrimeCount) {
        this.maxPrimeCount = maxPrimeCount;
    }

    public Integer getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(Integer rangeStart) {
        this.rangeStart = rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Integer rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputParams that = (InputParams) o;
        return Objects.equals(maxPrimeCount, that.maxPrimeCount) &&
                Objects.equals(rangeStart, that.rangeStart) &&
                Objects.equals(rangeEnd, that.rangeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPrimeCount, rangeStart, rangeEnd);
    }

    @Override
    public String toString() {
        return "InputParams{" +
                "maxPrimeCount=" + maxPrimeCount +
                ", rangeStart=" + rangeStart +
                ", rangeEnd=" + rangeEnd +
                '}';
    }
}
