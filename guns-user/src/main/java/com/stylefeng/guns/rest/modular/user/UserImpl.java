package com.stylefeng.guns.rest.modular.user;


import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.user.UserAPI;
import org.springframework.stereotype.Component;

//直接将服务暴露出去
@Component
@Service(interfaceClass = UserAPI.class)
public class UserImpl implements UserAPI{

    @Override
    public boolean login(String username, String password) {
        System.out.println("this is user service  "+username);
        return false;
    }
}