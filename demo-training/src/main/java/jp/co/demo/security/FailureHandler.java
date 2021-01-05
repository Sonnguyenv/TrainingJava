package jp.co.demo.security;

import jp.co.demo.common.RequestPathConst;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * FailureHandler
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String url = String.format("%s%s?error", request.getContextPath(), RequestPathConst.M01);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendRedirect(url);
    }
}
