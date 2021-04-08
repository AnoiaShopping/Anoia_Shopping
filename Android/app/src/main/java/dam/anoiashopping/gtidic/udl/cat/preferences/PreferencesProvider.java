package dam.anoiashopping.gtidic.udl.cat.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesProvider {
    private static String SHARED_PREFERENCES = "mPreferences";

    private static SharedPreferences sPreferences;

    public static SharedPreferences providePreferences() {
        return sPreferences;
    }
    public static void init(Context context) {

        sPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

    }
}