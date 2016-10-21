package com.fazleskhan.virmedica;


import com.fazleskhan.virmedica.question1.SieveOfEratosthenes;
import com.fazleskhan.virmedica.question2.MultithreadedSieveOfEratosthenes;
import com.fazleskhan.virmedica.shared.PrimesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class MyController {

    private static final String LASTNUMBER_KEY = "lastNumber";
    private static final String RESULT_KEY = "result";
    private static final String THREADCOUNT_KEY = "threadCount";

    private final SieveOfEratosthenes sieveOfEratosthenes;
    private final MultithreadedSieveOfEratosthenes multithreadedSieveOfEratosthenes;

    @Autowired
    public MyController(SieveOfEratosthenes sieveOfEratosthenes,
                        MultithreadedSieveOfEratosthenes multithreadedSieveOfEratosthenes) {
        this.sieveOfEratosthenes = sieveOfEratosthenes;
        this.multithreadedSieveOfEratosthenes = multithreadedSieveOfEratosthenes;
    }

    @SuppressWarnings({"SameReturnValue", "unused"})
    @RequestMapping("/")
    String index() {
        return "index";
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/solution1")
    public String solution1(@RequestParam(value = LASTNUMBER_KEY, required = false, defaultValue = "") String lastNumber, Model model) {
        model.addAttribute(LASTNUMBER_KEY, lastNumber);
        if (!"".equals(lastNumber)) {
            final int arg = Integer.parseInt(lastNumber);
            final PrimesResult result = getSieveOfEratosthenes().calcPrimes(arg);
            model.addAttribute(RESULT_KEY, result);
        }
        return "solution1";
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/solution2")
    public String solution2a(@RequestParam(value = LASTNUMBER_KEY, required = false, defaultValue = "") String lastNumber,
                             @RequestParam(value = THREADCOUNT_KEY, required = false, defaultValue = "") String threadCount,
                             Model model) {
        model.addAttribute(LASTNUMBER_KEY, lastNumber);
        model.addAttribute(THREADCOUNT_KEY, threadCount);
        if (!"".equals(lastNumber)) {
            final int arg0 = Integer.parseInt(lastNumber);
            final int arg1 = Integer.parseInt(threadCount);
            final PrimesResult result = getMultithreadedSieveOfEratosthenes().calcPrimes(arg0, arg1);
            model.addAttribute(RESULT_KEY, result);
        }
        return "solution2";
    }

    private SieveOfEratosthenes getSieveOfEratosthenes() {
        return this.sieveOfEratosthenes;
    }

    private MultithreadedSieveOfEratosthenes getMultithreadedSieveOfEratosthenes() {
        return this.multithreadedSieveOfEratosthenes;
    }
}