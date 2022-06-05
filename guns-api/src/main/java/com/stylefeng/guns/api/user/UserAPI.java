package com.stylefeng.guns.api.user;

public interface UserAPI {

    /**
     * 返回用户的id
     * @param username
     * @param password
     * @return
     */
    public int login(String username,String password);

    boolean register(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);




}
