package jp.co.demo.security;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.security.service.ContextService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SuccessHandler
 */
@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ContextService contextService;

    public SuccessHandler(ContextService contextService) {
        this.contextService = contextService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        contextService.removeSecurityContextByCookie(request);

        AccountDetails simpleUser = (AccountDetails) authentication.getPrincipal();
        LoginInfo loginInfo = new LoginInfo(simpleUser);
        redirectResponse(loginInfo, request, response);
    }

    /**
     * Send redirect response with result checking login success or not
     */
    private void redirectResponse(LoginInfo loginInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(BaseConst.USER_SESSION, loginInfo);

        contextService.storesSecurityContextInRedis(request);

        String url = request.getContextPath() + RequestPathConst.M04;
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect(url);
    }
}
