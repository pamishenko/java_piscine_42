package ex02;

public class MyThread extends Thread {
    private final Short[]   array;
    public static Integer[] arraySum;
    private final int threadIndex;
    private final int from;
    private final int to;

    public MyThread(Short[] array, Integer[] arraySum, int threadIndex, int from, int to) {
        this.array = array;
        this.arraySum = arraySum;
        this.threadIndex = threadIndex;
        this.from = from;
        this.to = to;
        for (int i = 0; i <= threadIndex; i++) {
            arraySum[i] = 0;
        }
    }
synchronized private void addSum(int index, int value) {
        arraySum[index] += value;
}
    @Override
    public void run() {
        int sum = 0;
        for (int i = from; i <= to; i++) {
                sum += array[i];
        }
        System.out.printf("Thread %d: from %d to %d sum is %d\n", threadIndex, from, to, sum);
                addSum(threadIndex, sum);
    }
}
