<<<<<<< HEAD:01_Scanner/src/main/java/company/Task1.java
package company;

import company.utility.ArithmeticalActions;
=======
package com.company;

import com.company.utility.ArithmeticalActions;
>>>>>>> a88bea94c0eed3768a48f5c7565e92f06ee7ed9c:01_Scanner/src/main/java/com/company/Task1.java
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

  private static final String INPUT_MESSAGE = "Введите данные в формате: <int число1> <знак [+, -, *, /]> <int число 2>";
  public static final String MATCHING_TEXT = "([0-9]+)\\s*([+\\-*\\/])\\s*([0-9]+)";
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(INPUT_MESSAGE);
    Pattern pattern = Pattern.compile(MATCHING_TEXT);
    while (true) {
      Matcher matcher = pattern.matcher(scanner.nextLine());
      if (matcher.matches()) {
        ArithmeticalActions action = findAction(matcher.group(2));
        printResult(matcher.group(1), matcher.group(3), action);
      }
    }
  }

  private static void printResult(String input1, String input2, ArithmeticalActions action) {
    int result = 0;
    int num1 = Integer.parseInt(input1);
    int num2 = Integer.parseInt(input2);
    switch (action) {
      case ADDITION:
        result = num1 + num2;
        break;
      case SUBTRACTION:
        result = num1 - num2;
        break;
      case DIVISION:
        result = num1 / num2;
        break;
      default:
        result = num1 * num2;
    }
    System.out.println(result);
  }

  private static ArithmeticalActions findAction(String input) {
    switch (input) {
      case "+":
        return ArithmeticalActions.ADDITION;
      case "-":
        return ArithmeticalActions.SUBTRACTION;
      case "/":
        return ArithmeticalActions.DIVISION;
      default:
        return ArithmeticalActions.MULTIPLICATION;
    }
  }

}
