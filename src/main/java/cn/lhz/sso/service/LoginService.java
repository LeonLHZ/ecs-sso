package cn.lhz.sso.service;

import cn.lhz.common.entity.User;

/**
 * @author Neo
 * @date 2019/11/26 23:01
 */
public interface LoginService
{
    public User login(String loginCode,String plantPssword);
}