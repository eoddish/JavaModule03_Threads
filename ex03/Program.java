import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.file.*;

public class Program {

    public static int finish = 0;

    public static void main(String[] args) {
        if (args.length < 1
                || args[0].length() < "--threadsCount=".length()
                || !args[0].substring(0, "--threadsCount=".length()).equals("--threadsCount=")) {
            System.out.println("Error: Wrong arguments!");
            System.exit(-1);
        }
        Integer threadsCount = Integer.parseInt(args[0].substring("--threadsCount=".length(), args[0].length()));

        String str;
        List<String> list= new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("files_urls.txt"));
            while ((str = reader.readLine()) != null) {
                list.add(str.split(" ")[1]);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        DownloadThread [] downLoadThreads = new DownloadThread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            downLoadThreads[i] = new DownloadThread(i);
            downLoadThreads[i].start();
        }

        int count = 0;
        int complete[] = new int[list.size()];

        while (count < list.size()) {
            for (int i = 0; i < list.size(); i++) {
                if (complete[i] == 1)
                    continue;
                for (int j = 0; j < threadsCount; j++) {
                    if (downLoadThreads[j].getReady() == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        downLoadThreads[j].setUrl(list.get(i));
                        downLoadThreads[j].setFileNumber(i);
                        downLoadThreads[j].setReady(1);
                        synchronized (downLoadThreads[j]) {
                            downLoadThreads[j].notify();
                        }
                        count++;
                        complete[i] += 1;
                        break;
                    }
                }
            }
        }
    }
}