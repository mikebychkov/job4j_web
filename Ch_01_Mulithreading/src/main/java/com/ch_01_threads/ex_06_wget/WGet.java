package com.ch_01_threads.ex_06_wget;

import com.ch_01_threads.ex_05_blocked_thread_interrupt.ConsoleProgress;

import java.io.*;
import java.net.URL;

public class WGet {

    public static void getFile(String file, int threshold) throws Exception {
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[threshold];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, threshold)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("Params must be: FileNameOrURL DownloadThresholdBytes");
        }
        String file = args[0];
        int threshhold = Integer.parseInt(args[1]);

        Thread progress = new Thread(new ConsoleProgress());
        progress.start();

        getFile(file, threshhold);

        progress.interrupt();

        System.out.println();
        System.out.println("Loading complete.");
    }
}
