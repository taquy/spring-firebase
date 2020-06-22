package fpt.fbiz.fremote.configs.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fpt.fbiz.fremote.consts.SecurityConstant;
import fpt.fbiz.fremote.entities.User;
import fpt.fbiz.fremote.repositories.UserRepository;
import fpt.fbiz.fremote.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class JwtFilter extends GenericFilterBean {

    private final UserRepository userRepository;

    public JwtFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private byte[] restResponseBytes(ApiResponse response) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(response);
        return serialized.getBytes();
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_STRING);
        if (token != null) {
            var jwtParser = JWT.require(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes())).build();
            var verifyResult = jwtParser.verify(token.replace(SecurityConstant.TOKEN_PREFIX, ""));

            var payload = verifyResult.getPayload();
            if (payload == null) {
                return null;
            }

            byte[] decodedBytes = Base64.getDecoder().decode(payload);
            String decodedString = new String(decodedBytes);
            JsonObject jsonObject = new JsonParser().parse(decodedString).getAsJsonObject();
            var claims = jsonObject.get("claims");

            Gson gson = new Gson();
            var userObj = gson.fromJson(claims, User.class);
            var userOpt = userRepository.findById(userObj.getId());

            if (!userOpt.isPresent()) {
                return null;
            }

            var user = userOpt.get();

            return new UsernamePasswordAuthenticationToken(user, user, user.getAuthorities());
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) servletRequest;
        var res = (HttpServletResponse) servletResponse;
        String header = req.getHeader(SecurityConstant.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        try {
            var authentication = getAuthentication(req);

            if (authentication == null) {
                throw new Exception("Unable to parse token");
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            var result = ApiResponse.error(ex.getMessage());
            res.getOutputStream().write(restResponseBytes(result));
        }

        chain.doFilter(req, res);
    }
}
