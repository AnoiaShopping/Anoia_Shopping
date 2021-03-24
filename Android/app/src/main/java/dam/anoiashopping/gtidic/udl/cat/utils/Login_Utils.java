package dam.anoiashopping.gtidic.udl.cat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Utils {

    public Login_Utils() {
    }

    public boolean isValidEmailAddress(String email){
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidName(String name){

    String regex = "/^[a-z][\\w-]{2,19}$/i"; //De la A a la Z amb 2 a 19 caracters
    return name.matches(regex);
    }
    
}
