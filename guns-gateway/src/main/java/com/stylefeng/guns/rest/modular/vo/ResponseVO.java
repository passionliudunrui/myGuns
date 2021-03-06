package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

@Data

public class ResponseVO<M>{

    private int status;

    private String msg;
    //返回数据实体
    private M date;

    //图片前缀
    private String imgPre;


    private ResponseVO(){
    }

    public static<M> ResponseVO success(String imgPre,M m){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setDate(m);
        responseVO.setImgPre(imgPre);

        return responseVO;
    }



    public static<M> ResponseVO success(M m){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setDate(m);

        return responseVO;
    }


    public static<M> ResponseVO success(String message){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(message);

        return responseVO;
    }



    //业务异常
    public static<M> ResponseVO serviceFail(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);

        return responseVO;
    }

    //系统异常
    public static<M> ResponseVO appFail(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);

        return responseVO;
    }




    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public M getDate() {
        return date;
    }

    public void setDate(M date) {
        this.date = date;
    }
}
