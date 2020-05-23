package com.project.miu.dao;

import com.project.miu.bean.model.Bank;
import com.project.miu.bean.model.Great;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDao extends JpaRepository<Bank,Long> {
    Bank findByBankUuid(@Param("bankUuid") String bankUuid);
}
