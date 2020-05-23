package com.project.miu.dao;

import com.project.miu.bean.model.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsDao extends JpaRepository<Comments,Long>, JpaSpecificationExecutor<Comments> {

    Comments findByUserUuidAndCommentsUuidAndDeleteData(String userUuid, String commentsUuid, Integer deleteData);

    Page<Comments> findByCouponsUuid(@Param("couponsUuid") String couponsUuid,
                                         @Param("of") Pageable of);

}
