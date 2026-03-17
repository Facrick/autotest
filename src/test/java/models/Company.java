package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {}

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public static Company fromMap(Map<String, Object> map) {
        if (map == null) return null;
        Company company = new Company();
        company.setName((String) map.get("name"));
        company.setCatchPhrase((String) map.get("catchPhrase"));
        company.setBs((String) map.get("bs"));
        return company;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCatchPhrase() { return catchPhrase; }
    public void setCatchPhrase(String catchPhrase) { this.catchPhrase = catchPhrase; }

    public String getBs() { return bs; }
    public void setBs(String bs) { this.bs = bs; }
}