package jp.co.demo.security;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.security.service.ContextService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LogoutSuccessHandler
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    private final ContextService contextService;

    public LogoutSuccessHandler(ContextService contextService) {
        this.contextService = contextService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        request.getSession().removeAttribute(BaseConst.USER_SESSION);

        contextService.removeSecurityContextBySessionId(request);

        String url = String.format("%s%s?logout", request.getContextPath(), RequestPathConst.M01);

        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(url);
    }
}
