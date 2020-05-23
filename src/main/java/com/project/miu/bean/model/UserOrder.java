package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:12 下午
 *
 * 用户订单
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderUuid;
    private String userUuid;
    private String GoodsUuid;
    private Integer orderStatus;//订单状态：1、取消支付 2、待支付 3、已完成 4、失败
    private Integer deleteData;
    private Date createTime;
    private Date updateTime;
}
