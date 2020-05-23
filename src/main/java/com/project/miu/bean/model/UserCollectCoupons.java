package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UserCollectCoupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userUuid;
    private String couponsUuid;
    private Integer deleteData;
    private Date createTime;
    private Date updateTime;
}
