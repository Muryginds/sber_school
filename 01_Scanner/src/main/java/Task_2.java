import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task_2 {
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
