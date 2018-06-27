package GoogleChallenge3Code2A2;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by ShIvS on 6/16/2017.
 */
public class Solution1a {
    public static HashMap<String,Integer> operastionMap = new HashMap<>();
    public static final double LOG2 = Math.log( 2.0 );

    public static void main(String[] args) {
        System.out.println(findNumberOfOperations("9999"));
        /*
        long start = System.currentTimeMillis();
        System.out.println(findNumberOfOperations("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"));
        long end =System.currentTimeMillis();

        System.out.println("time=" + (end-start));
        */
        /*
        for (int i = 1; i < 32; i++) {
            System.out.print(findNumberOfOperations(Integer.toString(i))+",");
        }

        */
        /*
        System.out.println("number of oprns required for 32 =" + findNumberOfOperations("32"));
        System.out.println("number of oprns required for 15 =" + findNumberOfOperations("15"));
        System.out.println("number of oprns required for 7 =" + findNumberOfOperations("7"));
        System.out.println("number of oprns required for 20 =" + findNumberOfOperations("20"));
        System.out.println("number of oprns required for 10 =" + findNumberOfOperations("10"));



        BigInteger number = new BigInteger("5357543035931336604742125245300009052807024058527668037218751941851755255624680612465991894078479290637973364587765734125935726428461570217992288787349287401967283887412115492710537302531185570938977091076523237491790970633699383779582771973038531457285598238843271083830214915826312193418602834034689");

        System.out.println(logBigInteger(number)/Math.log(2));
        */
       // populate();
       /*
        for (String number: operastionMap.keySet()){
            System.out.println(operastionMap.get(number) +":" + number);
        }
        */

    }
    public static double logBigInteger(BigInteger val) {
        int blex = val.bitLength() - 1022; // any value in 60..1023 is ok
        if (blex > 0)
            val = val.shiftRight(blex);
        double res = Math.log(val.doubleValue());
        return blex > 0 ? res + blex * LOG2 : res;
    }
    public static int findNumberOfOperations(String num) {
        Integer numberOfOperations = operastionMap.get(num);
        if(numberOfOperations!=null)
            return numberOfOperations;

        BigInteger number = new BigInteger(num);
        BigInteger n0 = new BigInteger("0");
        BigInteger n1 = new BigInteger("1");
        BigInteger n2 = new BigInteger("2");
        if(number.equals(n1))
            return 0;
        else if(number.mod(n2).equals(n0))
            return findNumberOfOperations(number.divide(n2).toString())+1;
        else
        {
            int x = findNumberOfOperations(number.subtract(n1).toString());
            int y = findNumberOfOperations(number.add(n1).toString());
            return (Math.min(x,y)+1);
        }

    }
    private static BigInteger populate() {
        BigInteger twosMultiples = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        for (int i = 0; i < 999; i++) {
            twosMultiples = twosMultiples.multiply(two);
            operastionMap.put(twosMultiples.toString(),i+1);
        }
        return twosMultiples;
    }
}