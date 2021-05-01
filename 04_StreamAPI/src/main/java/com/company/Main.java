package com.company;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    System.out.println("task 0:");
    Set<Integer> set = Stream.generate(() -> (int)(Math.random() * 100) * ((Math.random() >= 0.5)? -1 : 1))
        .filter(i -> i % 2 == 1)
        .map(i -> i * 2)
        //.peek(System.out::println)
        .limit(15)
        .collect(Collectors.toSet());
    int result = set.stream().reduce(Integer::sum).orElse(0);
    System.out.println(result);


    System.out.println("task 1:");
    List<String> listNames1 = List.of("Боб", "Патрик");
    List<String> listNames2 = List.of("Сквиртл");
    List<String> listNames3 = List.of("Боб", "Сэнди");
    List<String> listNames4 = List.of("Красти");

    Stream.of(listNames1, listNames2, listNames3, listNames4)
      .flatMap(Collection::stream)
        .collect(Collectors.toCollection(TreeSet::new))
        .forEach(System.out::println);

    System.out.println("task 2:");
    Map<String, Double> map = Map.of("A", 1.1,"B", 2.2, "C", 3.3);
    Double res = map.values().stream().collect(Collectors.averagingDouble(v -> v));
    System.out.println(res);

    System.out.println("task 3:");
    List<Long> list1 = List.of(1L, 1230L, 12303L, 43344563L, 234234L);
    List<Long> list2 = List.of(1230L, 12303L, 12454564L);
    list1.stream().filter(list2::contains).forEach(System.out::println);
  }
}
