package com.project.miu.dao;

import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.Great;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreatDao extends JpaRepository<Great,Long> {
    Great findByUserUuidAndCouponsUuid(String user_uuid, String coupons_uuid);

    Great findByUuid(String uuid);

    List<Great> findByUserUuid(String user_uuid);
}
