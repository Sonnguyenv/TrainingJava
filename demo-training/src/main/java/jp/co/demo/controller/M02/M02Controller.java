package jp.co.demo.controller.M02;

import jp.co.demo.common.BaseConst;
import jp.co.demo.common.RequestPathConst;
import jp.co.demo.common.ScreenPathConst;
import jp.co.demo.entity.Account;
import jp.co.demo.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class M02Controller {
    // attribute account page
    protected static final String ACCOUNT = "account";
    // attribute listAccount page
    protected static final String ACCOUNTS = "accounts";
    // attribute LoginID page
    protected static final String LOGIN_ID = "loginId";
    // attribute PageNo page
    protected static final String PAGE_NO = "pageNo";
    // attribute CurrentPage page
    protected static final String CURRENT_PAGE = "currentPage";
    // attribute TotalPages page
    protected static final String TOTAL_PAGES = "totalPages";
    // attribute TotalItems page
    protected static final String TOTAL_ITEMS = "totalItems";

    protected static final String EDIT = "Edit";
    protected static final String DELETE = "Delete";

    private AccountServiceImpl accountService;

    public M02Controller(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    // List User
    @GetMapping(RequestPathConst.M02)
    public String listUser(Model model, Account account) {
        return findPaginated(1, model, account);
    }

    @GetMapping(RequestPathConst.M02_01)
    public String createUser(Model model) {
        model.addAttribute(ACCOUNT, new Account());
        return ScreenPathConst.M02_01_SCREEN;
    }

    @GetMapping(RequestPathConst.M02_02)
    public String detailUser(@RequestParam(value = LOGIN_ID) String loginId, Model model) {
        Account account = accountService.findByLoginId(loginId);
        model.addAttribute(ACCOUNT, account);
        return ScreenPathConst.M02_02_SCREEN;
    }

    // Create User
    @PostMapping(RequestPathConst.M02_01)
    public String addUser(@ModelAttribute Account account, Model model) {
        model.addAttribute(ACCOUNT, account);
        Account oldAccount = accountService.findByLoginId(account.getLoginId());
        if (oldAccount != null) {
            model.addAttribute(ACCOUNT, oldAccount);
            return ScreenPathConst.M02_01_SCREEN;
        } else {
            accountService.createUser(account);
            return BaseConst.REDIRECT + RequestPathConst.M02;
        }
    }

    // Edit User
    @PostMapping(path= {RequestPathConst.M02_02}, params = EDIT)
    public String updateUser(@ModelAttribute Account account, Model model) {
        model.addAttribute(ACCOUNT, account);
        accountService.updateUser(account);
        return BaseConst.REDIRECT + RequestPathConst.M02;
    }

    // Delete User
    @PostMapping(path= {RequestPathConst.M02_02}, params = DELETE)
    public String deleteUser(@ModelAttribute Account account, Model model) {
        model.addAttribute(ACCOUNT, account);
        accountService.deleteUser(account);
        return BaseConst.REDIRECT + RequestPathConst.M02;
    }

    @GetMapping(RequestPathConst.M02_02_PAGE)
    public String findPaginated(@PathVariable (value = PAGE_NO) int pageNo, Model model, Account account) {
        int pageSize = 10;

        Page<Account> page = accountService.findPaginated(pageNo, pageSize, account);
        List<Account> accounts = page.getContent();

        model.addAttribute(CURRENT_PAGE, pageNo);
        model.addAttribute(TOTAL_PAGES, page.getTotalPages());
        model.addAttribute(TOTAL_ITEMS, page.getTotalElements());

        model.addAttribute(ACCOUNT, account);
        model.addAttribute(ACCOUNTS, accounts);
        return ScreenPathConst.M02_SCREEN;
    }
}
