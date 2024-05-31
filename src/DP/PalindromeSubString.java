package DP;

/**
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/description/
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 */
public class PalindromeSubString {
    /**
     * DP Solution
     */
    public int countSubstrings(String s) {
        // Time: O(N^2), Space: O(N^2)
        if (s.length() < 2) return s.length();

        // f(i, j): Define if s(i => j) is the palindrome
        // f(i, j) = f(i+1, j-1) + [char(i) == char(j)]
        boolean[][] records = new boolean[s.length()][s.length()];

        int result = 0;
        // 特殊的遍历方式是因为转移方程决定的: f(i,j) -> f(i+1, j-1), 所以i, j是由他们
        // 的后一个和前一个决定，那i就从后往前遍历，j就从前往后遍历
        for (int i = s.length() - 1; i >=0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    // record[i][j]是false是不需要专门set的，因为default是false
                    continue;
                }

                if (j - i > 2) {
                    records[i][j] = records[i + 1][j - 1];
                } else {
                    records[i][j] = true;
                }

                if (records[i][j]) result++;
            }
        }

        return result;
    }

    /**
     * 中心拓展的做法，启发是来自我们怎么去判断palindrome, Two pointers，
     * 我们遍历的时候与其寻找i来结尾的palindrome,why don't we find out the palindrome which
     * is centric with i
     */
    public int countSubstrings2(String s) {
        // Time: O(N^2), Space: O(1)
        if (s.length() < 2) return s.length();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd length, single char center
            int left = i, right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                result++;
            }

            // Even length, double chars center
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                result++;
            }
        }

        return result;
    }
}
