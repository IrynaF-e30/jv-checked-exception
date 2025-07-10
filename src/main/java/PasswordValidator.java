public class PasswordValidator {
    public void validate(User user) throws PasswordValidationException {
        String password = user.password();
        String repeatPassword = user.repeatPassword();

        if (password == null || repeatPassword == null
                || password.isBlank() || repeatPassword.isBlank()
                || !password.equals(repeatPassword)
                || password.length() < 10
                || !password.matches(".*[A-Z].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*\\d.*")
                || !password.matches(".*[^a-zA-Z0-9].*")) {
            throw new PasswordValidationException("Password is not valid");
        }

    }
}










