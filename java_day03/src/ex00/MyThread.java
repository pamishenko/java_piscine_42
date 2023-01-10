package ex00;

public class MyThread extends Thread{
    private final String    createItem;
    private final int       count;

    public MyThread(String createItem, int count) {
        this.createItem = createItem;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.count; i++) {
            System.out.println(this.createItem);
        }
    }
}
