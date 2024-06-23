package com.moonfeather.maimap_server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // replace with the origin of your frontend
public class LocationController {

    private final BaiduMapService baiduMapService;

    public LocationController(BaiduMapService baiduMapService) {
        this.baiduMapService = baiduMapService;
    }

    @GetMapping("/rgc")
    public String getLocations(@RequestParam double lat, @RequestParam double lng) throws Exception {
        return baiduMapService.getRGCRequest(lat, lng);
    }
}