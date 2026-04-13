import static org.junit.jupiter.api.Assertions.*;

class JunitTest {
    Registration reg = new Registration();

    @org.junit.jupiter.api.Test
    void checkUserNameTrue() {
        assertTrue(reg.checkPasswordComplexity("kyl_1"));
    }
    @org.junit.jupiter.api.Test
    void checkerUserNameFalse(){
        assertFalse(reg.checkUserName("Kyle!!!"));
    }

    @org.junit.jupiter.api.Test
    void checkPasswordComplexityTrue() {
        assertTrue(reg.checkPasswordComplexity("Ch&&sec@ke99!"));

    }

    @org.junit.jupiter.api.Test
    void checkPasswordComplexityFalse() {
      assertFalse(reg.checkPasswordComplexity("password"));
    }

    @org.junit.jupiter.api.Test
    void checkPhoneNumberTrue() {
        assertTrue(reg.checkCellPhoneNumber("+27833667508"));

        }

    @org.junit.jupiter.api.Test
    void checkPhoneNumberFalse() {
        assertTrue(reg.checkCellPhoneNumber("155645454545454"));
    }
}