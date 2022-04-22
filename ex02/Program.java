public class Program {

    public static int threadsSum = 0;

    public static void main(String[] args) {
        if (args.length < 2 || args[0].length() < "--arraySize=".length()
                || !args[0].substring(0, "--arraySize=".length()).equals("--arraySize=")
                || args[1].length() < "--threadsCount=".length()
                || !args[1].substring(0, "--threadsCount=".length()).equals("--threadsCount=")) {
            System.out.println("Error: Wrong arguments!");
            System.exit(-1);
        }
        Integer arraySize = Integer.parseInt(args[0].substring("--arraySize=".length(), args[0].length()));
        Integer threadsCount = Integer.parseInt(args[1].substring("--threadsCount=".length(), args[1].length()));

        int [] intArray = new int[arraySize];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int)(Math.random() * 2000) % 2000 - 1000;
        }
        int standardSum = 0;
        for (int i = 0; i < intArray.length; i++) {
            standardSum += intArray[i];
        }
        System.out.println("Sum: " + standardSum);

        int intsPerThread = arraySize / threadsCount;
        int start = 0;
        int finish = 0;
        SumThread[] sumThreadsArray = new SumThread[threadsCount];

        for (int i = 0; i < threadsCount - 1; i++) {
            finish = start + intsPerThread;
            sumThreadsArray[i] = new SumThread(i, intArray, start, finish);
            sumThreadsArray[i].start();
            start = finish;
        }
        finish = arraySize;
        sumThreadsArray[threadsCount - 1] = new SumThread(threadsCount - 1, intArray, start, finish);
        sumThreadsArray[threadsCount - 1].start();

        for (int i = 0; i < threadsCount; i++) {
            try {
                sumThreadsArray[i].join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("Sum by threads: " + threadsSum);
    }
}