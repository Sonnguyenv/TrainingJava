package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * ListeningFolder
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class ListeningFolder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listening_folder_seq")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String listeningDirectory;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private ListeningServer listeningServer;
}
