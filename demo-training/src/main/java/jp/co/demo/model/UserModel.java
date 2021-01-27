package jp.co.demo.model;

import jp.co.demo.entity.Account;
import jp.co.demo.enums.AccountStatus;

public class UserModel {
    private long id;
    private String loginId;
    private String fullName;
    private String password;
    private String newPassword;
    private String mailAddress;
    private AccountStatus accountStatus;

    public UserModel() {}

    public UserModel(Account account) {
        this.id = account.getId();
        this.loginId = account.getLoginId();
        this.fullName = account.getFullName();
        this.password = account.getPassword();
        this.mailAddress = account.getMailAddress();
        this.accountStatus = account.getAccountStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}


