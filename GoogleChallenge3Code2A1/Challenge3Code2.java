package GoogleChallenge3Code2A1;

import java.util.Arrays;

public class Challenge3Code2 {

    public static void main(String[] args) {


        int[][] m = {{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};//with
        //int[][] m = {{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};//without
        //int[][] m = {{0,1,1,0,0,0,0},{0,0,0,1,0,1,0},{0,0,0,1,0,0,0},{0,0,0,0,1,0,1},{0,0,0,0,0,0,0},{ 0,0,0,0,0,0,0},{ 0,0,0,0,0,0,0}};
        int[] ans = Answer.answer(m);
        System.out.println(Arrays.toString(ans));
    }
}