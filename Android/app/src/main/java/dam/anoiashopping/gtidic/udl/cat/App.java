package dam.anoiashopping.gtidic.udl.cat;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;
import dam.anoiashopping.gtidic.udl.cat.views.LoginActivity;
import dam.anoiashopping.gtidic.udl.cat.views.MainActivity;

public class App extends Application {

    String TAG = "App";

    @Override
    public void onCreate() {

        super.onCreate();
        PreferencesProvider.init(this);

        if (!PreferencesProvider.providePreferences().getString("token", "").equals("")) {

            Log.d (TAG, "L'usuari ja té token: " + PreferencesProvider.providePreferences().getString("token", "") + ". Iniciant pantalla principal.");

            Intent intent = new Intent (App.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity (intent);

        } else {

            Log.d (TAG, "L'usuari no té token. Iniciant pantalla Inici de sessió.");

            Intent intent = new Intent (App.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity (intent);
        }
    }
}
