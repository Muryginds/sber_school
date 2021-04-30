package com.company;

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
