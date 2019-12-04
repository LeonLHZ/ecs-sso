package cn.lhz.sso.service.consumer.fallback;

import cn.lhz.common.hystrix.FallBack;
import cn.lhz.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author Neo
 * @date 2019/11/26 23:39
 */
@Component
public class RedisServiceFallBack implements RedisService
{
    public RedisServiceFallBack()
    {
    }

    @Override
    public String put(String key, String value, long seconds)
    {
       return FallBack.badGetway();

    }

    @Override
    public String get(String key)
    {
        return FallBack.badGetway();
    }
}
