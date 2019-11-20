package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long detailId;
    private long orderId;
    private long uuid;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer delete;

}
