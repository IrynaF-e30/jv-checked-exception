public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        // ❗ Створюємо об'єкт User
        User user1 = new User("short", "short");
        service.registerUser(user1);

        User user2 = new User("securePassword1", "securePassword1");
        service.registerUser(user2);
    }
}

