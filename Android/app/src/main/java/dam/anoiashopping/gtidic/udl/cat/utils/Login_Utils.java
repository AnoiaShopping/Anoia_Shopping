package dam.anoiashopping.gtidic.udl.cat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Utils {

    //@Jordi: Afegir testos unitaris

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

    String regexName = "/^[a-zA-Zà-ÿÀ-Ý]{2,15}[\\s]?[a-zA-Zà-ÿÀ-Ý]{0,15}$/i";
    return name.matches(regexName);
    }
    
}
