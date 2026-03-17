package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {

    // Объявляем переменные, которые будем использовать во всех тестах
    static Playwright playwright;
    static Browser browser;
    Page page; // Страница (вкладка) для каждого теста

    // @BeforeAll - выполнится один раз перед всеми тестами
    @BeforeAll
    static void setup() {
        playwright = Playwright.create(); // Запускаем движок Playwright
        // Запускаем браузер (Chromium) НЕ в фоне, чтобы видеть, что происходит
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Не в фоне (headed mode)
                .setSlowMo(100));   // Замедление на 100ms, чтобы видеть шаги
    }

    // @BeforeEach - выполнится перед каждым тестом
    @BeforeEach
    void createPageAndNavigate() {
        page = browser.newPage(); // Открываем новую вкладку
        page.navigate("https://opensource-demo.orangehrmlive.com/"); // Идём на сайт
    }

    // @Test - это и есть наш тест
    @Test
    void loginTest() {
        // Шаг 1: Найти поле для логина и ввести текст
        page.locator("input[name='username']").fill("Admin");

        // Шаг 2: Найти поле для пароля и ввести текст
        page.locator("input[name='password']").fill("admin123");

        // Шаг 3: Найти кнопку и кликнуть
        page.locator("button[type='submit']").click();

        // Шаг 4: Проверить, что после логина появилась панель (например, заголовок Dashboard)
        Locator dashboardHeader = page.locator("h6:has-text('Dashboard')");
        // Проверяем, что элемент виден
        assertTrue(dashboardHeader.isVisible(), "Заголовок Dashboard не появился, логин скорее всего не удался");
    }

    // @AfterEach - выполнится после каждого теста
    @AfterEach
    void closePage() {
        page.close();
    }

    // @AfterAll - выполнится один раз после всех тестов
    @AfterAll
    static void teardown() {
        browser.close();  // Закрываем браузер
        playwright.close(); // Закрываем движок Playwright
    }
}
