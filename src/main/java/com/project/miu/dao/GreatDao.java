package com.project.miu.dao;

import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.Great;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreatDao extends JpaRepository<Great,Long> {
    Great findByUserUuidAndCouponsUuid(long user_uuid, long coupons_uuid);

    Great findByUuid(long uuid);

    List<Great> findByUserUuid(long user_uuid);
}
