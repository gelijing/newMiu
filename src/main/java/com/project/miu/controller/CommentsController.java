package com.project.miu.controller;

import com.project.miu.bean.model.Comments;
import com.project.miu.bean.vo.PageResult;
import com.project.miu.commons.util.Result;
import com.project.miu.commons.util.ResultUtil;
import com.project.miu.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/comments",method = {RequestMethod.POST})
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 添加评论
     * @param couponsUuid
     * @param commentsContent //todo 评论内容 要求？？
     * @return
     */
    @RequestMapping(value = "/addComments",method = {RequestMethod.POST})
    public Result addComments(String userUuid,String couponsUuid, String commentsContent){
        if(couponsUuid == null || StringUtils.isEmpty(commentsContent)){
            return ResultUtil.error("参数错误！");
        }
        int res = commentsService.addComments(userUuid,couponsUuid,commentsContent);
        if(res == 1){
            return ResultUtil.success();
        }
        return ResultUtil.error("添加评论失败，请稍后重试");
    }

    /**
     * 删除评论
     * @param userUuid 用户id
     * @param commentsUuid 评论id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delComments",method = {RequestMethod.DELETE})
    public Result delComments(@RequestParam("userUuid") String userUuid,
                              @RequestParam("commentsUuid") String commentsUuid) throws Exception {
        if(StringUtils.isEmpty(userUuid) || StringUtils.isEmpty(commentsUuid)){
            return ResultUtil.error("参数错误！");
        }
        Boolean res = commentsService.deleteComment(userUuid,commentsUuid);
        if(!res){
            return ResultUtil.error("删除评论失败！");
        }
        return ResultUtil.success("删除评论成功！");
    }

    /**
     * 查看优惠券信息下的相关评论
     * @param couponsUuid 优惠券id
     * @param page 页码 默认1
     * @param size 条数 默认10
     * @return
     */
    @RequestMapping(value = "viewComments",method = {RequestMethod.POST})
    public Result viewComments(@RequestParam("couponsUuid") String couponsUuid,
                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "10") Integer size){
        if( StringUtils.isEmpty(couponsUuid)){
            return ResultUtil.error("参数错误！");
        }
        Page<Comments> commentsPage = commentsService.viewComments(couponsUuid, page, size);
        return ResultUtil.success(new PageResult<>(commentsPage.getTotalElements(),commentsPage.getContent()));
    }}
