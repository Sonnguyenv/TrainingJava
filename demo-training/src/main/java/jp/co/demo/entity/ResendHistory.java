package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * ResendHistory
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class ResendHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resend_history_seq")
    private Long id;

    @ManyToOne
    private TransferHistory transferHistory;

    @ManyToOne
    private ErrorCode errorCode;
}
