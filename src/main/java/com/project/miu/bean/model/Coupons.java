package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String uuid;
    private String title;
    private long money;
    private String address;
    private String description;
    private String categoryUuid;
    private String bankUuid;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int deleteData;
}
