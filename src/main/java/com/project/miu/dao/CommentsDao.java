package com.project.miu.dao;

import com.project.miu.bean.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsDao extends JpaRepository<Comments,Long> {

    Comments findByUserUuidAndCommentsUuidAndDeleteData(String userUuid, String commentsUuid, Integer deleteData);
}
