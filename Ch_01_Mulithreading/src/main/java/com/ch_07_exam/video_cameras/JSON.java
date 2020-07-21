package com.ch_07_exam.video_cameras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSON implements Parser {

    @Override
    public Map<String, String> parse(String s) {
        s = s.replace("\n", "").replace("\"", "").replace(" ", "");
        Map<String, String> rsl = new HashMap<>();
        String[] sData = s.split(",");
        for (String is : sData) {
            int pos = is.indexOf(":");
            String is1 = is.substring(0, pos);
            String is2 = is.substring(pos + 1, is.length());
            rsl.put(is1, is2);
        }
        return rsl;
    }

    @Override
    public String toFormat(Map<String, String> m) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String strSeparator = "";
        for (Map.Entry<String, String> e : m.entrySet()) {
            if (!strSeparator.isEmpty()) {
                sb.append(strSeparator);
            }
            String s = String.format("\"%s\":\"%s\"", e.getKey(), e.getValue());
            sb.append(s);
            strSeparator = ",";
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String listToFormat(List<Map<String, String>> l) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String strSeparator = "";
        for (Map<String, String> m : l) {
            if (!strSeparator.isEmpty()) {
                sb.append(strSeparator);
            }
            sb.append(toFormat(m));
            strSeparator = ",";
        }
        sb.append("]");
        return sb.toString();
    }
}
