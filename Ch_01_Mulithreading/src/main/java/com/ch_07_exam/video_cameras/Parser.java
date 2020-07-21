package com.ch_07_exam.video_cameras;

import java.util.List;
import java.util.Map;

public interface Parser {

    Map<String, String> parse(String s);

    String toFormat(Map<String, String> m);

    String listToFormat(List<Map<String, String>> l);
}
