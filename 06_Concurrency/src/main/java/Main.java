public class Main {

  public static void main(String[] args) {

/*    MessagePrinter message1 = new MessagePrinter("OLGA");
    MessagePrinter message2 = new MessagePrinter("NIKOLAY");
    Thread thread1 = new Thread(message1);
    thread1.start();
    Thread thread2 = new Thread(message2);
    thread2.start();*/

    for (int i = 0; i < 20; i++) {
      try {
        Thread thread1 = new DetailsProcess(1);
        thread1.start();
        Thread thread2 = new DetailsProcess(2);
        thread2.start();
        Thread thread3 = new DetailsProcess(3);

        thread1.join();
        thread2.join();

        thread3.start();
        thread3.join();

        Thread thread4 = new DetailsProcess(4);
        thread4.start();
        Thread thread5 = new DetailsProcess(5);
        thread5.start();
        Thread thread6 = new DetailsProcess(6);

        thread4.join();
        thread5.join();

        thread6.start();
        thread6.join();
        System.out.println("---------------------");

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}