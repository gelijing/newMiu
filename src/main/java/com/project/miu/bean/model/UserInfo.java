package com.project.miu.bean.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String userName;
  private String password;
  private String userUuid;
  private long status;//1:正常 2:删除 3:锁定账号
  @CreatedDate
  private Date createTime;
  @LastModifiedDate
  private Date updateTime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

}
