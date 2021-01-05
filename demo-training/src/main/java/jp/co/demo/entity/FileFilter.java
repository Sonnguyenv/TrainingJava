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
 * FileFilter
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class FileFilter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_filter_seq")
    private Long id;

    @NotNull
    @Column(length = 150)
    private String name;

    private boolean available;

    @ManyToOne
    private ListeningFolder listeningFolder;
}
