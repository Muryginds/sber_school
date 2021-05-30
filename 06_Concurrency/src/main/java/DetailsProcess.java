public class DetailsProcess extends Thread {

  private int process;
  public DetailsProcess(int process){
    this.process = process;
  }

  @Override
  public void run() {
    System.out.println(process);
  }
}
