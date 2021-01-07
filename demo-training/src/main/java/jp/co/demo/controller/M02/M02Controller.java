package jp.co.demo.controller.M02;

import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.entity.Account;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class M02Controller {
    private AccountServiceImpl accountService;

    public M02Controller(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(RequestPathConst.M02)
    public String listUser() {
        return ScreenPathConst.M02_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_01)
    public String createUser(Model model) {
        model.addAttribute("account", new Account());
        return ScreenPathConst.M02_01_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_02)
    public String detailUser() {
        return ScreenPathConst.M02_02_SCREEN;
    }

    @PostMapping(RequestPathConst.M02_01)
    public String addUser(@ModelAttribute Account account) {
        accountService.postUser(account);
        return "succes";
    }
}
