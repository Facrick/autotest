package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты GET запросов для пользователей")
public class UserGetTests extends BaseTest {

    @Test
    @DisplayName("Получение всех пользователей")
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();

        assertAll("Проверка списка пользователей",
                () -> assertNotNull(users),
                () -> assertFalse(users.isEmpty()),
                () -> assertEquals(10, users.size()),
                () -> assertNotNull(users.get(0).getId()),
                () -> assertNotNull(users.get(0).getName())
        );

        System.out.println("✓ Получено пользователей: " + users.size());
        System.out.println("✓ Первый пользователь: " + users.get(0).getName());
    }

    @Test
    @DisplayName("Получение пользователя по ID")
    void testGetUserById() {
        User expectedUser = TestDataFactory.createUserWithAllFields();
        User actualUser = userService.getUserById(1);

        assertNotNull(actualUser, "Пользователь не должен быть null");

        assertAll("Проверка пользователя с ID=1",
                () -> assertEquals(1, actualUser.getId()),
                () -> assertEquals(expectedUser.getName(), actualUser.getName()),
                () -> assertEquals(expectedUser.getUsername(), actualUser.getUsername()),
                () -> assertEquals(expectedUser.getEmail(), actualUser.getEmail()),
                () -> assertEquals(expectedUser.getPhone(), actualUser.getPhone()),
                () -> assertEquals(expectedUser.getWebsite(), actualUser.getWebsite())
        );

        assertAll("Проверка вложенных объектов",
                () -> assertNotNull(actualUser.getAddress()),
                () -> assertEquals(expectedUser.getAddress().getCity(), actualUser.getAddress().getCity()),
                () -> assertNotNull(actualUser.getCompany()),
                () -> assertEquals(expectedUser.getCompany().getName(), actualUser.getCompany().getName())
        );

        System.out.println("✓ Пользователь с ID 1 успешно получен: " + actualUser.getName());
    }

    @Test
    @DisplayName("Получение несуществующего пользователя")
    void testGetNonExistentUser() {
        assertThrows(RuntimeException.class, () -> userService.getUserById(999), "Должно выбрасываться исключение при 404");

        System.out.println("✓ Несуществующий пользователь обработан корректно (исключение)");
    }
}