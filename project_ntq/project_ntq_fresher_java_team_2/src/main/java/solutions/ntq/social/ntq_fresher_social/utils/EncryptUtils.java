package solutions.ntq.social.ntq_fresher_social.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;
import java.util.zip.CRC32;

public class EncryptUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String crc32PasswordEncoder(String pass) {
        CRC32 crc32 = new CRC32();
        crc32.update(pass.getBytes());
        return Long.toHexString(crc32.getValue());
    }

    public static String decodeString(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        String dataDecode = new String(decodedBytes);
        return dataDecode;
    }
}
