package com.project.miu.dao;

import com.project.miu.bean.model.Category;
import com.project.miu.bean.model.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao extends JpaRepository<Goods,String> {
    Goods findByGoodsUuidAndDeleteData(@Param("goodsUuid") String goodsUuid,
                                        @Param("deleteData") Integer deleteData);
}
