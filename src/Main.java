public class Main {

    public static void main(String[] args) {

        Registration reg = new Registration();
        String regMessage = reg.registerUser();
        System.out.println(regMessage);

        Login login = new Login();

        boolean success = login.loginUser(
                reg.getUsername(),
                reg.getPassword(),
                reg.getUsername(),
                reg.getPassword()
        );

        System.out.println(login.returnLoginStatus(success, reg.getUsername()));
    }
}
