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

    }
}
