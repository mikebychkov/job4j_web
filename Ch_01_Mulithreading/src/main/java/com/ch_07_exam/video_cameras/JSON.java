package com.ch_07_exam.video_cameras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONArray;

public class JSON implements Parser {
    @Override
    public Map<String, String> parse(String s) {
        JSONObject jo = new JSONObject(s);
        Map<String, String> rsl = new HashMap<>();
        for (Map.Entry<String, Object> m : jo.toMap().entrySet()) {
            rsl.put(m.getKey(), String.valueOf(m.getValue()));
        }
        return rsl;
    }

    @Override
    public String toFormat(Map<String, String> m) {
        JSONObject jo = new JSONObject(m);
        return jo.toString();
    }

    @Override
    public String listToFormat(List<Map<String, String>> l) {
        JSONArray ja = new JSONArray(l);
        return ja.toString();
    }
}
