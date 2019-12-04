package cn.lhz.sso.service.impl;

import cn.lhz.common.entity.User;
import cn.lhz.common.utils.*;
import cn.lhz.sso.mapper.UserMapper;
import cn.lhz.sso.service.LoginService;
import cn.lhz.sso.service.consumer.RedisService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neo
 * @date 2019/11/26 23:01
 */
@Service
public class LoginServiceImpl implements LoginService
{
    private static final Logger logger =  LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    public LoginServiceImpl()
    {
    }

    @Override
    public User login(String loginCode, String plantPssword)
    {
        User user =null;
        //先从redis中查找数据
        String json = redisService.get(loginCode);
        //如果redis中没有则去数据库中查找
        if (json==null){
            user= userMapper.login(loginCode, EscUtil.md5(plantPssword));


            //查出数据放到redis中logincode为key，有效时间为一天（60*60*24）
            try
            {
                redisService.put(loginCode, MapperUtils.obj2json(user),60*60*24);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


        }
        else {
            try
            {
                user = MapperUtils.json2pojo(json, User.class);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }


        return user;
    }
}
