package jp.co.demo.controller.M03;

import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.entity.Account;
import jp.co.demo.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class M03Controller {
    // attribute account page
    protected static final String ACCOUNT = "account";
    // attribute Confirm Password
    protected  static final String CONFIRM_PASSWORD = "confirm_password";

    private final AccountServiceImpl accountService;

    public M03Controller(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(RequestPathConst.M03)
    public String getScreen(Model model, @ModelAttribute Account account, String confirmPassword) {
        model.addAttribute(ACCOUNT, account);
        model.addAttribute(CONFIRM_PASSWORD, confirmPassword);
        return ScreenPathConst.M03_SCREEN;
    }

    // Chang Password User Login
    @PostMapping(RequestPathConst.M03)
    public String changePassword(@ModelAttribute Account account, Model model, String confirmPassword) {
        model.addAttribute(CONFIRM_PASSWORD, confirmPassword);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account1 = accountService.findByLoginId(auth.getName());
        boolean check = accountService.checkIfValidOldPassword(account, account1.getPassword());
        if (check == true) {
            System.out.printf("aaa");
        }
        model.addAttribute(ACCOUNT, account1);
        return ScreenPathConst.M03_SCREEN;
    }
}
