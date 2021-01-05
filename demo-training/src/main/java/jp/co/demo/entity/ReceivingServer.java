package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * ReceivingServer
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class ReceivingServer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receiving_server_seq")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String ipAddress;

    @NotNull
    @Column(length = 20)
    private String username;

    private int port;

    @NotNull
    @Column(length = 50)
    private String password;

    @NotNull
    @Column(length = 100)
    private String receivingDirectory;

    @OneToOne
    private Hospital hospital;
}
