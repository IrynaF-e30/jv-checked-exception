public record User(
        String login,
        String password,
        String repeatPassword
) {

    @Override
    public String toString() {
        return "User{login='" + login + "', password='" + password + "', repeatPassword='"
                + repeatPassword + "'}";
    }

}



