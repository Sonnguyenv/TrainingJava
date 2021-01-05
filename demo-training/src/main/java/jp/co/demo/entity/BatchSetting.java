package jp.co.demo.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * BatchSetting
 */
@Entity
@Data
@QueryEntity
@EqualsAndHashCode(callSuper = true)
public class BatchSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_setting_seq")
    private Long id;

    private int timesRetry;
}
