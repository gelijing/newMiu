package com.project.miu.service;

import com.project.miu.bean.model.Comments;
import com.project.miu.commons.util.IdGenerateUtil;
import com.project.miu.commons.util.SecurityUtil;
import com.project.miu.dao.CommentsDao;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * 评论
 */
@Service
public class CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    /**
     * 添加评论 todo 是否允许重复评论？？
     * @param couponsUuid 优惠券uuid
     * @param commentsContent 评价内容
     * @return
     */
    public int addComments(String userUuid, String couponsUuid, String commentsContent) {
        Comments comments = new Comments();
        comments.setCommentsUuid(IdGenerateUtil.getUUID());
        comments.setUserUuid(userUuid);
        comments.setCommentsContent(commentsContent);
        comments.setCouponsUuid(couponsUuid);
        comments.setDeleteData(1);
        Comments save = commentsDao.save(comments);
        if(save == null){
            return 0;
        }
        return 1;
    }

    /**
     * 删除评论
     * @param commentsUuid 评论id todo 只能删除自己的评论？？
     * @return
     * @throws Exception
     */
    public boolean deleteComment(String memberId,String commentsUuid) throws Exception {
        Comments res = commentsDao.findByUserUuidAndCommentsUuidAndDeleteData(memberId,commentsUuid,1);
        Assert.notNull(res,"删除评论失败！");
        res.setDeleteData(2);
        Comments save = commentsDao.save(res);
        if(save == null){
            return false;
        }
        return true;
    }
}
