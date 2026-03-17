package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address() {}

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    @SuppressWarnings("unchecked")
    public static Address fromMap(Map<String, Object> map) {
        if (map == null) return null;

        Address address = new Address();
        address.setStreet((String) map.get("street"));
        address.setSuite((String) map.get("suite"));
        address.setCity((String) map.get("city"));
        address.setZipcode((String) map.get("zipcode"));

        Map<String, Object> geoMap = (Map<String, Object>) map.get("geo");
        address.setGeo(Geo.fromMap(geoMap));

        return address;
    }

    // Геттеры и сеттеры
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getSuite() { return suite; }
    public void setSuite(String suite) { this.suite = suite; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public Geo getGeo() { return geo; }
    public void setGeo(Geo geo) { this.geo = geo; }
}