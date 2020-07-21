package com.ch_07_exam.video_cameras;

public class Main {

    public static void main(String[] args) throws Exception {
        ProcessURL purl = new ProcessURL(new JSON());
        purl.start("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
        System.out.println(purl.stop());
    }
}
