public class UserService {

    private final PasswordValidator passwordValidator = new PasswordValidator();

    public void registerUser(String password, String repeatPassword) {
        try {
            passwordValidator.validate(password, repeatPassword);
            saveUser(password);  // Уявний метод, що зберігає користувача
            System.out.println("Користувача успішно зареєстровано.");
        } catch (PasswordValidationException e) {
            System.out.println("Ваші паролі невірні. Спробуйте ще раз.");
        }
    }

    // Замість реального збереження просто виведемо повідомлення
    private void saveUser(String password) {
        // Логіка збереження користувача, наприклад у базу даних
        System.out.println("Збережено користувача з паролем: " + password);
    }
}

