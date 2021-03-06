package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 532. K-diff Pairs in an Array
 * 
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as 
 * an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 * 
 * Example 1:
 * 		Input: [3, 1, 4, 1, 5], k = 2
 * 		Output: 2
 * 		Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * 					 Although we have two 1s in the input, we should only return the number of unique pairs.
 * 
 * Example 2:
 * 		Input:[1, 2, 3, 4, 5], k = 1
 * 		Output: 4
 * 		Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * 		
 * Example 3:
 * 		Input: [1, 3, 1, 5, 4], k = 0
 * 		Output: 1
 * 		Explanation: There is one 0-diff pair in the array, (1, 1).
 * 
 * Note:
 * 	The pairs (i, j) and (j, i) count as the same pair.
 * 	The length of the array won't exceed 10,000.
 * 	All the integers in the given input belong to the range: [-1e7, 1e7].
 * 
 * @author Hxkandwal
 */
public class KDiffPairsInAnArray extends AbstractCustomTestRunner {
	
	private static KDiffPairsInAnArray _instance = new KDiffPairsInAnArray();
	
	//  two-pointers approach
	public int findPairsBetter(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0, end = 1, result = 0;
        
        while (start < nums.length && end < nums.length) {
            if (start == end || nums[start] + k > nums[end]) end++;
            else if (nums[start] + k < nums[end]) start++;
            else {
                start ++; result ++;
                while (start < nums.length && nums[start] == nums[start - 1]) start++;
                end = Math.max (end + 1, start + 1);
            }
        }
        
        return result;
    }
	
	public int _findPairs(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k < 0) return 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put (num, map.getOrDefault(num, 0) + 1);
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) count ++;
            } else {
                if (map.containsKey(entry.getKey() + k)) count ++;
            }
        }
        return count;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 1, 4, 1, 5 }, 2, 2);
		_instance.runTest(new int[] { 1, 3, 1, 5, 4 }, 0, 1);
	}

	public void runTest(final int[] nums, int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}  
	
}
