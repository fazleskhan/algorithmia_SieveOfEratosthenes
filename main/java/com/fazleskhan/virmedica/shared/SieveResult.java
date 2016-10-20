package com.fazleskhan.virmedica.shared;

public class SieveResult {
    private final Integer[] primes;
    private final String[] messages;

    public SieveResult(Integer[] primes, String[] messages) {
        this.messages = messages;
        this.primes = primes;
    }

    public Integer[] getPrimes() {
        return this.primes;
    }

    public String[] getMessages() {
        return this.messages;
    }
}
