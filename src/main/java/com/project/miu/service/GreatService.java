package com.project.miu.service;

import com.project.miu.bean.model.Coupons;
import com.project.miu.bean.model.Great;
import com.project.miu.dao.CouponsDao;
import com.project.miu.dao.GreatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GreatService {
    @Autowired
    private GreatDao greatDao;

    @Autowired
    private CouponsDao couponsDao;

    /**
     * 收藏
     * @param user_uuid
     * @param coupons_uuid
     * @return
     */
    public int addGreat(Long user_uuid, Long coupons_uuid) {
        Great great = greatDao.findByUserUuidAndCouponsUuid(user_uuid,coupons_uuid);
        if(great == null){//未收藏=>收藏
            Great great1 = new Great();
            great1.setUserUuid(user_uuid);
            great1.setCouponsUuid(coupons_uuid);
            great1.setDeleteData(1);
            greatDao.save(great1);
            return 1;
        } else if(great != null && great.getDeleteData() == 1){//已收藏=>不变
            return 0;
        }else {//取消收藏后=>收藏
            Great greatByUuid = greatDao.findByUuid(great.getUuid());
            greatByUuid.setDeleteData(1);
            greatDao.save(greatByUuid);
            return 1;
        }
    }

    /**
     * 取消收藏
     * @param user_uuid
     * @param coupons_uuid
     * @return
     */
    public int decGreat(Long user_uuid,Long coupons_uuid){
        Great great = greatDao.findByUserUuidAndCouponsUuid(user_uuid,coupons_uuid);
        if(great != null && great.getDeleteData() == 1){
            great.setDeleteData(2);
            greatDao.save(great);
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 查看收藏内容
     * @param userUuid
     * @return
     */
    public List<Coupons> getGreat(long userUuid) {
        List<Great> greatList = greatDao.findByUserUuid(userUuid);
        List<Coupons> couponsList = new ArrayList<>();
        for(int i = 0 ; i < greatList.size();i++){
            long couponsUuid = greatList.get(i).getCouponsUuid();
            Coupons coupons = couponsDao.findByUuid(String.valueOf(couponsUuid));
            couponsList.add(coupons);
        }
        return couponsList;
    }
}
