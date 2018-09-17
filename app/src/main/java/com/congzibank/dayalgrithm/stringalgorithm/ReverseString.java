package com.congzibank.dayalgrithm.stringalgorithm;

/**
 * Created by cong_wang on 2018/9/17.
 *
 * 1.1 旋转字符串
 * 给定一个字符串，要求将字符串前面的若干个字符移动到字符串的尾部
 * 如 把 字符串 'abcdef' 前面 的2个字符'a'和'b'移动到字符串的尾部
 * 变成'cdeab'. 要求对长度为n的字符串 时间复杂度为O(n),空间复杂度为O(1)
 *
 */


public class ReverseString {

    /**
     * 第一版的字符串旋转，将需要移动的字符一个个移动到字符串的尾部
     * @param s 被移动的字符串
     * @param n 字符需要移动到的位置
     *  这个函数的含义是将 字符串头部的字符移动到m位置上
     */
    public static String reverseOneString(String s, int n) {
        char[] chars = s.toCharArray();
        char first = chars[0];
        for (int i = 0; i < n; i++){
            chars[i-1] = chars[i];
        }
        chars[n - 1] = first;
        return String.valueOf(chars);
    }

    /**
     * 将字符串s前面m个字符移动到 n的位置
     * @param s 这个函数需要 m*n次操作，而且空间复杂度较高
     * @param n
     * @param m
     * @return
     */
    public static String reverseString(String s, int n, int m) {
        //对每个字符都移动一遍
        while(m-- > 0) {
            s = reverseOneString(s, n);
        }
        return s;
    }


    /**
     * 三步反转发
     * <p>
     * 假设 X = "abc" 则 X^ ="cba", X^^  = "abc" = X
     * 将字符串 "abdcef" 分成两部分 X, Y
     * 则  (X^Y^)^ = YT
     * 算法步骤
     * 1 将字符串 分为两部分 X Y
     * 2 对 X反转 X-->X^ Y-->Y^
     * 3 将 X^Y^ 反转--> YX
     */

    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int lo = 0;
        int hi = chars.length;
        while(lo ++ < hi--){
            char temp = chars[lo];
            chars[lo] = chars[hi];
            chars[hi] = temp;
        }
        return String.valueOf(chars);
    }


    /**
     *
     * @param s
     * @param n 移动到的位置
     * @param m 需要移动的数量 0-m-1
     * @return
     */
    public static String reverseStringAdvance(String s, int n, int m) {
        String left = reverseString(s.substring(0, m - 1));
        String right = reverseString(s.substring(n));
        return reverseString(right + left);
    }
}
