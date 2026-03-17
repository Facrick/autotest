package utils;

import models.*;

public class TestDataFactory {

    public static User createTestUser() {
        User user = new User();
        user.setName("Тестовый Пользователь");
        user.setUsername("testuser_" + System.currentTimeMillis());
        user.setEmail("test@example.com");
        user.setPhone("123-456-7890");
        user.setWebsite("testuser.com");
        return user;
    }

    public static User createUserWithAllFields() {
        Geo geo = new Geo("-37.3159", "81.1496");
        Address address = new Address("Kulas Light", "Apt. 556",
                "Gwenborough", "92998-3874", geo);
        Company company = new Company("Romaguera-Crona",
                "Multi-layered client-server neural-net",
                "harness real-time e-markets");

        return new User(1, "Leanne Graham", "Bret",
                "Sincere@april.biz", address,
                "1-770-736-8031 x56442", "hildegard.org", company);
    }

    public static User createUserForUpdate() {
        User user = new User();
        user.setName("Обновленное Имя");
        user.setUsername("updateduser");
        user.setEmail("updated@example.com");
        user.setPhone("999-999-9999");
        user.setWebsite("updated.com");
        return user;
    }
}