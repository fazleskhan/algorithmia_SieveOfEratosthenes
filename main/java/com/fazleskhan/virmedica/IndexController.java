package com.fazleskhan.virmedica;


import com.fazleskhan.virmedica.question1.SieveOfEratosthenes;
import com.fazleskhan.virmedica.question2.MultithreadedSieve;
import com.fazleskhan.virmedica.question2a.FunctionalMultithreadedSieve;
import com.fazleskhan.virmedica.shared.SieveResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
class IndexController {

    private final SieveOfEratosthenes sieveOfEratosthenes;
    private final MultithreadedSieve multithreadedSieve;
    private final FunctionalMultithreadedSieve functionalMultithreadedSieve;

    @Autowired
    public IndexController(SieveOfEratosthenes sieveOfEratosthenes,
                           MultithreadedSieve multithreadedSieve,
                           FunctionalMultithreadedSieve functionalMultithreadedSieve) {
        this.sieveOfEratosthenes = sieveOfEratosthenes;
        this.multithreadedSieve = multithreadedSieve;
        this.functionalMultithreadedSieve = functionalMultithreadedSieve;
    }

    @SuppressWarnings({"SameReturnValue", "unused"})
    @RequestMapping("/")
    String index() {
        return "index";
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/solution1")
    public String solution1(@RequestParam(value = "lastNumber", required = false, defaultValue = "") String lastNumber, Model model) {
        model.addAttribute("lastNumber", lastNumber);
        if (!"".equals(lastNumber)) {
            final int arg = Integer.parseInt(lastNumber);
            final SieveResult result = getSieveOfEratosthenes().calcPrimes(arg);
            for (int i = 1; i < result.getMessages().length; i++) {
                model.addAttribute("messages", Arrays.toString(result.getMessages()));
            }
            model.addAttribute("primes", Arrays.toString(result.getPrimes()));
        }
        return "solution1";
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/solution2")
    public String solution2a(@RequestParam(value = "lastNumber", required = false, defaultValue = "") String lastNumber,
                             @RequestParam(value = "threadCount", required = false, defaultValue = "") String threadCount,
                             Model model) {
        model.addAttribute("lastNumber", lastNumber);
        model.addAttribute("threadCount", threadCount);
        if (!"".equals(lastNumber)) {
            final int arg0 = Integer.parseInt(lastNumber);
            final int arg1 = Integer.parseInt(threadCount);
            final SieveResult result = getMultithreadedSieve().calcPrimes(arg0, arg1);
            for (int i = 1; i < result.getMessages().length; i++) {
                model.addAttribute("messages", Arrays.toString(result.getMessages()));
            }
            model.addAttribute("primes", Arrays.toString(result.getPrimes()));
        }
        return "solution2";
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/solution2a")
    public String solution2(@RequestParam(value = "lastNumber", required = false, defaultValue = "") String lastNumber,
                            @RequestParam(value = "threadCount", required = false, defaultValue = "") String threadCount,
                            Model model) {
        model.addAttribute("lastNumber", lastNumber);
        model.addAttribute("threadCount", threadCount);
        if (!"".equals(lastNumber)) {
            final int arg0 = Integer.parseInt(lastNumber);
            final int arg1 = Integer.parseInt(threadCount);
            final SieveResult result = getFunctionalMultithreadedSieve().calcPrimes(arg0, arg1);
            for (int i = 1; i < result.getMessages().length; i++) {
                model.addAttribute("messages", Arrays.toString(result.getMessages()));
            }
            model.addAttribute("primes", Arrays.toString(result.getPrimes()));
        }
        return "solution2";
    }


    private SieveOfEratosthenes getSieveOfEratosthenes() {
        return this.sieveOfEratosthenes;
    }

    private MultithreadedSieve getMultithreadedSieve() {
        return this.multithreadedSieve;
    }

    private FunctionalMultithreadedSieve getFunctionalMultithreadedSieve() {
        return this.functionalMultithreadedSieve;
    }
}