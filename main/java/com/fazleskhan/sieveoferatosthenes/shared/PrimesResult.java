package com.fazleskhan.sieveoferatosthenes.shared;

import java.util.List;

public class PrimesResult {
    private final List<Integer> primes;
    private final List<String> messages;

    public PrimesResult(List<Integer> primes, List<String> messages) {
        this.messages = messages;
        this.primes = primes;
    }

    public List<Integer> getPrimes() {
        return this.primes;
    }

    public List<String> getMessages() {
        return this.messages;
    }

}
