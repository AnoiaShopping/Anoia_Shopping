package dam.anoiashopping.gtidic.udl.cat.utils;

import android.content.Context;

import dam.anoiashopping.gtidic.udl.cat.R;
import dam.anoiashopping.gtidic.udl.cat.models.Account;

public class Validation {

    //TODO
    Context context;
    public Validation(Context context) {
        this.context = context;
    }

    public boolean validation (Account account) {

        boolean Return = true;
        ValidationResultImpl validationResultImpl = new ValidationResultImpl("", true);


        validationResultImpl = isValidFirstName(account.getFirstname());
        if (!validationResultImpl.isValid()) {
            Return = false;
            System.out.println(validationResultImpl.getMsgError());
            //TODO: ensenyar error
        }

        validationResultImpl = isValidLastName(account.getLastname());
        if (!validationResultImpl.isValid()) {
            Return = false;
            System.out.println(validationResultImpl.getMsgError());
            //TODO: ensenyar error
        }

        validationResultImpl = isValidUsername (account.getUsername());
        if (!validationResultImpl.isValid()) {
            Return = false;
            System.out.println(validationResultImpl.getMsgError());
            //TODO: ensenyar error
        }

        validationResultImpl = isValidEmail (account.getEmail());
        if (!validationResultImpl.isValid()) {
            Return = false;
            System.out.println(validationResultImpl.getMsgError());
            //TODO: ensenyar error
        }

        validationResultImpl = isValidPassword (account.getPassword());
        if (!validationResultImpl.isValid()) {
            Return = false;
            System.out.println(validationResultImpl.getMsgError());
            //TODO: ensenyar error
        }

        return Return;

    }

    private ValidationResultImpl isValidFirstName(String name) {

        String msg = "";
        boolean isValid = true;

        String regex = "^[a-zA-Zà-ÿÀ-Ý]{2,15}[\\s]?[a-zA-Zà-ÿÀ-Ý]{0,15}$";
        if (!name.matches(regex)) {

            isValid = false;

            if (name.isEmpty()) {
                msg = context.getString(R.string.emptyFirstName);
            } else {
                msg = context.getString(R.string.wrongFirstName);
            }
        }

        return new ValidationResultImpl(msg, isValid);
    }

    private ValidationResultImpl isValidLastName(String name) {

        String msg = "";
        boolean isValid = true;

        String regex = "^[a-zA-Zà-ÿÀ-Ý]{2,15}[\\s]?[a-zA-Zà-ÿÀ-Ý]{0,15}$";
        if (!name.matches(regex)) {

            isValid = false;

            if (name.isEmpty()) {
                msg = context.getString(R.string.emptyLastName);
            } else {
                msg = context.getString(R.string.wrongLastName);
            }
        }

        return new ValidationResultImpl(msg, isValid);
    }

    private ValidationResultImpl isValidUsername (String Username) {

        String msg = "";
        boolean isValid = true;

        String regex = "^[\\w-.]{2,20}$";
        if (Username.matches(regex)) {

            isValid = false;

            if (Username.isEmpty()) {
                msg = context.getString(R.string.emptyUsername);
            } else {
                msg = context.getString(R.string.wrongUsername);
            }
        }

        return new ValidationResultImpl(msg, isValid);
    }

    private ValidationResultImpl isValidEmail (String email) {

        String msg = "";
        boolean isValid = true;

        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(regex)) {

            isValid = false;

            if (email.isEmpty()) {
                msg = context.getString(R.string.emptyEmail);
            } else {
                msg = context.getString(R.string.wrongEmail);
            }
        }

        return new ValidationResultImpl(msg, isValid);
    }

    private ValidationResultImpl isValidPassword (String password) {

        String msg = "";
        boolean isValid = true;

        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        if (!password.matches(regex)) {

            isValid = false;

            if (password.isEmpty()) {
                msg = context.getString(R.string.emptyPassword);
            } else {
                msg = context.getString(R.string.wrongPassword);
            }
        }

        return new ValidationResultImpl(msg, isValid);
    }

}
