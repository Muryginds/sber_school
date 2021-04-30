<<<<<<< HEAD:01_Scanner/src/main/java/company/Task2.java
package company;
=======
package com.company;
>>>>>>> a88bea94c0eed3768a48f5c7565e92f06ee7ed9c:01_Scanner/src/main/java/com/company/Task2.java

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
  private static final String INPUT_MESSAGE = "Введите данные в формате: <1,2, …, n>";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(INPUT_MESSAGE);
    while (true) {
      String input = scanner.nextLine();
      try {
        List<Integer> list = Arrays.stream(input.split(", ?")).map(Integer::parseInt)
            .sorted().collect(Collectors.toList());
        int min = list.get(0);
        int max = list.get(list.size()-1);
        System.out.println("MIN: " + min + "\nMAX: " + max);
      } catch (Exception ex) {
        System.out.println(INPUT_MESSAGE);
      }
    }
  }
}
