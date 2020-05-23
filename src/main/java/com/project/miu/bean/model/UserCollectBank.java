package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UserCollectBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userUuid;
    private String bankUuid;
    private Integer deleteData;
    private Date createTime;
    private Date updateTime;
}
