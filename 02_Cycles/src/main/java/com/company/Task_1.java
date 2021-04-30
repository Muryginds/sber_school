<<<<<<< HEAD:02_Cycles/src/main/java/company/Task_1.java
package company;
=======
package com.company;
>>>>>>> a88bea94c0eed3768a48f5c7565e92f06ee7ed9c:02_Cycles/src/main/java/com/company/Task_1.java

import java.util.Random;
import java.util.Scanner;

public class Task_1 {
  private static final Scanner scanner = new Scanner(System.in);
  private static final int secret = new Random().nextInt(10);

  public static void main(String[] args) {
    String input = scanner.nextLine();
    int inputNumber = Integer.parseInt(input);

    while(inputNumber != secret) {
      if (inputNumber < secret) {
        System.out.println("Input bigger number");
      } else {
        System.out.println("Input lesser number");
      }
      input = scanner.nextLine();
      inputNumber = Integer.parseInt(input);
    }
    System.out.println("You guessed!");
  }
}
