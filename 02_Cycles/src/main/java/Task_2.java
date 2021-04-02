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

    if (wordLength + 2 <= width) {
      int middleHeight = height % 2 == 1? height / 2 : height / 2 - 1;
      StringBuilder stringBuilder = new StringBuilder();
      StringBuilder markerString = new StringBuilder()
          .append(String.valueOf(MARKER).repeat(width));

      for (int i = 0; i < height; i++) {
        if (i == 0 || i == height - 1) {
          stringBuilder.append(markerString).append("\n");
        } else if (i == middleHeight) {
          int h = (width - wordLength - 2)/2;
          stringBuilder.append(MARKER)
              .append(String.valueOf(EMPTY_SPACE).repeat(h))
              .append(word)
              .append(String.valueOf(EMPTY_SPACE).repeat(width - wordLength - h - 2))
              .append(MARKER).append("\n");
        } else {
          stringBuilder.append(MARKER)
              .append(String.valueOf(EMPTY_SPACE).repeat(width - 2))
              .append(MARKER).append("\n");
        }
      }
      System.out.println(stringBuilder.toString());
    } else {
      System.out.println("Ошибка");
    }
  }
}
