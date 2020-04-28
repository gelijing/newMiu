package com.project.miu.service;

import com.project.miu.bean.bo.CategoryBO;
import com.project.miu.bean.model.Category;
import com.project.miu.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 插入类目
     * @param categoryBO
     * @return
     */
    public int insertCategory(CategoryBO categoryBO) {
        Category cg = categoryDao.findByCategoryName(categoryBO.getCategoryName());
        Assert.notNull(cg,"此类目已存在！");
        Category category = new Category();
        category.setCategoryUuid(categoryBO.getCategoryUuid());
        category.setCategoryName(categoryBO.getCategoryName());
        category.setDeleteData(1);
        Category save = categoryDao.save(category);//todo delete 默认值0？？
        if(save == null){
            return 0;
        }
        return 1;
    }

    public List<Category> findAll(){
        List<Category> categoryList = categoryDao.findAll();
        return categoryList;
    }
}
