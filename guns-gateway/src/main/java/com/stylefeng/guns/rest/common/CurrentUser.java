package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.api.user.UserInfoModel;

public class CurrentUser {

    //线程绑定的存储空间
    private static final ThreadLocal<UserInfoModel> threadLocal=new ThreadLocal<>();

    public static void saveUserInfo(UserInfoModel userInfoModel){
        threadLocal.set(userInfoModel);

    }

    public static UserInfoModel getCurrentUser(){
        return threadLocal.get();
    }


}
