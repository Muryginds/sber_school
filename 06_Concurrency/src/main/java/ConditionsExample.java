public class ConditionsExample {
  public static void main(String[] args) {

    Thread thread = new Thread(){
      @Override
      public void run() {
        System.out.println("run state:"+ getState());
      }
    };

    System.out.println(thread.getState());
    try {
    thread.start();

    thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(thread.getState());

  }
}
