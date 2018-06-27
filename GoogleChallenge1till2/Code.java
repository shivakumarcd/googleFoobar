package GoogleChallenge1till2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Code {

    public static void main(String[] args) {
        int input[] = {5, 2, 33, 2, 5, 3, 66, 176, 222, 357};
        //Integer input[] = {1};
        System.out.println(answer(input));
    }

    private static int answer(int[] input) {
        Map<Integer, List<Integer>> divisibles = new LinkedHashMap<>();
        int length = input.length;
        for (int i = 0; i < length; i++) {
            List<Integer> divisibleList = new ArrayList<>();
            int divisor = input[i];
            for (int j = i+1; j < length; j++) {
                int dividend = input[j];
                if (dividend % divisor == 0) {
                    divisibleList.add(j);
                }
            }
            divisibles.put(i, divisibleList);
        }

        int count = 0;
        for (int i = 0; i < length; i++) {
            List<Integer> divisibleList = divisibles.get(i);
            for (int j = 0; j < divisibleList.size(); j++) {
                Integer jIndex = divisibleList.get(j);
                count += divisibles.get(jIndex).size();

            }
        }
        return count;
    }

}