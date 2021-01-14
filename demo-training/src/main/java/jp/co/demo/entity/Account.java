package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import jp.co.demo.enums.AccountStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Account
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "account_seq")
    private Long id;

    @Column(unique = true, length = 20)
    private String loginId;

    @NotNull
    private String fullName;

    @NotNull
    private String password;

    @Column(length = 100)
    private String mailAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AccountStatus accountStatus = AccountStatus.PROVISIONAL;

    @NotNull
    @Column(length = 20)
    private String authorities;

    @Column(length = 100)
    private String hash;

    private boolean deleted;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Hospital> hospitals;
}
