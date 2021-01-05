package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ErrorCode
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class ErrorCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "error_code_seq")
    private Long id;

    @Column(length = 10, unique = true)
    private String code;

    @Column(length = 200)
    private String message;
}
