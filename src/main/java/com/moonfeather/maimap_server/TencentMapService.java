package com.moonfeather.maimap_server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TencentMapService extends MapService{
    String AK = "4BQBZ-6DJWA-MJDKJ-CEHME-I4AL7-IDBK7";

    public double[] coordinateTranslate(double lat, double lng) throws Exception {
        Map<String, String> TransParam = Map.of(
                "key", AK,
                "locations", lat + "," + lng,
                "type", "1",
                "output", "json"
        );
        requestGetAK("https://apis.map.qq.com/ws/coord/v1/translate?", TransParam);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(request.toString());
        lat = node.get("locations").get(0).get("lat").asDouble();
        lng = node.get("locations").get(0).get("lng").asDouble();

        return new double[]{lat, lng};
    }

    public String getRGCRequest(double[] coordinate) throws Exception {

        Map<String, String> param = Map.of(
                "key", AK,
                "location", coordinate[0] + "," + coordinate[1],
                "output", "json"
        );
        requestGetAK("https://apis.map.qq.com/ws/geocoder/v1/?", param);
        return request.toString();
    }
}
