package com.moonfeather.maimap_server;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> searchLocations(String query) {
        return locationRepository.findTop5ByName(query);
    }
}