package com.project.miu.service;

import com.project.miu.bean.model.Bank;
import com.project.miu.dao.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: gelj
 * @Date: 2020/5/22 9:47 下午
 */
@Service
public class BankService {

    @Autowired
    private BankDao bankDao;

    public Bank getBankInfo(String bankUuid){
        Bank byBankUuid = bankDao.findByBankUuid(bankUuid);
        return byBankUuid;
    }
}
