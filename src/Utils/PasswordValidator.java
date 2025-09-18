package Utils;

public class PasswordValidator {
    public static boolean validate(String password){
        return password!=null && password.length()>=6;
    }
}
