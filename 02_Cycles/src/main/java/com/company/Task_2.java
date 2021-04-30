<<<<<<< HEAD:02_Cycles/src/main/java/company/Task_2.java
package company;
=======
package com.company;
>>>>>>> a88bea94c0eed3768a48f5c7565e92f06ee7ed9c:02_Cycles/src/main/java/com/company/Task_2.java

import java.util.Scanner;

public class Task_2 {
  private static final Scanner scanner = new Scanner(System.in);
  private static final char MARKER = '*';
  private static final char EMPTY_SPACE = ' ';

  public static void main(String[] args) {
    int height = Integer.parseInt(scanner.nextLine());
    int width = Integer.parseInt(scanner.nextLine());
    String word = scanner.nextLine();
    int wordLength = word.length();

    if (width >= wordLength + 2) {
      int middleHeight = height % 2 == 1? height / 2 : height / 2 - 1;
      String lineSeparator = System.lineSeparator();
      StringBuilder stringBuilder = new StringBuilder();
      StringBuilder markerString = new StringBuilder()
          .append(buildString(MARKER, width));

      for (int i = 0; i < height; i++) {
        if (i == 0 || i == height - 1) {
          stringBuilder.append(markerString).append(lineSeparator);
        } else if (i == middleHeight) {
          int h = (width - wordLength - 2)/2;
          stringBuilder.append(MARKER)
              .append(buildString(EMPTY_SPACE, h))
              .append(word)
              .append(buildString(EMPTY_SPACE, width - wordLength - h - 2))
              .append(MARKER).append(lineSeparator);
        } else {
          stringBuilder.append(MARKER)
              .append(buildString(EMPTY_SPACE, width - 2))
              .append(MARKER).append(lineSeparator);
        }
      }
      System.out.println(stringBuilder.toString());
    } else {
      System.out.println("Ошибка");
    }
  }

  private static StringBuilder buildString(char symbol, int num) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < num; i++) {
      sb.append(symbol);
    }
    return sb;
  }
}
