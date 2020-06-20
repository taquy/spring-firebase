package fpt.fbiz.fremote.configs.auth;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private static String HEADER_NAME = "X-Auth";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(xAuth)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
//                FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);
//
//                String userName = holder.getUid();
//
//                Authentication auth = new FirebaseAuthenticationToken(userName, holder);
//                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (FirebaseTokenInvalidException e) {
                throw new SecurityException(e);
            }
        }
    }

}
