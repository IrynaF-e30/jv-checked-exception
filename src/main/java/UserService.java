public class UserService {
    private PasswordValidator passwordValidator = new PasswordValidator();

    public void registerUser(User user) {
        try {
            passwordValidator.validate(user.repeatPassword(),
                    user.password());
            saveUser(user);
        } catch (PasswordValidationException e) {
            System.out.println("Your passwords are incorrect. Try again.");
        }
    }

    private void saveUser(User user) {
        System.out.println(user + " was saved to database!!!");
    }

    public void registerUser(String securepassword, String securepassword1) {
    }
}





