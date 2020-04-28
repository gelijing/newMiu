package com.project.miu.controller;

import com.project.miu.bean.bo.CategoryBO;
import com.project.miu.bean.model.Category;
import com.project.miu.commons.myEnum.ResultEnum;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/category",method = {RequestMethod.POST})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 增加优惠券类型 如美食/旅游等
     * //todo 此种类型只针对管理员权限 未实现
     * @param categoryBO
     * @return
     */
    @RequestMapping(value = "/insertCategory",method = {RequestMethod.POST})
    public Result insertCategory(CategoryBO categoryBO){
        Assert.isNull(categoryBO,"参数错误！");
        Assert.isNull(categoryBO.getMemberId(),"参数错误！");
        Assert.isNull(categoryBO.getCategoryUuid(),"参数错误！");
        Assert.isNull(categoryBO.getCategoryName(),"参数错误！");

        int res = categoryService.insertCategory(categoryBO);
        if(res == 0){
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),"插入失败，请稍后再试！");
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/testcategory",method = {RequestMethod.POST})
    public Result selectAll(){
        List<Category> all = categoryService.findAll();
        String path = "/usr/local/opt/testDemo.txt";
        try {
            FileWriter writer = new FileWriter(path);// 字符流
            writer.write("category:"+all.toString());
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultUtil.success(categoryService.findAll());
    }
}
