package tests;

import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.TestDataFactory;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты PUT запросов для пользователей")
public class UserPutTests extends BaseTest {

    @Test
    @DisplayName("Полное обновление пользователя")
    void testUpdateUser() {
        int userId = 1;
        User updatedData = TestDataFactory.createUserForUpdate();
        updatedData.setId(userId);

        User updatedUser = userService.updateUser(userId, updatedData);

        assertNotNull(updatedUser, "Обновленный пользователь не должен быть null");

        assertAll("Проверка обновленного пользователя",
                () -> assertEquals(userId, updatedUser.getId()),
                () -> assertEquals(updatedData.getName(), updatedUser.getName()),
                () -> assertEquals(updatedData.getUsername(), updatedUser.getUsername()),
                () -> assertEquals(updatedData.getEmail(), updatedUser.getEmail()),
                () -> assertEquals(updatedData.getPhone(), updatedUser.getPhone()),
                () -> assertEquals(updatedData.getWebsite(), updatedUser.getWebsite())
        );

        System.out.println("✓ Пользователь с ID " + userId + " успешно обновлен:");
        System.out.println("  Новое имя: " + updatedUser.getName());
    }

    @Test
    @DisplayName("Частичное обновление пользователя")
    void testPartialUpdateUser() {
        int userId = 1;
        User partialUpdate = new User();
        partialUpdate.setName("Частично Обновленное Имя");
        partialUpdate.setEmail("partial@update.com");

        User updatedUser = userService.updateUser(userId, partialUpdate);

        assertNotNull(updatedUser, "Обновленный пользователь не должен быть null");

        assertAll("Проверка частичного обновления",
                () -> assertEquals(userId, updatedUser.getId()),
                () -> assertEquals(partialUpdate.getName(), updatedUser.getName()),
                () -> assertEquals(partialUpdate.getEmail(), updatedUser.getEmail())
        );

        System.out.println("✓ Пользователь частично обновлен:");
        System.out.println("  Новое имя: " + updatedUser.getName());
        System.out.println("  Новый email: " + updatedUser.getEmail());
    }
}