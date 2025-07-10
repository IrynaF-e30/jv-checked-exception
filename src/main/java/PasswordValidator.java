package core.basesyntax;

public class PasswordValidator {
    public void validate(User user) throws PasswordValidationException {
        if (user == null
                || user.password() == null || user.repeatPassword() == null
                || user.password().isBlank() || user.repeatPassword().isBlank()
                || !user.password().equals(user.repeatPassword())
                || user.password().length() < 10
                || !user.password().matches(".*[A-Z].*")
                || !user.password().matches(".*[a-z].*")
                || !user.password().matches(".*\\d.*")
                || !user.password().matches(".*[^a-zA-Z0-9].*")) {
            throw new PasswordValidationException("Password is not valid");
        }
    }
}







