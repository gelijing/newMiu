package com.project.miu.controller;

import com.project.miu.bean.bo.CategoryBO;
import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category",method = {RequestMethod.POST})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/insertCategory",method = {RequestMethod.POST})
    public Result insertCategory(CategoryBO categoryBO){
        if(categoryBO == null ||
                StringUtils.isEmpty(categoryBO.getCategoryName())||
                categoryBO.getCategoryUuid() == 0){
            return ResultUtil.error();
        }
        int res = categoryService.insertCategory(categoryBO);
        if(res == 0){
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),"插入失败，请稍后再试！");
        }
        return ResultUtil.success();
    }
}
