package com.moonfeather.maimap_server;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Service
public class BaiduMapService {
    private final String AK = "XHKv0XndEu67LqcJrUHMsxZvsBlq8JAi";
    private StringBuffer request;

    public void requestGetAK(String strUrl, Map<String, String> param) throws Exception {
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return;
        }
        StringBuffer queryString = new StringBuffer();
        queryString.append(strUrl);
        for (Map.Entry<?, ?> pair : param.entrySet()) {
            queryString.append(pair.getKey() + "=");
            //    第一种方式使用的 jdk 自带的转码方式  第二种方式使用的 spring 的转码方法 两种均可
            //    queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8").replace("+", "%20") + "&");
            queryString.append(UriUtils.encode((String) pair.getValue(), "UTF-8") + "&");
        }

        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        java.net.URL url = new URL(queryString.toString());
        System.out.println(queryString.toString());
        URLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();

        InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        isr.close();
        System.out.println("AK: " + buffer.toString());
        request = buffer;
    }

    public String getRGCRequest(double lat, double lng) throws Exception {
        Map<String, String> param = Map.of(
                "ak", AK,
                "location", lat + "," + lng,
                "output", "json",
                "coordtype", "wgs84ll",
                "extensions_poi", "0"
        );
        requestGetAK("http://api.map.baidu.com/reverse_geocoding/v3/?", param);
        return request.toString();
    }

}
