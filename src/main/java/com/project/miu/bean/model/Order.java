package com.project.miu.bean.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long orderId;
    private String buyerName;
    private String buyerPhone;
    private long orderMoney;
    private byte orderStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Byte delete;

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id")
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "buyer_name")
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Basic
    @Column(name = "buyer_phone")
    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    @Basic
    @Column(name = "order_money")
    public long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(long orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Basic
    @Column(name = "order_status")
    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "delete")
    public Byte getDelete() {
        return delete;
    }

    public void setDelete(Byte delete) {
        this.delete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                orderId == order.orderId &&
                orderMoney == order.orderMoney &&
                orderStatus == order.orderStatus &&
                Objects.equals(buyerName, order.buyerName) &&
                Objects.equals(buyerPhone, order.buyerPhone) &&
                Objects.equals(createTime, order.createTime) &&
                Objects.equals(updateTime, order.updateTime) &&
                Objects.equals(delete, order.delete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, buyerName, buyerPhone, orderMoney, orderStatus, createTime, updateTime, delete);
    }
}
