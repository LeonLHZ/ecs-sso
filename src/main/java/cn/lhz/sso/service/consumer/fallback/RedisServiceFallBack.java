package cn.lhz.sso.service.consumer.fallback;

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
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public String get(String key)
    {
        throw new UnsupportedOperationException("Method not implemented.");
    }
}
