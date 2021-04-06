package dam.anoiashopping.gtidic.udl.cat.utils;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Utils {
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String getEncodedHash(String password, String salt, int iterations) {
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA256Digest());
        gen.init(password.getBytes(StandardCharsets.UTF_8), salt.getBytes(), iterations);
        byte[] dk = ((KeyParameter) gen.generateDerivedParameters(256)).getKey();
        byte[] hashBase64 = Base64.encode(dk, Base64.DEFAULT);
        return new String(hashBase64);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encode(String password, String salt, int iterations) {
        String algorithm = "pbkdf2-sha256";
        String hash = getEncodedHash(password, salt, iterations);
        hash = hash.substring(0,hash.length()-1);
        hash =  hash.replaceAll("\\+",".");

        String salt_hash = Base64.encodeToString(salt.getBytes(), Base64.DEFAULT);
        salt_hash = salt_hash.substring(0,salt_hash.length()-1);
        salt_hash = salt_hash.replaceAll("\\+",".");

        return String.format(Locale.ENGLISH,
                "$%s$%d$%s$%s", algorithm, iterations, salt_hash, hash);
    }
}
