package dam.anoiashopping.gtidic.udl.cat.manager;

import android.content.SharedPreferences;

import dam.anoiashopping.gtidic.udl.cat.preferences.PreferencesProvider;

public class SessionManager {

    private final SharedPreferences sharedPreferences;

    public SessionManager () {
        this.sharedPreferences = PreferencesProvider.providePreferences();
    }

    public void firstTimeAsking (String permission, boolean isFirstTime) {
        sharedPreferences.edit().putBoolean(permission, isFirstTime).apply();
    }

    public boolean isFirstTimeAsking (String permission) {
        return sharedPreferences.getBoolean(permission, true);
    }

}
