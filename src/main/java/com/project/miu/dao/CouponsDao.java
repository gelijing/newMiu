package com.project.miu.dao;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponsDao extends JpaRepository<Coupons,Integer> {
    Page<Coupons> findByCategoryUuid(Long categoryUuid, Pageable pageable);

    Coupons findByUuid(String uuid);
}
