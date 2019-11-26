package cn.lhz.sso.service.impl;

import cn.lhz.common.entity.User;
import cn.lhz.common.utils.EscUtil;
import cn.lhz.sso.mapper.UserMapper;
import cn.lhz.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neo
 * @date 2019/11/26 23:01
 */
@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private UserMapper userMapper;

    public LoginServiceImpl()
    {
    }

    @Override
    public User login(String loginCode, String plantPssword)
    {
        User user = new User();
        user.setUserPassword(EscUtil.md5(plantPssword));
        user.setUserUsername(loginCode);

        return userMapper.selectUserByUserNamePassword(user);
    }
}
