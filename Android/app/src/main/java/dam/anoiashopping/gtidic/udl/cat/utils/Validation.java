package dam.anoiashopping.gtidic.udl.cat.utils;

import android.content.Context;

import dam.anoiashopping.gtidic.udl.cat.R;

public class Validation {

    //TODO
    Context context;
    public Validation(Context context) {
        this.context=context;
    }

    public ValidationResultImpl isValidUsername(String username) {

        String msg = "";
        boolean isValid = true;

        if (username.isEmpty()) {
            msg = this.context.getString(R.string.emptyUsername);
            isValid = false;
        }

        return new ValidationResultImpl(msg, isValid);
    }

    public static void getUsernameError (String msg, boolean isValid, String username) {




    }
}
