package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:10 下午
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userUuid;
    private long userAccountBalance;
    private Integer deleteData;
    private Date createTime;
    private Date updateTime;
}
