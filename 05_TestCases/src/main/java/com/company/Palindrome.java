package com.company;

public class Palindrome {
  public static boolean isPalindrome(String s) {
    if (s == null || s.isBlank()) {
      return false;
    } else {
      String input = s.toLowerCase().trim();
      for (int i = 0; i <= input.length()/2; i++) {
        if (!Character.isAlphabetic(input.charAt(i))) {
            return false;
        }
      }
      String revert = new StringBuilder(input).reverse().toString();
      return input.equals(revert);
    }
  }
}
