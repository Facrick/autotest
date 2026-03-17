package utils;

public class InstallBrowsers {
    public static void main(String[] args) {
        try {
            com.microsoft.playwright.CLI.main(new String[]{"install"});
            System.out.println("Браузеры успешно установлены!");
        } catch (Exception e) {
            System.err.println("Ошибка при установке браузеров:");
            e.printStackTrace();
        }
    }
}