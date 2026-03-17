package tests;

import clients.ApiClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import services.UserService;

public abstract class BaseTest {
    protected static ApiClient apiClient;
    protected UserService userService;

    @BeforeAll
    static void setUpClass() {
        apiClient = ApiClient.getInstance();
    }

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @AfterAll
    static void tearDownClass() {
        if (apiClient != null) {
            apiClient.close();
        }
    }
}