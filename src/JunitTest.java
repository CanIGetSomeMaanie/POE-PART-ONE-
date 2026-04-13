import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JunitTest {

    Registration reg = new Registration();
    Login login = new Login();

    // =====================
    // ASSERT TRUE / FALSE
    // =====================

    @Test
    void usernameCorrect() {
        assertTrue(reg.checkUserName("kyl_1"));
    }

    @Test
    void usernameIncorrect() {
        assertFalse(reg.checkUserName("kyle!!!!"));
    }

    @Test
    void passwordCorrect() {
        assertTrue(reg.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void passwordIncorrect() {
        assertFalse(reg.checkPasswordComplexity("password"));
    }

    @Test
    void phoneCorrect() {
        assertTrue(reg.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void phoneIncorrect() {
        assertFalse(reg.checkCellPhoneNumber("08966553"));
    }

    @Test
    void loginSuccess() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void loginFail() {
        assertFalse(login.loginUser("wrong", "wrong", "kyl_1", "Ch&&sec@ke99!"));
    }

    // =====================
    // ASSERT EQUALS (MESSAGES)
    // =====================

    public String registerUser(String username, String password, String phoneNumber) {

        if (!reg.checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!reg.checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!reg.checkCellPhoneNumber(phoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        // IMPORTANT: assignment wants these messages separately
        return "Cell number successfully captured.";
    }}