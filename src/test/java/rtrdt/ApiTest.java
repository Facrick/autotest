package rtrdt;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {

    static Playwright playwright;
    static APIRequestContext request;

    @BeforeAll
    static void setUp() {
        // Запускаем Playwright
        playwright = Playwright.create();

        // Создаем контекст для API-запросов с базовым URL
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://jsonplaceholder.typicode.com"));
    }

    @Test
    void testGetTodos() {
        // 1. Отправляем GET запрос
        APIResponse response = request.get("/todos");

        // 2. Проверяем статус ответа
        assertEquals(200, response.status(), "Статус должен быть 200 OK");

        // 3. Получаем тело ответа как текст (работает всегда)
        String responseBody = response.text();
        assertNotNull(responseBody, "Тело ответа не должно быть null");
        assertFalse(responseBody.isEmpty(), "Тело ответа не должно быть пустым");

        // 4. Выводим первые 200 символов для проверки
        System.out.println("Первые 200 символов ответа: " + responseBody.substring(0, Math.min(200, responseBody.length())));
        System.out.println("Тест API успешно выполнен!");
    }

    @AfterAll
    static void tearDown() {
        if (request != null) {
            request.dispose();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
