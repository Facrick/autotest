package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class FirstTest {

    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)  // Видим браузер
                .setSlowMo(2000));     // Замедление, чтоб успевать смотреть
    }

    @BeforeEach
    void setUp() {
        page = browser.newPage();
    }

    @Test
    void firstTest() {
        page.navigate("https://opensource-demo.orangehrmlive.com/");

        // Вводим логин
        page.locator("input[name='username']").fill("Admin");
        // Вводим пароль
        page.locator("input[name='password']").fill("admin123");
        // Жмём кнопку
        page.locator("button[type='submit']").click();
        // Проверяем, что залогинились
        var x3 = page.locator("h6:has-text('Dashboard')").isVisible();

        Assertions.assertTrue(x3);

        System.out.println("Тест прошел, красава!");
    }

    @AfterEach
    void tearDown() {
        page.close();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }
}
