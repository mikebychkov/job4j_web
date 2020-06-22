package com.ch_01_threads.ex_06_wget;

import com.ch_01_threads.ex_05_blocked_thread_interrupt.ConsoleProgress;

import java.io.*;
import java.net.URL;

public class WGet {

    private boolean download(String file, int threshold) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[threshold];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, threshold)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void start(String file, int threshhold) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();

        boolean rsl = download(file, threshhold);

        progress.interrupt();

        System.out.println();
        if (rsl) {
            System.out.println("Loading complete.");
        } else {
            System.out.println("Filed to get file.");
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Params must be: FileNameOrURL DownloadThresholdBytes");
        }
        String file = args[0];
        int threshhold = Integer.parseInt(args[1]);
        new WGet().start(file, threshhold);
    }
}
