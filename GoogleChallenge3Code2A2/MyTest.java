package GoogleChallenge3Code2A2;

import java.math.BigInteger;

/**
 * Created by ShIvS on 6/16/2017.
 */
public class MyTest {
    public static void main(String[] args) {
        String num = "", nine = "9";
        for (int i = 0; i < 310; i++) {
            num += nine;
        }

        BigInteger n = new BigInteger(num);
        System.out.println("num: " + num);
        System.out.println("num: " + n);

        System.out.println(n.divide(n));
    }
}
