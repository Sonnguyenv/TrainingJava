package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import jp.co.demo.enums.TransferStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * TransferHistory
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class TransferHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_history_seq")
    private Long id;

    @Column(length = 250)
    private String listeningFilePath;

    @Column(length = 250)
    private String localFilePath;

    @Column(length = 250)
    private String receivingFilePath;

    @Column(length = 150)
    private String fileName;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 2)
    private TransferStatus status;

    private int resendCount;

    private Long parentId;

    @ManyToOne
    private ErrorCode errorCode;

    @ManyToOne
    private Hospital hospital;
}
