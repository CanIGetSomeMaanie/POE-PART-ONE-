public class RegistrationTest {

    public static void main(String[] args) {

        Registration reg = new Registration();

        System.out.println(reg.checkUserName("kyl_1")); // true
        System.out.println(reg.checkUserName("kyle!!!!")); // false

        System.out.println(reg.checkPasswordComplexity("Ch&&sec@ke99!")); // true
        System.out.println(reg.checkPasswordComplexity("password")); // false

        System.out.println(reg.checkCellPhoneNumber("+27838968976")); // true
        System.out.println(reg.checkCellPhoneNumber("08966553")); // false
    }
}