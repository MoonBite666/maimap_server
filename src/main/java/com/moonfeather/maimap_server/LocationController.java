package com.moonfeather.maimap_server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // replace with the origin of your frontend
public class LocationController {

    private final TencentMapService tencentMapService;

    public LocationController(TencentMapService tencentMapService) {
        this.tencentMapService = tencentMapService;
    }

    @GetMapping("/rgc")
    public String getLocations(@RequestParam double lat, @RequestParam double lng) throws Exception {
        return tencentMapService.getRGCRequest(
                tencentMapService.coordinateTranslate(lat, lng)
        );
    }
    @GetMapping("/translate")
    public double[] translate(@RequestParam double lat, @RequestParam double lng) throws Exception {
        return tencentMapService.coordinateTranslate(lat, lng);
    }
}