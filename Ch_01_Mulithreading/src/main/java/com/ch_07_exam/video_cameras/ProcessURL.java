package com.ch_07_exam.video_cameras;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessURL {

    private ExecutorService pool;
    private ConcurrentLinkedQueue<Map<String, String>> data;
    private Parser parser;
    private boolean initialized;

    public ProcessURL(Parser parser) {
        this.parser = parser;
    }

    public void add(Map<String, String> m) {
        data.add(m);
    }

    private void init() {
        data = new ConcurrentLinkedQueue<>();
        pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        initialized = true;
    }

    public void start(String strURL) throws MalformedURLException {
        if (initialized) {
            System.out.println("Already started!");
            return;
        }
        init();
        URL url = new URL(strURL);
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
            int in;
            boolean openDataRead = false;
            while ((in = reader.read()) != -1) {
                char c = (char) in;
                if (c == '[' || c == ']') {
                    continue;
                }
                if (c == '{') {
                    openDataRead = true;
                }
                if (openDataRead) {
                    sb.append(c);
                }
                if (c == '}') {
                    openDataRead = false;

                    Map<String, String> m = parser.parse(sb.toString());

                    pool.submit(new ProcessNode(m, this));

                    sb.delete(0, sb.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String stop() {
        if (!initialized) {
            System.out.println("Need to start before!");
            return null;
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        initialized = false;
        return parser.listToFormat(new LinkedList<>(data));
    }

    public Parser getParser() {
        return parser;
    }
}
