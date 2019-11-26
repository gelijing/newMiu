package com.project.miu.controller;

import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/comments",method = {RequestMethod.POST})
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 添加评论
     * @param couponsUuid
     * @param commentsContent //todo 评论内容 要求？？
     * @param request
     * @return
     */
    @RequestMapping(value = "/addComments",method = {RequestMethod.POST})
    public Result addComments(String couponsUuid, String commentsContent, HttpServletRequest request){
        if(couponsUuid == null || StringUtils.isEmpty(commentsContent)){
            return ResultUtil.error("参数错误！");
        }
        int res = commentsService.addComments(couponsUuid,commentsContent,request);
        if(res == 1){
            return ResultUtil.success();
        }
        return ResultUtil.error("添加评论失败，请稍后重试");
    }

    @RequestMapping(value = "delComments",method = {RequestMethod.DELETE})
    public Result delComments(String commentsUuid) throws Exception {
        if(StringUtils.isEmpty(commentsUuid)){
            return ResultUtil.error("参数错误！");
        }
        Boolean res = commentsService.deleteComment(commentsUuid);
        if(!res){
            return ResultUtil.error("删除评论失败！");
        }
        return ResultUtil.success("删除评论成功！");
    }
}
