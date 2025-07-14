package core.basesyntax;

public class User {
    private final String password;
    private final String repeatPassword;

    public User(String password, String repeatPassword) {
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}

