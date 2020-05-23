package com.project.miu.dao;

import com.project.miu.bean.bo.CouponsBO;
import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CouponsDao extends JpaRepository<Coupons,String> {
    Page<Coupons> findByCategoryUuid(String categoryUuid, Pageable pageable);

    Coupons findByUuid(String uuid);

    List<Coupons> findByEndTimeLessThan(Date nowTime);

    @Query(value = "select * from coupons WHERE bank_uuid in(?1) ORDER BY create_time",nativeQuery = true)
    Page<Coupons> findByBankUuidList(@Param("bankIdList") List<String> bankIdList,
                                 @Param("pageable") Pageable pageable);

    Page<Coupons> findByBankUuid(@Param("bankUuid") String bankUuid,
                                 @Param("pageable") Pageable pageable);
}
