package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String commentsUuid;
    private String commentsContent;
    private String userUuid;
    private String couponsUuid;
    private Integer deleteData;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
}
