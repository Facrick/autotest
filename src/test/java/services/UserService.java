package services;

import clients.ApiClient;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public UserService() {
        this.apiClient = ApiClient.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    public List<User> getAllUsers() {
        APIResponse response = apiClient.get("/users");
        validateResponseStatus(response, 200, "Failed to get users");

        try {
            String jsonString = response.text();
            List<Map<String, Object>> usersMap = objectMapper.readValue(
                    jsonString,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            List<User> users = new ArrayList<>();
            for (Map<String, Object> userMap : usersMap) {
                User user = User.fromMap(userMap);
                if (user != null) {
                    users.add(user);
                }
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    public User getUserById(int id) {
        APIResponse response = apiClient.get("/users/" + id);
        validateResponseStatus(response, 200, "Failed to get user with id: " + id);

        try {
            String jsonString = response.text();

            // Проверяем на пустой ответ (для несуществующих пользователей)
            if (jsonString == null || jsonString.trim().isEmpty() || jsonString.equals("{}")) {
                return null;
            }

            Map<String, Object> userMap = objectMapper.readValue(
                    jsonString,
                    new TypeReference<Map<String, Object>>() {}
            );

            return User.fromMap(userMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response for user " + id, e);
        }
    }

    public List<User> getUsersByUsername(String username) {
        APIResponse response = apiClient.get("/users",
                RequestOptions.create().setQueryParam("username", username));
        validateResponseStatus(response, 200, "Failed to get users by username: " + username);

        try {
            String jsonString = response.text();
            List<Map<String, Object>> usersMap = objectMapper.readValue(
                    jsonString,
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            List<User> users = new ArrayList<>();
            for (Map<String, Object> userMap : usersMap) {
                User user = User.fromMap(userMap);
                if (user != null) {
                    users.add(user);
                }
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    public User createUser(User user) {
        APIResponse response = apiClient.post("/users", user.toMap());
        validateResponseStatus(response, 201, "Failed to create user");

        try {
            String jsonString = response.text();
            Map<String, Object> userMap = objectMapper.readValue(
                    jsonString,
                    new TypeReference<Map<String, Object>>() {}
            );

            return User.fromMap(userMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    public User updateUser(int id, User user) {
        APIResponse response = apiClient.put("/users/" + id, user.toMap());
        validateResponseStatus(response, 200, "Failed to update user with id: " + id);

        try {
            String jsonString = response.text();
            Map<String, Object> userMap = objectMapper.readValue(
                    jsonString,
                    new TypeReference<Map<String, Object>>() {}
            );

            return User.fromMap(userMap);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }

    public boolean deleteUser(int id) {
        APIResponse response = apiClient.delete("/users/" + id);
        return response.status() == 200;
    }

    private void validateResponseStatus(APIResponse response, int expectedStatus, String errorMessage) {
        if (response.status() != expectedStatus) {
            throw new RuntimeException(errorMessage + ". Status: " + response.status());
        }
    }
}