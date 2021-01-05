package jp.co.demo.security;

import jp.co.demo.enums.Authorities;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * LoginInfo
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginInfo extends AccountDetails implements Serializable {

    private Authorities authority;

    public LoginInfo(AccountDetails simpleUser) {
        super(simpleUser.getId(), simpleUser.getUsername(), simpleUser.getPassword(),
                simpleUser.getAuthorities(), simpleUser.getHospitalIds());

        simpleUser.getAuthorities().forEach(grantedAuthority -> Authorities.valueOf(grantedAuthority.getAuthority()));
    }
}
