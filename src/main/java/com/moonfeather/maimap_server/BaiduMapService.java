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
public class BaiduMapService extends MapService {
    String AK = "XHKv0XndEu67LqcJrUHMsxZvsBlq8JAi";

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
