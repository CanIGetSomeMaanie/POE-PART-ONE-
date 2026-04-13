import java.util.Scanner;

public class Registration {

    Scanner scanner = new Scanner(System.in);

    private String username;
    private String password;
    private String phone;

    // Username check
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password check
    public boolean checkPasswordComplexity(String password) {

        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Phone check (regex)
    public boolean checkCellPhoneNumber(String phone) {
        return phone.matches("^(\\+27|0)[0-9]{9}$");
    }

    // REGISTER METHOD (THIS IS IMPORTANT — RETURNS MESSAGES)
    public String registerUser() {

        System.out.println("Enter username must contain no more than 5 letters and a underscore ");
        username = scanner.nextLine();

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure username contain no more than 5 letters and a underscore";
        }

        System.out.println("Enter password, ensure that the password contains at least eight characters, a capital letter, a number, and a special character: ");
        password = scanner.nextLine();

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        System.out.println("Enter phone number: ");
        phone = scanner.nextLine();

        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // getters for login
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }}