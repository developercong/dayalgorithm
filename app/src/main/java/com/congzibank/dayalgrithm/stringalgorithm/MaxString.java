package com.congzibank.dayalgrithm.stringalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.crypto.Mac;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * Created by cong_wang on 2018/12/6.
 */

public class MaxString {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] map = new int[256];
        for (int i : map) {
            i = -1;
        }

        int pre = -1;
        int len = 0;
        int cur = 0;
        for (int i = 0 ; i < chars.length; i++) {
            pre = Math.max(pre, map[chars[i]]);
            cur = i - pre;
            len = Math.max(cur, len);
            map[chars[i]] =i;
        }
        return len;
    }

    /**
     * 求字符串数组的最长公共前缀
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 输入: ["flower","flow","flight"]
        输出: "fl"
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int i = 0;
        int length = Integer.MAX_VALUE;
        for (String str : strs) {
            length = Math.min(length, str.length());
        }
        for (i = 0; i < length ; i++) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[0].charAt(i) !=strs[j].charAt(i)){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0].substring(0, i);
    }

    /**
     * 滑动窗口方法， 定义一个和s1 一样长度的窗口
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s2 == null || s1 == null) return false;

        int len1 = s1.length();
        int len2 = s2.length();

        int[] cha1 = new int[26];
        int[] cha2 = new int[26];
        //得到s1的字符串的字符数的数组
        for (char c : s1.toCharArray()) {
            cha1[c - 'a']++;
        }

        for (int i = 0; i < len2; i++) {
            //大于s1的长度后，需要减去上一个的字符，这样保证了窗口的长度一直是s1的长度。
            if (i >= len1) {
                cha2[s2.charAt(i - len1) - 'a']--;
            }
            cha2[s2.charAt(i) - 'a']++;

            if (Arrays.equals(cha1, cha2)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

     示例 1:

     输入: num1 = "2", num2 = "3"
     输出: "6"
     示例 2:

     输入: num1 = "123", num2 = "456"
     输出: "56088"
     说明：

     num1 和 num2 的长度小于110。
     num1 和 num2 只包含数字 0-9。
     num1 和 num2 均不以零开头，除非是数字 0 本身。
     不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.equals("")|| num2 == null || num2.equals("")) return "";

        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        Stack<Integer> stack = new Stack<>();
        return "";
    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        if (grid == null || grid.length == 0) return 0;
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int num = deepSearch(grid, i, j);
                    max = Math.max(max, num);
                }
            }
        }
        return max;
    }

    private int deepSearch(int[][] grid, int i, int j) {
        int num = 0;
        //去除 搜索过的点，减少
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 0;
             num = 1 + deepSearch(grid, i - 1, j) + deepSearch(grid, i + 1, j) + deepSearch(grid, i ,j - 1) + deepSearch(grid, i, j + 1);
        }
        return num;
    }


    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //表示现在这个子数组是有序的
            if (nums[mid] > nums[start]) {
                //只有在这个范围内的target才能查找到
                if(nums[start]<= target && target < nums[mid]){
                    end = mid-1;
                }else{
                    start = mid+1;
                }
            } else {
                //表明这个数组是有序的判断是否在这个范围内，不是的话就在另一个数组中
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid == 0 ? mid : mid - 1;
                }
            }

        }
        return -1;

    }

    public boolean isValidString(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] aChars = s.toCharArray();
        char[] bChars = t.toCharArray();

        for (int i = 0; i < aChars.length ; i++) {
            map.put(aChars[i], map.get(aChars[i]) == null ? 1 : map.get(aChars[i]) + 1);
        }
        for (int j = 0 ; j < bChars.length; j++) {
            if (map.get(bChars[j]) == null || map.get(bChars[j]) < 1) {
                return false;
            }
            map.put(bChars[j], map.get(bChars[j]) - 1);
        }
        return true;
    }

    /**
     * 恢复ip地址，使用 递归暴力求解
     * 1 定义这个ip段是否有先 函数 辅助函数
     * 2 使用 int 变量 表示当前递归到第几段，如果到了第四段，则保存到result中，
     * 3 否则判断 当前段 1-3长度的可能性是否有效
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        search(result, "", s, 0);
        return result;
    }

    /**
     * 这个递归函数
     * @param result 结果
     * @param tempString  临时生成的字符串，用来保存之前符合条件的ip段
     * @param remain 剩余还没被分段的字符串
     * @param start 已经划分的段数
     */
    private void search(List<String> result, String tempString, String remain, int start) {
        if (start == 3) { // 表明是第四段ip
            if (isValid(remain)) {
                result.add(tempString + remain);
            }
            return;
        }

        //每一段的ip有三种可能的长度
        for (int i = 1 ; i < 4 && i < remain.length(); i++) {
            String curr = remain.substring(0, i);// 得到每种的字符
            //判断是否有效
            if (isValid(curr)) {
                search(result, tempString + curr + ".", remain.substring(i), start + 1);
            }
        }

    }

    /**
     * 判断字符串是否符合IP地址的标准
     * 0-255
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if(s.charAt(0) == '0')
            return s.equals("0"); // to eliminate cases like "00", "10"
        int digit = Integer.valueOf(s);
        return digit >= 0 && digit <= 255;
    }

    /**
     *  朋友圈数量
     *  班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

     给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < M.length ; i++) {
            for (int j = i; j < M.length ; j++ ) {

            }
        }
        return 0;
    }
}
