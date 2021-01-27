package jp.co.demo.controller.M03;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.model.UserModel;
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
    protected final String USER_MODEL = "userModel";

    protected final String ERROR = "error";

    private final AccountServiceImpl accountService;

    public M03Controller(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(RequestPathConst.M03)
    public String getScreen(Model model) {
        model.addAttribute(USER_MODEL, new UserModel());
        return ScreenPathConst.M03_SCREEN;
    }

    // Chang Password User Login
    @PostMapping(RequestPathConst.M03)
    public String changePassword(@ModelAttribute UserModel userModel, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.findByLoginId(auth.getName());
        String newPassword = userModel.getNewPassword();
        boolean check = accountService.checkIfValidOldPassword(userModel, account.getPassword());
        if (check == false) {
            model.addAttribute(ERROR, "error");
            return ScreenPathConst.M03_SCREEN;
        }
        account.setPassword(newPassword);
        accountService.updateUser(account);
        model.addAttribute(USER_MODEL, userModel);
        return BaseConst.REDIRECT + RequestPathConst.M02;
    }
}
