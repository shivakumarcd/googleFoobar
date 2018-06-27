package GoogleChallenge3Code3A1;

/**
 * Created by ShIvS on 6/19/2017.
 */
public class XorCalculator {

    public static void main(String[] args) {
        System.out.println(answer(0,3));
        System.out.println(answer(17,4));

        /*
        System.out.println(findXorBetween(0,4)^6);
        System.out.println(findXorBetween(17,23)^25^26^29);
        */
    }

    public static int answer(int start, int length) {
        int totalXor = 0;
        int rowXor = 0;
        int end = start + length - 1;
        for (int i = 0; i <length ; i++) {
            rowXor = findXorBetween(start,end);
            totalXor = totalXor ^ rowXor;

            start = start + length;
            end = start + length - (i+2);
        }
        return totalXor;
    }

    public static int findXorBetween(int a, int b) {
        return findXorTill(b)^findXorTill(a-1);
    }
    public static int findXorTill(int num)
    {
        int reminder = num % 4;
        if(reminder == 0)
            return num;
        else
        {
            switch (reminder){
                case 1: return 1;
                case 2: return num+1;
                case 3: return 0;
            }
        }

        return 0;
    }
}
/*
0   0000 <- 0  [a]
1   0001 <- 1  [1]
2   0010 <- 3  [a+1]
3   0011 <- 0  [0]

4   0100 <- 4  [a]
5   0101 <- 1  [1]
6   0110 <- 7  [a+1]
7   0111 <- 0  [0]

8   1000 <- 8  [a]
9   1001 <- 1  [1]
10  1010 <- 11 [a+1]
11  1011 <- 0  [0]

12  1100 <- 12 [a]
13  1101 <- 1  [1]
14  1110 <- 15 [a+1]
15  1111 <- 0  [0]

16 10000 <- 16 [a]
17 10001 <- 1  [1]
18 10010 <- 19 [a+1]
19 10011 <- 0  [0]

20 10100 <- 20 [a]
 */