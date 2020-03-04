package com.adelphipartners.hrapi.model;

import java.util.HashMap;
import java.util.Map;

public enum OfficeLocation {

    NY("New York, America"), NBO("Nairobi, Kenya"), LON("London, United " +
            "Kingdom"), IST("Istanbul, Turkey");

    private static Map<String, OfficeLocation> BY_LOCATION = new HashMap<>();

    static {
        for (OfficeLocation location : values()) {
            BY_LOCATION.put(location.fullLocation, location);
        }
    }

    public final String fullLocation;

    private OfficeLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public static OfficeLocation valueOfFullLocation(String fullLocation) {
        return BY_LOCATION.get(fullLocation);
    }


    @Override
    public String toString() {
        return this.fullLocation;
    }
}
