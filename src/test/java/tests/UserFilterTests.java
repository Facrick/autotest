package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты фильтрации пользователей")
public class UserFilterTests extends BaseTest {

    @Test
    @DisplayName("Фильтрация пользователей по username")
    void testFilterByUsername() {
        String username = "Bret";

        List<User> users = userService.getUsersByUsername(username);

        assertAll("Проверка фильтрации по username",
                () -> assertNotNull(users, "Список не должен быть null"),
                () -> assertFalse(users.isEmpty(), "Должны быть найдены пользователи"),
                () -> assertTrue(users.stream().allMatch(u -> username.equals(u.getUsername())),
                        "Все найденные пользователи должны иметь username = " + username)
        );

        System.out.println("✓ Найдено пользователей с username '" + username + "': " + users.size());
        if (!users.isEmpty()) {
            System.out.println("  Первый найденный: " + users.get(0).getName());
        }
    }

    @Test
    @DisplayName("Фильтрация по несуществующему username")
    void testFilterByNonExistentUsername() {
        String nonExistentUsername = "nonexistentuser123";

        List<User> users = userService.getUsersByUsername(nonExistentUsername);

        assertNotNull(users, "Список не должен быть null");
        assertTrue(users.isEmpty(), "Для несуществующего username должен вернуться пустой список");

        System.out.println("✓ Для несуществующего username '" + nonExistentUsername +
                "' получен пустой список");
    }
}