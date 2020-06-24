package com.ch_03_resource_sinchronization.ex_01_visibility;

import java.io.*;

public class ParseFile {

    private File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
        }
        return output;
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        }
        return output;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        }
    }
}