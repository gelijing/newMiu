package com.project.miu.dao;

import com.project.miu.bean.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {
    Category findByCategoryName(String categoryName);
    //test
    List<Category> findAll();
}
