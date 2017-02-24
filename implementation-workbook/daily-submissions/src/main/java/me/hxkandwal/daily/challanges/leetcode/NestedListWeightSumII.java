package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 364. Nested List Weight Sum II
 * 
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level 
 * integers have weight 1, and the root level integers have the largest weight.
 * 
 * Example 1:
 * 		Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)
 * 
 * Example 2:
 * 		Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 * 
 * @author Hxkandwal
 */
public class NestedListWeightSumII extends AbstractCustomTestRunner {

	public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0;
        List<Integer> ans = new ArrayList<>();
        depthSum (nestedList.iterator(), 1, ans);
        for (int idx = 0; idx < ans.size(); idx ++) res += ans.get (idx) * (ans.size() - idx);
        return res;
    }
    
    private void depthSum (Iterator<NestedInteger> iter, int depth, List<Integer> ans) {
        while (iter.hasNext()) {
            NestedInteger n = iter.next();
            if (n.isInteger()) {
                while (depth > ans.size()) ans.add (0);
                ans.set (depth - 1, ans.get (depth - 1) + n.getInteger());
            } else depthSum (n.getList().iterator(), depth + 1, ans);
        }
    }
    
}
