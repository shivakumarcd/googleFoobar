package GoogleChallenge3Code2A2;
//package com.google.challenges;

import java.math.BigInteger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FinalSolution {
    public static final BigInteger TWO = new BigInteger("2");
    public static final BigInteger ONE = new BigInteger("1");
    public static final List<String> EVEN = Arrays.asList("0", "2", "4", "6", "8");

    public static int answer(String n) {

        // Your code goes here.
        BigInteger number = new BigInteger(n);
        int noOfPath = 0;
        List<BigInteger> next = new ArrayList<>();
        next.add(number);
        while (!next.contains(ONE)) {
            next = findNext(next);
            next = simplify(next);
            noOfPath++;
        }
        return noOfPath;

    }
    public static List<BigInteger> simplify(List<BigInteger> next) {
        Collections.sort(next);
        int endIndex = next.size();
        for (int i = 0; i < next.size() - 1 && i >= 0; i++) {
            if(next.get(i).multiply(TWO).compareTo(next.get(i+1)) < 0) {
                endIndex = i + 1;
                break;
            }
        }
        return next.subList(0, endIndex);
    }

    public static List<BigInteger> findNext(List<BigInteger> next) {
        List<BigInteger> newNext = new ArrayList<>();
        for (int i = 0; i < next.size(); i++) {
            BigInteger num = next.get(i);
            if (isEven(num)) {
                addIfNotPresent(newNext, num.divide(TWO));//TODO add only if not present in next
            } else {
                addIfNotPresent(newNext, num.add(BigInteger.ONE)); //TODO add only if not present in next
                addIfNotPresent(newNext, num.subtract(BigInteger.ONE)); //TODO add only if not present in next
            }
        }
        newNext.removeAll(next);
        return newNext;
    }

    public static boolean addIfNotPresent(List<BigInteger> newNext, BigInteger num) {
        return !newNext.contains(num) && newNext.add(num);
    }
    public static boolean isEven(BigInteger num) {
        String numStr = num.toString();
        return EVEN.contains("" + numStr.charAt(numStr.length()-1));
    }

}