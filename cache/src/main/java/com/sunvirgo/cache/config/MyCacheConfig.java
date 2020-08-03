package com.sunvirgo.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 类说明:
 *
 * @author : 黄刚
 * @date : 2020/8/3 22:13
 **/
@Configuration
public class MyCacheConfig {
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+"["+ Arrays.asList(objects).toString()+"]";
            }
        };

    }
}
