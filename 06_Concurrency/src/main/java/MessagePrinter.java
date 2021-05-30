public class MessagePrinter implements Runnable{

  String name;
  static volatile int counter;

  public MessagePrinter(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    while (true){
      printName();
    }
  }

  private synchronized void printName() {
    System.out.println(this.getClass().getName() + ": № " + counter + " " + name);
    counter++;
  }
}