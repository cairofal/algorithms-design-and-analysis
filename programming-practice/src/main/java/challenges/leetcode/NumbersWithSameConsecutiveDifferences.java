package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 967. Numbers With Same Consecutive Differences
 *
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 * You may return the answer in any order.
 *
 * Example 1:
 *      Input: N = 3, K = 7
 *      Output: [181,292,707,818,929]
 *      Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 *      Input: N = 2, K = 1
 *      Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * Note:
 *  1. 1 <= N <= 9
 *  2. 0 <= K <= 9
 *
 * @author Hxkandwal
 */
public class NumbersWithSameConsecutiveDifferences extends AbstractCustomTestRunner {

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> ans;
        if (N == 1) ans = Arrays.asList (0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        else {
            ans = new ArrayList<>();
            for (Integer val : numsSameConsecDiff(N - 1, K)) {
                if (val > 0 && val %10 + K < 10) ans.add (10 * val + (val %10 + K));
                if (val > 0 && K > 0 && val %10 - K >= 0) ans.add (10 * val + (val %10 - K));
            }
        }
        return ans.stream().mapToInt(j -> j).toArray();
    }

}
