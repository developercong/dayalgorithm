package com.congzibank.dayalgrithm.stringalgorithm;


import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1.2 字符串包含
 * 给定两个 有字母构成的字符串，为了简单起见可以设置为全部为小写或者大写
 * 我们这里设置为大写 字符串B的长度比A的小，如何快速判断字符串是否包含字符串B
 * ABCD 包含 BAD  --不需要保证顺序
 * Created by cong_wang on 2018/9/17.
 */

public class ContainString {

    /**
     * 最原始的想法 从头开始比较a，b如果 某个字符不匹配就返回false
     * @param a
     * @param b
     * @return 这个效率太低
     */
    public static boolean containsFisrt(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; (j <a.length()) && !a.substring(j,j+1).equals(b.substring(i,i+1)); j++) {
                if (j >= a.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 可以通过对字符串进行排序，然后比较时就不需要从头扫描
     * @param a
     * @param b
     * @return
     */
    public static boolean containsSecond(String a, String b) {
        char[] achars = a.toCharArray();
        char[] bchars = b.toCharArray();
        Arrays.sort(achars);
        Arrays.sort(bchars);

        for (int i = 0 ,j = 0; j < bchars.length;) {
            while ((i < a.length()) && achars[i] < bchars[j]) {
                ++i;
            }
            if (i >= a.length() || achars[i] > bchars[j]) {
                return false;
            }
            j++;
        }
        return true;
    }

    /**
     * 其他的 我们可以将字符串a 中的字符全都放入一个hashset中，然后遍历比较b的
     * 每个字符是否能在这个hashset中找到
     */

    public static boolean containsThird(String a, String b) {
        int hash = 0;
        for (int i = 0; i < a.length(); i++) {
            hash |= (1 << (a.charAt(i) - 'A')); //将存在的字符映射成位
        }

        for (int i = 0; i < b.length(); i++) {
            //只要存在一个b中字符 但是a中不存在就返回false
            if ((hash & (1 << (b.charAt(i) - 'A'))) == 0) {
                return false;
            }
        }
        return true;
    }
}
