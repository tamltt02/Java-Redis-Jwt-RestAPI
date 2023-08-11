package solutions.ntq.social.ntq_fresher_social.security.userprincal;

import org.springframework.security.crypto.password.PasswordEncoder;
import solutions.ntq.social.ntq_fresher_social.utils.EncryptUtils;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return EncryptUtils.crc32PasswordEncoder(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) { // so sanh pass duoc tao ra va chuoi truyen vao
        return s.equals(EncryptUtils.crc32PasswordEncoder(charSequence.toString()));
    }
}
