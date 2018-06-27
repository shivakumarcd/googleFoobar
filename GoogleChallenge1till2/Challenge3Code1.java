package GoogleChallenge1till2;

/**
 * Created by ShIvS on 6/8/2017.
 */
public class Challenge3Code1 {
    public static void main(String[] args)
    {
        System.out.println(answer(100000,1));
    }
    public static String answer(int x, int y)
    {
        //using formula n(n+1)/2 to find nth element and assuming mathematical induction proof exists
        long xthElement = findXthElementOnGround(x);
        //System.out.println("xthElement:"+xthElement);
        String solution = findYthElementAtX(x,xthElement,y);
        return solution;
    }

    private static String findYthElementAtX(int x, long xthElement, int y) {
        y--;
        while(y>0)
        {
            xthElement = xthElement + x;
            x++;
            y--;
        }
        long elementAtXY = xthElement;
        return String.valueOf(elementAtXY);
    }

    private static long findXthElementOnGround(long x) {
        return (x*(x+1)/2);
    }
}
