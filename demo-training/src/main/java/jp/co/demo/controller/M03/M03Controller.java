package jp.co.demo.controller.M03;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.entity.Account;
import jp.co.demo.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
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

    private final AccountServiceImpl accountService;

    public M03Controller(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(RequestPathConst.M03)
    public String getScreen(Model model) {
        model.addAttribute(ACCOUNT, new Account());
        return ScreenPathConst.M03_SCREEN;
    }

    // Create User
    @PostMapping(RequestPathConst.M03)
    public String changePassword(@ModelAttribute Account account, Model model) {
        model.addAttribute(ACCOUNT, new Account());
        return ScreenPathConst.M03_SCREEN;
    }
}
