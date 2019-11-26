package com.project.miu.service;

import com.project.miu.bean.model.Comments;
import com.project.miu.dao.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.UUID;

import static com.project.miu.commons.constants.Constants.SESSION_TOKEN_KEY;

@Service
public class CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    /**
     * 添加评论
     * @param couponsUuid 优惠券uuid
     * @param commentsContent 评价内容
     * @param request
     * @return
     */
    public int addComments(String couponsUuid, String commentsContent, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userUuid = (String) session.getAttribute(SESSION_TOKEN_KEY);//获取用户id
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String commentsUuid = str.replace("-", "");
        Comments comments = new Comments();
        comments.setCommentsUuid(commentsUuid);
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
     * @param commentsUuid 评论id
     * @return
     * @throws Exception
     */
    public boolean deleteComment(String commentsUuid) throws Exception {
        Comments res = commentsDao.findByCommentsUuidAndDeleteData(commentsUuid,1);
        if(res == null){
            throw new Exception("该评论不存在！");
        }
        res.setDeleteData(2);
        Comments save = commentsDao.save(res);
        if(save == null){
            return false;
        }
        return true;
    }
}
