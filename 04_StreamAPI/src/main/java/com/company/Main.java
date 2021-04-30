package com.company;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    Set<Integer> set = Stream.generate(() -> (int)(Math.random() * 100) * ((Math.random() >= 0.5)? -1 : 1))
        .filter(i -> i >= 0 && i % 2 == 1)
        .map(i -> i * 2)
        .peek(System.out::println)
        .limit(15)
        .collect(Collectors.toSet());
    int result = set.stream().reduce(Integer::sum).orElse(0);
    System.out.println(result);
  }
}
