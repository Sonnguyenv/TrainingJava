package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Hospital
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class Hospital extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    private Long id;

    @NotNull
    @Column(length = 10)
    private String code;

    @Column(length = 100)
    private String name;

    private boolean enable;

    private boolean deleted;
}
