import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.file.*;

public class DownloadThread extends Thread {

    private int fileNumber;
    private String url;
    private int number;
    private int ready;


    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DownloadThread(int number) {
        this.number = number;
    }

    public void setReady(int ready) {
        this.ready = ready;
    }

    public int getReady() {
        return ready;
    }

    public synchronized void  run() {

        while (true) {
            if (ready == 1) {

                System.out.println("Thread-" + (number + 1) + " start download file number " + (fileNumber + 1));

                String home = System.getProperty("user.home");
                try {
                    BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                    String fileName = url.substring(url.lastIndexOf('/'));

                    File file = new File(home + "/Downloads/" + fileName);
                    file.createNewFile();
                    FileOutputStream out = new FileOutputStream(home + "/Downloads/" + fileName);
                    byte buffer[] = new byte[512];
                    int ret = 0;
                    while ((ret = in.read(buffer, 0, 512)) != -1) {
                        out.write(buffer, 0, 512);
                    }
                    in.close();
                    out.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println("Thread-" + (number + 1) + " finish download file number " + (fileNumber + 1));
                ready = 0;
            }
        }
    }

}