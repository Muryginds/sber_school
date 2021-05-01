package com.company;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PalindromeTest {
  @Test
  @DisplayName("Проверка на null")
  public void testNullString() throws Exception {
    boolean result = Palindrome.isPalindrome(null);
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Проверка на пустую строку")
  public void testEmptyString() throws Exception {
    boolean result = Palindrome.isPalindrome("");
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Проверка на верхний регистр")
  public void testUpperCase() throws Exception {
    boolean result = Palindrome.isPalindrome("ТОпот");
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("Проверка на вхождение цифр")
  public void testNumbers() throws Exception {
    boolean result = Palindrome.isPalindrome("1234321");
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Проверка на строку из пробелов")
  public void testAllSpaces() throws Exception {
    boolean result = Palindrome.isPalindrome("     ");
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Проверка на вхождение пробелов")
  public void testHasSpaces() throws Exception {
    boolean result = Palindrome.isPalindrome("  поп");
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("Проверка на переключение раскладки")
  public void testHasEnglishLetters() throws Exception {
    boolean result = Palindrome.isPalindrome("топoт");
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Слово - палиндром")
  public void testPalindrome() throws Exception {
    boolean result = Palindrome.isPalindrome("поп");
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("Слово - не палиндром")
  public void testNotPalindrome() throws Exception {
    boolean result = Palindrome.isPalindrome("ракетка");
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("Слово из 1 буквы")
  public void testOneLetter() throws Exception {
    boolean result = Palindrome.isPalindrome("а");
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("Слово из одинаковых букв")
  public void testSameLetters() throws Exception {
    boolean result = Palindrome.isPalindrome("ааааа");
    assertThat(result).isTrue();
  }
}
