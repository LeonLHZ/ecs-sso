package cn.lhz.sso.mapper;

import cn.lhz.common.entity.User;

public interface UserMapper {
int addUser(User user);

User selectUserByUserNamePassword(User user);

int updateUser(User user);


}