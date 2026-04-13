public class Login {

    // checks login
    public boolean loginUser(String inputUser, String inputPass,
                             String storedUser, String storedPass) {

        return inputUser.equals(storedUser) && inputPass.equals(storedPass);
    }

    // returns message (REQUIRED)
    public String returnLoginStatus(boolean loginSuccess, String username) {

        if (loginSuccess) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}