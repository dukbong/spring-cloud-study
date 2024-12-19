package study.cloud_user_service.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.cloud_user_service.config.detail.CustomUserDetail;
import study.cloud_user_service.request.RequestLogin;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    @Value("${token.expiration_time}")
//    private long tokenExpirationTime;
//    @Value("${token.secret}")
//    private String tokenSecret;

    private final Environment env;

    public AuthenticationFilter(Environment env) {
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Authentication AttemptAuthentication 시작~>>>>");
        try {
            RequestLogin login = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword(),
                            // 권한 용도
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetail userDetail = (CustomUserDetail)authResult.getPrincipal();

        Signer signer = HMACSigner.newSHA256Signer(env.getProperty("token.secret"));
        long expirationTime = Long.parseLong(env.getProperty("token.expiration_time"));
        ZonedDateTime expirationDate = Instant.ofEpochMilli(System.currentTimeMillis() + expirationTime).atZone(ZoneId.systemDefault());
        JWT jwt = new JWT().setIssuer("")
                .setSubject(userDetail.getUserId())
                .setExpiration(expirationDate);

        String encodeJWT = JWT.getEncoder().encode(jwt, signer);

        response.addHeader("Authorization", "Bearer " + encodeJWT);
        response.addHeader("user-id", userDetail.getUserId());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("unsuccessful authentication");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
    }
}
