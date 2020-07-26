package com.ch_07_exam.video_cameras;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ProcessNode implements Runnable {

    private Map<String, String> data;
    private ProcessURL purl;

    public ProcessNode(Map<String, String> data, ProcessURL purl) {
        this.data = data;
        this.purl = purl;
    }

    public Map<String, String> readURL(String strURL) {
        URL url;
        try {
            url = new URL(strURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
            int in;
            boolean openDataRead = false;
            while ((in = reader.read()) != -1) {
                char c = (char) in;
                if (c == '{') {
                    openDataRead = true;
                }
                if (openDataRead) {
                    sb.append(c);
                }
                if (c == '}') {
                    openDataRead = false;
                    return purl.getParser().parse(sb.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * METHOD DESCRIPTION:
     *
     * INPUT FORMAT:
     * Map<String, String> = {
     *      "id": 20,
     *      "sourceDataUrl": "http://www.mocky.io/v2/5c51b2e6340000a24a129f5f?mocky-delay=100ms",
     *      "tokenDataUrl": "http://www.mocky.io/v2/5c51b5ed340000554e129f7e"
     * }
     *
     * sourceDataUrl = "{
     *      "urlType": "LIVE",
     *      "videoUrl": "rtsp://127.0.0.1/20"
     * }"
     *
     * tokenDataUrl = "{
     *      "value": "fa4b5b22-249b-11e9-ab14-d663bd873d93",
     *      "ttl": 180
     * }"
     *
     * OUTPUT FORMAT:
     * Map<String, String> = {
     *      "id": 20,
     *      "urlType": "LIVE",
     *      "videoUrl": "rtsp://127.0.0.1/20",
     *      "value": "fa4b5f64-249b-11e9-ab14-d663bd873d93",
     *      "ttl": 180
     * }
     */
    @Override
    public void run() {

        Map<String, String> rsl = new HashMap<>();

        // id
        rsl.put("id", data.get("id"));

        // sourceDataUrl
        Map<String, String> sourceDataUrl = null;
        sourceDataUrl = readURL(data.get("sourceDataUrl"));
        if (sourceDataUrl != null) {
            rsl.put("urlType", sourceDataUrl.get("urlType"));
            rsl.put("videoUrl", sourceDataUrl.get("videoUrl"));
        }

        // tokenDataUrl
        Map<String, String> tokenDataUrl = null;
        tokenDataUrl = readURL(data.get("tokenDataUrl"));
        if (tokenDataUrl != null) {
            rsl.put("value", tokenDataUrl.get("value"));
            rsl.put("ttl", tokenDataUrl.get("ttl"));
        }

        purl.add(rsl);
    }
}
