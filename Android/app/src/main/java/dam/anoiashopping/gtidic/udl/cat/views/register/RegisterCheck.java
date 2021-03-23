package dam.anoiashopping.gtidic.udl.cat.views.register;

import android.widget.CheckBox;
import android.widget.EditText;
import dam.anoiashopping.gtidic.udl.cat.utils.*;

public class RegisterCheck extends RegisterActivity{

    // Comprobem que totes les dades estiguin plenes per registrar-se

    protected static boolean CheckAll (EditText First_Name, EditText Last_Name, EditText User_Name,
                                       EditText Email, EditText Password1, EditText Password2,
                                       CheckBox Accept_Terms_conditions) {

        Login_Utils Login_Utils = new Login_Utils();
        int i = 0;

        //Aquesta Activity no cal, tot a RegisterCheck
        if (First_Name.getText().toString().isEmpty()) {
            First_Name.setError("Escriu el teu Nom");
            i++;
        }

        if (Last_Name.getText().toString().isEmpty()) {
            Last_Name.setError("Escriu el teu Cognom");
            i++;
        }

        if (User_Name.getText().toString().isEmpty()) {
            User_Name.setError("Escriu el teu Nom d'Usuari");
            i++;
        }

        if (Email.getText().toString().isEmpty()) {
            Email.setError("Escriu el teu Correu");
            i++;
        } else if (!Login_Utils.isValidEmailAddress(Email.getText().toString())) {
            Email.setError("Correu Invàlid");
            i++;
        }

        if (Password1.getText().toString().isEmpty()) {
            Password1.setError("Escriu una Contrassenya");
            i++;
        } else if (!Login_Utils.isValidPassword(Password1.getText().toString())) {
            Password1.setError("Contrassenya Invàlida");
            i++;
        }

        if (Password2.getText().toString().isEmpty()) {
            Password2.setError("Repeteix la Contrassenya");
            i++;
        }  else if (!Password1.getText().toString().equals(Password2.getText().toString())) {
            Password2.setError("Les Contrassenyes han de coincidir");
            i++;
        }

        if (!Accept_Terms_conditions.isChecked()) {
            Accept_Terms_conditions.setError("Has d'acceptar els Termes i Condicions per registrar-te");
            i++;
        }else{
            Accept_Terms_conditions.setError(null);
        }
        return i == 0;
    }
}
