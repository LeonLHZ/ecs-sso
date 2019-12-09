package cn.lhz.sso.mapper;

import cn.lhz.common.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper
{
    int addUser(User user);

    //User selectUserByUserNamePassword(User user);

    int updateUser(User user);

    User login(@Param("userUsername") String loginCode,@Param("userPassword")String plantPassword);

}