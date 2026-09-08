package bank;
public class User {

    private String username;
    private String password;
    private int userId;

    public User(String username, String password, int userId) {

        setUsername(username);
        setPassword(password);
        setUserId(userId);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty."
            );
        }

        this.username = username.trim();
    }

    public void setPassword(String password) {

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty."
            );
        }

        if (password.length() < 10) {
            throw new IllegalArgumentException(
                    "Password cannot be less than 10 characters."
            );
        }

        this.password = password;
    }

    public void setUserId(int userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException(
                    "Bank.User ID must be greater than 0."
            );
        }

        this.userId = userId;
    }

    @Override
    public String toString() {

        return "===========================\n" +
                "Username : " + username +
                "\nPassword : " + password +
                "\nBank.User ID : " + userId +
                "\n===========================";
    }
}