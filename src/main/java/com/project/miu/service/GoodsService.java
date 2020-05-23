package com.project.miu.service;

import com.project.miu.bean.model.Comments;
import com.project.miu.bean.model.Goods;
import com.project.miu.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:24 下午
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    //查看所有商品 分页显示
    public Page<Goods> viewGoods(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
        Page<Goods> goodsPage = goodsDao.findAll(pageable);
        return goodsPage;
    }

}
