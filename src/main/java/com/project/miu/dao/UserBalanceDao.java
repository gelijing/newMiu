package com.project.miu.dao;

import com.project.miu.bean.model.Goods;
import com.project.miu.bean.model.UserBalance;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceDao extends JpaRepository<UserBalance,String> {
    UserBalance findByUserUuidAndDeleteData(@Param("userUuid") String userUuid,
                                             @Param("deleteData") Integer deleteData);

    //UserBalance findByUserUuid(@Param("userUuid") String userUuid);
}
