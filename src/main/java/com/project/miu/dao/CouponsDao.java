package com.project.miu.dao;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsDao extends JpaRepository<Coupons,Integer> {
    List<Coupons> findByCategoryUuid(Integer categoryUuid);

    Coupons findByUuid(String uuid);
}
