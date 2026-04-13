public class LoginTest {

    public static void main(String[] args) {

        Login login = new Login();

        String storedUser = "kyl_1";
        String storedPass = "Ch&&sec@ke99!";

        // TEST 1: Correct login (should be TRUE)
        boolean successLogin = login.loginUser("kyl_1", "Ch&&sec@ke99!", storedUser, storedPass);
        System.out.println("Login Successful Test: " + successLogin); // expected: true

        // TEST 2: Incorrect login (should be FALSE)
        boolean failLogin = login.loginUser("wrongUser", "wrongPass", storedUser, storedPass);
        System.out.println("Login Failed Test: " + failLogin); // expected: false

        // Optional: show return messages too
        System.out.println(login.returnLoginStatus(successLogin, storedUser));
        System.out.println(login.returnLoginStatus(failLogin, storedUser));
    }
}