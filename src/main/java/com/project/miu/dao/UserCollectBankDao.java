package com.project.miu.dao;

import com.project.miu.bean.model.UserCollectBank;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: gelj
 * @Date: 2020/5/1 4:03 下午
 */
public interface UserCollectBankDao extends JpaRepository<UserCollectBank,Long> {
    List<UserCollectBank> findByUserUuid(@Param("userUuid") String userUuid);
}
