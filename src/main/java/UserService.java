public class UserService {
    private final PasswordValidator passwordValidator = new PasswordValidator();

    public void registerUser(String password, String repeatPassword) {
        try {
            passwordValidator.validate(password, repeatPassword);
            saveUser(password);
            System.out.println("Користувача успішно зареєстровано.");
        } catch (PasswordValidationException e) {
            System.out.println("Ваші паролі невірні. Спробуйте ще раз.");
        }
    }

    private void saveUser(String password) {
        System.out.println("Збережено користувача з паролем: " + password);
    }
}


