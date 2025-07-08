public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // Правильний пароль
        userService.registerUser("securepassword", "securepassword");

        // Неправильний (короткий) пароль
        userService.registerUser("short", "short");

        // Різні паролі
        userService.registerUser("longpassword123", "differentpassword");
    }
}

