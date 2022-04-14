package com.ep.domain;

import java.io.Serializable;

/*
* 用于封装后端给前端的信息
* */
public class ResultInfo implements Serializable {
    private int status;    //状态码
    private String msg;    //错误信息
    private Object data;   //后端返回结果数据对象


    /**
     * 无参构造函数
     * */
    public ResultInfo(){

    }

    /*
     * 有参构造函数
     * @param status
     * @param msg
     * */
    public ResultInfo(int status,String msg){
        this.msg = msg;
        this.status = status;

    }

    /*
     * 有参构造函数
     * @param status
     * @param msg
     * @param data
     * */
    public ResultInfo(int status,String msg,Object data){
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "ResultInfo{" +
                "status:" + status +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}';
    }
}
