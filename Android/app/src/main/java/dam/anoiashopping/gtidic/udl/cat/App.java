package dam.anoiashopping.gtidic.udl.cat;

import android.app.Application;

import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;

public class App extends Application {

    String TAG = "App";

    @Override
    public void onCreate() {

        super.onCreate();
        PreferencesProvider.init(this);

    }
}
