public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();

        User user = new User("email@email.com", "Password123", "Password123");

        service.registerUser(user); // 🔧 додаємо виклик методу
    }
}

