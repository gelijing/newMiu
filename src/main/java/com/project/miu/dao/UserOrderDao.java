package com.project.miu.dao;

import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserOrderDao extends JpaRepository<UserOrder,String> {
    UserOrder findByOrderUuid(String orderUuid);
}
