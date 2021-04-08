package dam.anoiashopping.gtidic.udl.cat.utils;

import java.util.regex.Pattern;

import dam.anoiashopping.gtidic.udl.cat.R;

public class Validation {

    public static ValidationResultImpl checkFirstName (String FirstName) {

        boolean isValid = true;
        int msg_code = 0;

        if (FirstName == null) {

            isValid = false;
            msg_code = R.string.emptyFirstName;

        } else {

            if (!FirstName.matches("^[a-zA-Zà-ÿÀ-Ý]{2,15}[\\s]?[a-zA-Zà-ÿÀ-Ý]{0,15}$")) {

                isValid = false;
                msg_code = R.string.wrongFirstName;
            }

        }

        return new ValidationResultImpl (msg_code, isValid);
    }

    public static ValidationResultImpl checkLastName (String LastName) {

        boolean isValid = true;
        int msg_code = 0;

        if (LastName == null) {

            isValid = false;
            msg_code = R.string.emptyLastName;

        } else {

            if (!LastName.matches("^[a-zA-Zà-ÿÀ-Ý]{2,15}[\\s]?[a-zA-Zà-ÿÀ-Ý]{0,15}$")) {

                isValid = false;
                msg_code = R.string.wrongLastName;
            }

        }

        return new ValidationResultImpl (msg_code, isValid);
    }

    public static ValidationResultImpl checkUsername (String Username) {

        boolean isValid = true;
        int msg_code = 0;

        if (Username == null) {

            isValid = false;
            msg_code = R.string.emptyUsername;

        } else {

            if (!Username.matches("^[\\w-.]{2,20}$")) {

                isValid = false;
                msg_code = R.string.wrongUsername;
            }

        }

        return new ValidationResultImpl (msg_code, isValid);
    }

    public static ValidationResultImpl checkEmail (String Email) {

        boolean isValid = true;
        int msg_code = 0;

        if (Email == null) {

            isValid = false;
            msg_code = R.string.emptyEmail;

        } else {

            if (!Email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")) {

                isValid = false;
                msg_code = R.string.wrongEmail;
            }

        }

        return new ValidationResultImpl (msg_code, isValid);
    }

    public static ValidationResultImpl checkPasswords (String Password, String Password2) {

        boolean isValid = true;
        int msg_code = 0;

        if (Password == null) {

            isValid = false;
            msg_code = R.string.emptyPassword;

        } else {

            if (!Password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {

                isValid = false;
                msg_code = R.string.wrongPassword;

            }
            else if (!Password.equals(Password2)) {

                isValid = false;
                msg_code = R.string.samePassword;
            }

        }

        return new ValidationResultImpl (msg_code, isValid);
    }

    public static ValidationResultImpl checkPassword (String Password) {

        boolean isValid = true;
        int msg_code = 0;

        if (Password == null) {

            isValid = false;
            msg_code = R.string.emptyPassword;

        } else {

            if (!Password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {

                isValid = false;
                msg_code = R.string.wrongPassword;

            }

        }

        return new ValidationResultImpl (msg_code, isValid);
    }

}
