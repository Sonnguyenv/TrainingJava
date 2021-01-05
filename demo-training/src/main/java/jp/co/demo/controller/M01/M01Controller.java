package jp.co.demo.controller.M01;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.controller.BaseController;
import jp.co.demo.entity.Account;
import jp.co.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * M01Controller
 */
@Controller
@Slf4j
public class M01Controller extends BaseController {

    private final AccountService accountService;

    public M01Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = {RequestPathConst.HOME, RequestPathConst.M, RequestPathConst.M01})
    public String login(@RequestParam(value = LOGOUT, required = false) String logout, HttpServletResponse response) {
        if (logout != null) {
            Cookie cookie = new Cookie(BaseConst.UID, null);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            log.info("# User logout success");
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (Objects.nonNull(auth) && !(auth instanceof AnonymousAuthenticationToken)) {
                Account loggedInUser = accountService.findByLoginId(auth.getName());
                if (Objects.nonNull(loggedInUser)) {
                    return BaseConst.REDIRECT + RequestPathConst.M02;
                }
            }

            log.info("# Init user login");
        }
        return ScreenPathConst.M01_SCREEN;
    }
}
