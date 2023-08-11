package solutions.ntq.social.ntq_fresher_social.security.jwt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import solutions.ntq.social.ntq_fresher_social.security.userprincal.UserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtTokenProvider jwtTokenProvider;
    @Autowired
    private   UserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtTokenProvider.getTokenJwtFromRequest(request); // Lấy jwt từ request
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                String username = jwtTokenProvider.getUserNameFromToken(jwt);// Lấy username từ chuỗi jwt
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username); // Lấy thông tin người dùng từ user
                if (userDetails != null) {// Nếu người dùng hợp lệ, set thông tin user cho Seturity Context để biết được đã login hay chưa và request đấy được user nào gửi lên
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            log.error("failed on set user authentication", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // Kiểm tra xem header Authorization có chứa thông tin jwt không
            return bearerToken.substring(7);
        }
        return null;
    }
}
