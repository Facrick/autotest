package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты DELETE запросов для пользователей")
public class UserDeleteTests extends BaseTest {

    @Test
    @DisplayName("Удаление существующего пользователя")
    void testDeleteUser() {
        int userId = 1;

        boolean deleted = userService.deleteUser(userId);

        assertTrue(deleted, "Пользователь должен быть успешно удален");
        System.out.println("✓ Пользователь с ID " + userId + " успешно удален");
    }

    @Test
    @DisplayName("Удаление несуществующего пользователя")
    void testDeleteNonExistentUser() {
        int nonExistentId = 999;

        // JSONPlaceholder возвращает 200 даже для несуществующих
        boolean deleted = userService.deleteUser(nonExistentId);

        assertTrue(deleted, "API должен вернуть успех (особенность JSONPlaceholder)");
        System.out.println("✓ Запрос на удаление несуществующего пользователя обработан");

    }
}