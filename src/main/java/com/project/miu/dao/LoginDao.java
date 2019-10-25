package com.project.miu.dao;

import com.project.miu.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao extends JpaRepository<UserInfo,Integer> {
    /*@Select("select * from userInfo where user_name=#{uname}")
    List<Map<String,Object>> findByUserName(@Param("uname") String userName);*/
    /*@Insert("insert into userInfo (user_name,password) values (#{uname},#{password},#{name},#{status},#{createTime})")
    List<Map<String,Object>> insert(@Param("uname") String userName,
                                    @Param("password") String password,
                                    @Param("name") String name,
                                    @Param("status") Integer status,
                                    @Param("createTime") Date createTime);*/

}
