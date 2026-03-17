package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты POST запросов для пользователей")
public class UserPostTests extends BaseTest {

    @Test
    @DisplayName("Создание нового пользователя")
    void testCreateUser() {
        User newUser = TestDataFactory.createTestUser();

        User createdUser = userService.createUser(newUser);

        assertNotNull(createdUser, "Созданный пользователь не должен быть null");

        assertAll("Проверка созданного пользователя",
                () -> assertNotNull(createdUser.getId(), "Должен быть присвоен ID"),
                () -> assertTrue(createdUser.getId() > 0, "ID должен быть положительным"),
                () -> assertEquals(newUser.getName(), createdUser.getName()),
                () -> assertEquals(newUser.getUsername(), createdUser.getUsername()),
                () -> assertEquals(newUser.getEmail(), createdUser.getEmail())
        );

        System.out.println("✓ Пользователь успешно создан:");
        System.out.println("  ID: " + createdUser.getId());
        System.out.println("  Имя: " + createdUser.getName());
        System.out.println("  Username: " + createdUser.getUsername());
    }

    @Test
    @DisplayName("Создание пользователя с минимальными данными")
    void testCreateUserWithMinimalData() {
        User minimalUser = new User();
        minimalUser.setName("Минимальный пользователь");
        minimalUser.setUsername("minimal");

        User createdUser = userService.createUser(minimalUser);

        assertNotNull(createdUser, "Созданный пользователь не должен быть null");

        assertAll("Проверка пользователя с минимальными данными",
                () -> assertNotNull(createdUser.getId()),
                () -> assertEquals(minimalUser.getName(), createdUser.getName()),
                () -> assertEquals(minimalUser.getUsername(), createdUser.getUsername())
        );

        System.out.println("✓ Минимальный пользователь создан с ID: " + createdUser.getId());
    }
}