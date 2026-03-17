package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geo {
    private String lat;
    private String lng;

    public Geo() {}

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public static Geo fromMap(Map<String, Object> map) {
        if (map == null) return null;
        Geo geo = new Geo();
        geo.setLat((String) map.get("lat"));
        geo.setLng((String) map.get("lng"));
        return geo;
    }

    // Геттеры и сеттеры
    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat; }

    public String getLng() { return lng; }
    public void setLng(String lng) { this.lng = lng; }
}