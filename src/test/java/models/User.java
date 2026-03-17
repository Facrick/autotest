package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User() {}

    public User(Integer id, String name, String username, String email,
                Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    @SuppressWarnings("unchecked")
    public static User fromMap(Map<String, Object> map) {
        if (map == null || map.isEmpty()) return null;

        User user = new User();
        user.setId((Integer) map.get("id"));
        user.setName((String) map.get("name"));
        user.setUsername((String) map.get("username"));
        user.setEmail((String) map.get("email"));
        user.setPhone((String) map.get("phone"));
        user.setWebsite((String) map.get("website"));

        Map<String, Object> addressMap = (Map<String, Object>) map.get("address");
        user.setAddress(Address.fromMap(addressMap));

        Map<String, Object> companyMap = (Map<String, Object>) map.get("company");
        user.setCompany(Company.fromMap(companyMap));

        return user;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (id != null) map.put("id", id);
        if (name != null) map.put("name", name);
        if (username != null) map.put("username", username);
        if (email != null) map.put("email", email);
        if (phone != null) map.put("phone", phone);
        if (website != null) map.put("website", website);
        return map;
    }

    // Геттеры и сеттеры
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}