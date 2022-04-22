public class SumThread extends Thread {
    private int[] array;
    private int start;
    private int finish;
    private int sum;
    private int number;
    private int done;

    public SumThread(int number, int [] array, int start, int finish) {
        this.array = array;
        this.start = start;
        this.finish = finish;
        this.number = number;
    }

    public void run() {

            for (int i = start; i < finish; i++) {
                sum += array[i];
            }
            System.out.println("Thread " + (number + 1 ) + ": from " + start + " to " + (finish - 1) + " sum is " + sum);
            Program.threadsSum += sum;
    }

}