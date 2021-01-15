package jp.co.demo.controller.M02;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.entity.Account;
import jp.co.demo.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class M02Controller {
    private AccountServiceImpl accountService;

    public M02Controller(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    // List User
    @GetMapping(path ={RequestPathConst.M02})
    public String listUser(Account account, Model model) {
        model.addAttribute("accounts", accountService.getUser(account, 0, 10));
        return ScreenPathConst.M02_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_01)
    public String createUser(Model model) {
        model.addAttribute("account", new Account());
        return ScreenPathConst.M02_01_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_02)
    public String detailUser(@RequestParam(value="loginId") String loginId, Model model) {
        Account account = accountService.findByLoginId(loginId);
        model.addAttribute("account", account);
        return ScreenPathConst.M02_02_SCREEN;
    }

    // Create User
    @PostMapping(RequestPathConst.M02_01)
    public String addUser(@ModelAttribute Account account, Model model) {
        model.addAttribute("account", account);
        Account account1 = accountService.findByLoginId(account.getLoginId());
        if (account1 != null) {
            model.addAttribute("account", account1);
            return ScreenPathConst.M02_01_SCREEN;
        } else {
            accountService.createUser(account);
            return BaseConst.REDIRECT + RequestPathConst.M02;
        }
    }

    // Edit User
    @PostMapping(path= {RequestPathConst.M02_02}, params = "Edit")
    public String updateUser(@ModelAttribute Account account, Model model) {
        model.addAttribute("account", account);
        accountService.updateUser(account);
        return BaseConst.REDIRECT + RequestPathConst.M02;
    }

    // Delete User
    @PostMapping(path= {RequestPathConst.M02_02}, params = "Delete")
    public String deleteUser(@ModelAttribute Account account, Model model) {
        model.addAttribute("account", account);
        accountService.deleteUser(account);
        return BaseConst.REDIRECT + RequestPathConst.M02;
    }
}
