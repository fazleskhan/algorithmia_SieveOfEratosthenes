package com.fazleskhan.virmedica.shared;

public class SeiveResult {
    private final Integer[] primes;
    private final String[] messages;

    public SeiveResult(Integer[] primes, String[] messages){
        this.messages = messages;
        this.primes = primes;
    }

    public Integer[] getPrimes(){
        return this.primes;
    }

    public String[] getMessages(){
        return this.messages;
    }
}
