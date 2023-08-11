package solutions.ntq.social.ntq_fresher_social.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.security.userprincal.CustomUserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret.key}")
    private String JWT_SECRET;
    @Value("${jwt.time.expired}")
    private String JWT_EXPIRATION;

    public String generateToken(User user, Date now) {
        Date expireDate = new Date(now.getTime() + Long.parseLong(JWT_EXPIRATION));
        Map<String, Object> claim = new HashMap<>();
        claim.put("username", user.getUsername());
        return Jwts.builder() // Tạo chuỗi json web token từ id của user.
                .setSubject(Long.toString(user.getId()))
                .setAudience(user.getUsername())
                .setIssuedAt(now)
                .setClaims(claim)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String createTokenForJwt(Authentication authentication, Date now) {
        CustomUserDetails userPrinciple = (CustomUserDetails) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(now.getTime() + Long.parseLong(JWT_EXPIRATION)))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody()
                .getSubject();
        return userName;
    }

    public String getTokenJwtFromRequest(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            ex.printStackTrace();
            log.error("Invalid Jwt signature");
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            ex.printStackTrace();
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            ex.printStackTrace();
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            log.error("JWT claims string is empty.");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error token valid: " + ex.getMessage());
        }
        return false;
    }
}
