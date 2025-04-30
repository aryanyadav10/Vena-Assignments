package util;

public class EmailValidator {
    public static boolean isValid(String email) {
        return email != null && email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") < email.length() - 1;
    }
}
