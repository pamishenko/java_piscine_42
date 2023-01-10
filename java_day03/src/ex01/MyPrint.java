package ex01;

public class MyPrint {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private boolean flag = true;
    synchronized void print() throws InterruptedException {
        String  color = ANSI_YELLOW;
        if (Thread.currentThread().getName().equals("Egg"))  {
            while (!flag) {
                wait();
            }
        }
        else if (Thread.currentThread().getName().equals("Hen")) {
            while (flag) {
                wait();
            }
        }
        flag = !flag;
        if (!Thread.currentThread().getName().equals("Egg"))
            color = ANSI_RESET;
        System.out.println(color + Thread.currentThread().getName() + ANSI_RESET);
        notify();
    }
}
