package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:12 下午
 */
@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String goodsUuid;
    private String goodsName;
    private String goodsContent;
    private String goodsPicture;
    private long goodsPrice;
    private String goodsFactory;
    private Integer deleteData;
    private Date createTime;
    private Date updateTime;
}
