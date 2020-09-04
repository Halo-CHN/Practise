package io.github.halochn.leetcode.sort;

public class S242 {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] x = new int[26], y = new int[26];
        for (int i = 0; i < s.length(); i++) {
            x[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            y[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < x.length; i++) {
            if (x[i] != y[i]) return false;
        }
        return true;
    }
}