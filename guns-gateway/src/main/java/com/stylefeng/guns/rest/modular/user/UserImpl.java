package com.stylefeng.guns.rest.modular.user;


import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//直接将服务暴露出去
@Component
@Service
public class UserImpl implements UserAPI{

    @Override
    public boolean login(String username, String password) {
        return true;
    }
}
