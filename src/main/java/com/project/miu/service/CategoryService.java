package com.project.miu.service;

import com.project.miu.bean.bo.CategoryBO;
import com.project.miu.bean.model.Category;
import com.project.miu.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public int insertCategory(CategoryBO categoryBO) {
        Category category = new Category();
        category.setCategoryUuid(categoryBO.getCategoryUuid());
        category.setCategoryName(categoryBO.getCategoryName());
        Category save = categoryDao.save(category);//todo delete 默认值0？？
        if(save == null){
            return 0;
        }
        return 1;
    }
}
