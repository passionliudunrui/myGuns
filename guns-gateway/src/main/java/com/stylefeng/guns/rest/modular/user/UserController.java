package com.stylefeng.guns.rest.modular.user;


import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Reference(interfaceClass = UserAPI.class,check = false)
    private UserAPI userAPI;


    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseVO register(UserModel userModel){
        if(userModel.getUsername()==null||userModel.getUsername().trim().length()==0){
            return ResponseVO.serviceFail("用户名不能为空");
        }
        if(userModel.getPassword()==null||userModel.getPassword().trim().length()==0){
            return ResponseVO.serviceFail("密码不能为空");
        }

        boolean isSuccess = userAPI.register(userModel);
        if(isSuccess){
            return ResponseVO.success("注册成功");
        }
        else{
            return ResponseVO.serviceFail("注册失败");
        }
    }

    @RequestMapping(value="check",method = RequestMethod.POST)
    public ResponseVO check(String username){
        if(username!=null&&username.trim().length()>0){
            boolean notExists=userAPI.checkUsername(username);
            if(notExists){
                return ResponseVO.success("用户名不存在");
            }
            else{
                return ResponseVO.serviceFail("用户名存在");
            }

        }
        else{

            return ResponseVO.serviceFail("用户名不能为空");
        }
    }


    @RequestMapping(value="logout",method = RequestMethod.GET)
    public ResponseVO logout(String username){
        /**
         * 应用：
         * 1.前端存储jwt （七天）  jwt刷新问题
         * 2.服务器端会存储活动用户信息 30min
         * 3.jwt中的userId为key查找活跃用户。
         */


        return ResponseVO.success("用户退出成功");

    }



    @RequestMapping(value="getUserInfo",method = RequestMethod.GET)
    public ResponseVO getUserInfo(String username){
        //获取当前登录用户  将用户id传入后端进行查询

        String userId = CurrentUser.getCurrentUser();
        if(userId!=null&&userId.trim().length()>0){
            int uuid=Integer.parseInt(userId);
            UserInfoModel userInfo = userAPI.getUserInfo(uuid);
            if(userInfo!=null){

                return ResponseVO.success(userInfo);
            }else{
                return ResponseVO.appFail("用户信息查询失败");
            }
        }else{

            return ResponseVO.serviceFail("用户未登录");
        }

    }


    @RequestMapping(value="updateUserInfo",method = RequestMethod.POST)
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel){
        //获取当前登录用户  将用户id传入后端进行查询

        String userId = CurrentUser.getCurrentUser();
        if(userId!=null&&userId.trim().length()>0){
            int uuid=Integer.parseInt(userId);
            //判断当前登录人员的id和修改结果id是否一致
            if(uuid!=userInfoModel.getUuid()){
                return ResponseVO.serviceFail("请修改自己的信息");
            }
            UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
            if(userInfo!=null){

                return ResponseVO.success(userInfo);
            }else{
                return ResponseVO.appFail("用户信息修改失败");
            }
        }else{

            return ResponseVO.serviceFail("用户未登录");
        }

    }

}
