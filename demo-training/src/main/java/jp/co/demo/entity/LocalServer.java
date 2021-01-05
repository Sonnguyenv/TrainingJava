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
 * StorageServer
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class LocalServer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_server_seq")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String localDirectory;

    @OneToOne
    private ListeningFolder listeningFolder;
}
