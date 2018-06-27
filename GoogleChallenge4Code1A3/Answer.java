//package com.google.challenges;
/**
 * Created by ShIvS on 7/22/2017.
 */

package GoogleChallenge4Code1A3;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Answer {

    public static int returnValue = 100;
    public static void main(String[] args) {
        callFunctions();
    }

    public static int answer(int[] banana_list) {

        // Your code goes here.
        if(banana_list.length % 2 == 1){
            throw new RuntimeException("someMessagehere");
        }
        Arrays.sort(banana_list);
        //System.out.println(Arrays.toString(banana_list));

        int[] indexOfNextPair = new int[banana_list.length];
        /**
         * +ve == paired
         * 0 == paired
         * -1 == not paired yet
         * -100 == no pair found
         */
        for (int i = 0; i < indexOfNextPair.length; i++) {
            indexOfNextPair[i] = -1;
        }
        generatePairInfo(banana_list, indexOfNextPair);
        return Answer.returnValue;
    }

    public static void callFunctions(){
        //System.out.println("calling function generateInversePairs()");
        callingFunctionGenerateInversePairs();
        //System.out.println("*******************************************************************************************");

        //System.out.println("calling function isValidPair1()");
        callingFunctionIsValidPair();
    }

    public static void callingFunctionGenerateInversePairs(){
        for (int i = 2; i <= 20; i = i+2) {
            Pair.generateInvalidPairs(i, i);
            //System.out.println("\n");
        }
    }

    public static void callingFunctionIsValidPair(){
        HashSet<Pair> setOfPairs = new HashSet<>();
        //System.out.println("\nfor (1,7)");
        boolean validity = Pair.isValidPair1(1,7, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(1,7));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (1,31)");
        validity = Pair.isValidPair1(1,31, setOfPairs);
        //System.out.println(validity  + " ===" + Pair.isValidPair(1,31) );
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (5,35)");
        validity = Pair.isValidPair1(5,35, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(5,35));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (1,15)");
        validity = Pair.isValidPair1(1,15, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(1,15));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (1,3)");
        validity = Pair.isValidPair1(1,3, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(1,3));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (1,5)");
        validity = Pair.isValidPair1(1,5, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(1,5));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (11,55)");
        validity = Pair.isValidPair1(11,55, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(11,55));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (36,24)");
        validity = Pair.isValidPair1(36,24, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(36,24));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (13,1)");
        validity = Pair.isValidPair1(1,13, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(1,13));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (3,7)");
        validity = Pair.isValidPair1(3,7, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(3,7));
        //System.out.println(setOfPairs);

        setOfPairs = new HashSet<>();
        //System.out.println("\nfor (19,21)");
        validity = Pair.isValidPair1(19,21, setOfPairs);
        //System.out.println(validity + " ===" + Pair.isValidPair(19,21));
        //System.out.println(setOfPairs);
    }

    /**
     *
     * @param banana_list
     * @param indexOfNextPair
     * quit if Answer.returnValue = length /2
     * Else keep trying all combinations to ensure max value of Answer.returnValue is not missed
     *
     */
    public static void generatePairInfo(int[] banana_list, int[] indexOfNextPair){
        for (int i = 0; i < indexOfNextPair.length; i++) {
            if(indexOfNextPair[i] == -1){

            }
        }
    }
}

class Pair {
    int minNum1;
    int num2;

    public Pair(int num1, int num2) {
        if (num1 < num2) {
            minNum1 = num1;
            this.num2 = num2;
        } else{
            minNum1 = num2;
            this.num2 = num1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Pair incoming = (Pair) obj;
        if(incoming.getMinNum1() == minNum1 && incoming.getNum2() == num2){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return minNum1+num2;
    }

    @Override
    public String toString() {
        return "(" + minNum1 + "," + num2 + ")";
    }

    public static void generateInvalidPairs(int num1, int num2){
        Pair pair = new Pair(num1, num2);
        //System.out.print(pair.toString() + " = ");
        while(pair.getMinNum1() > 1) {
            if(pair.getMinNum1() % 2 == 1){
                break;
            }
            int tmp = pair.getMinNum1()/2;
            pair.setMinNum1(tmp);
            pair.setNum2(pair.getNum2()+tmp);
            //System.out.print(pair.toString() + ", ");
            }
    }

    public static boolean isValidPair1(int num1, int num2, HashSet<Pair> setOfPairs ){
        Pair pair = new Pair(num1, num2);
        setOfPairs.add(pair);
        do{
            Pair nextPair = new Pair(pair.getMinNum1() * 2, pair.getNum2() - pair.getMinNum1());
            if(nextPair.getMinNum1() == nextPair.getNum2()){
                setOfPairs.add(nextPair);
                return false;
            }
            if(setOfPairs.contains(nextPair)) {
                setOfPairs.add(nextPair);
                return true;
            }
            setOfPairs.add(nextPair);
            pair = nextPair;
        }while(true);
    }

    /* written considering pattern generated for (1,1)(2,2)(3,3)(4,4)(5,5)... and all pairs that lead to these*/
    public static boolean isValidPair(int num1, int num2){
        int min, max;
        if(num1 > num2){
            min = num2;
            max = num1;
        } else {
            min = num1;
            max = num2;
        }

        int gcd = egcd(min, max);
        min = min/gcd;
        max = max/gcd;
        double result = (double) max / min;
        double log2 = Math.log(result + 1) / Math.log(2);
//        //System.out.println("log2= " + log2);
        int intValue = (int) log2;
        if(intValue == log2){
            return false;
        }
        if(max == (min +1) ){
            return true;
        }
        return isValidPair1(min, max, new HashSet<Pair>());
    }
    public static int egcd(int a, int b) {
        if (a == 0)
            return b;

        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }

        return a;
    }

    /***************************************************************************/
    public void setMinNum1(int minNum1) {
        this.minNum1 = minNum1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getMinNum1() {
        return minNum1;
    }

    public int getNum2() {
        return num2;
    }
}
//last line