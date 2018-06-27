package GoogleChallenge3Code2A2;

import java.math.BigInteger;

/**
 * Created by ShIvS on 6/17/2017.
 */
public class Solution1b {
    public static void main(String[] args) {
        BigInteger b = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        BigInteger n0 = new BigInteger("0");
        BigInteger n1 = new BigInteger("1");
        BigInteger n2 = new BigInteger("2");

        while(!b.equals(n1))
        {
            b = b.divide(n2);
        }
        System.out.println("reached here");

        b = new BigInteger("5");
        System.out.println(b.divide(n2));
    }
}
